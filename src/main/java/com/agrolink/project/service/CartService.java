package com.agrolink.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agrolink.project.dto.CartRequest;
import com.agrolink.project.entity.Cart;
import com.agrolink.project.entity.Crop;
import com.agrolink.project.entity.User;
import com.agrolink.project.repository.CartRepository;
import com.agrolink.project.repository.CropRepository;
import com.agrolink.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;
	
	private final CropRepository cropRepository;
	
	private final UserRepository userRepository;
	
	public String addToCart(CartRequest request)
	{
		User buyer = userRepository.findById(request.getBuyerId())
				.orElseThrow(()-> new RuntimeException("Buyer Not Found"));
		
		Crop crop = cropRepository.findById(request.getCropId())
				    .orElseThrow(()-> new RuntimeException("Crop Not Found"));
		
		Cart cart = Cart.builder()
				 .buyer(buyer)
				 .crop(crop)
				 .quantity(request.getQuantity())
				 .build();
		
		cartRepository.save(cart);
		
		return "crop Added To cart";
				 
	}
	
	public List<Cart> getBuyerCart(Long buyerId)
	{
		return cartRepository.findByBuyerId(buyerId);
	}
	
	public String removeCartItem(Long cartId)
	{
		cartRepository.deleteById(cartId);
		return "Item Removed";
	}
	
	
	
	
	

}
