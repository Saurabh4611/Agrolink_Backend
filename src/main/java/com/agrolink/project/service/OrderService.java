package com.agrolink.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.agrolink.project.dto.OrderRequest;
import com.agrolink.project.entity.Crop;
import com.agrolink.project.entity.Order;
import com.agrolink.project.entity.OrderStatus;
import com.agrolink.project.entity.User;
import com.agrolink.project.repository.CropRepository;
import com.agrolink.project.repository.OrderRepository;
import com.agrolink.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final CropRepository cropRepository;
	
	public String placeOrder(OrderRequest request)
	{
		User buyer = userRepository.findById(request.getBuyerId()).orElseThrow(()->new RuntimeException("Buyer Not Found"));
		
		Crop crop = cropRepository.findById(request.getCropId()).orElseThrow(()-> new RuntimeException("Crop Not Found"));
		
		
		
		
		//stock checkups
		if(crop.getQuantity()<request.getQuantity())
		{
			throw new RuntimeException("Insufficient stock Avaliable");
		}
		
	
	Double totalPrice = 
			crop.getPrice()*request.getQuantity();
	
	
	
	Order order = Order.builder()
			      .buyer(buyer)
			      .crop(crop)
			      .quantity(request.getQuantity())
			      .totalPrice(totalPrice)
			      .orderDate(LocalDateTime.now())
			      .status(OrderStatus.PENDING)
			      .build();
	
	   
	crop.setQuantity(crop.getQuantity()-request.getQuantity());
	
	cropRepository.save(crop);
	orderRepository.save(order);
	
	return "Order Placed Successfully";
	}
	
	public String cancelOrder(Long orderId)
	{
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()->new RuntimeException("Order Not Found"));
		
		Crop crop = order.getCrop();
		
		crop.setQuantity(
				crop.getQuantity()+ order.getQuantity());
		
		cropRepository.save(crop);
		
		
		//update Status for buyer
		
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		
		return "Order Cancelled Successfully";
	}
	
		
		
		
		
	
	public List<Order> getBuyerOrder(Long buyerId)
	{
		return orderRepository.findByBuyerId(buyerId);
	}
	
	

}
