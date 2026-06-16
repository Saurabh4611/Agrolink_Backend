package com.agrolink.project.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.dto.OrderRequest;
import com.agrolink.project.entity.Order;
import com.agrolink.project.entity.OrderStatus;
import com.agrolink.project.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

	
	private final OrderService orderService;
	
	//placing Order Here
	@PreAuthorize("hasRole('BUYER')")
	@PostMapping("/place")
	public String placeOrder(@RequestBody OrderRequest request)
	{
		return orderService.placeOrder(request);
	}
	
	//Buyer History
	@GetMapping("/{buyerId}")
	public List<Order>getBuyerOrders(
			@PathVariable Long buyerId)
	{
		return orderService.getBuyerOrder(buyerId);
	}
	@PreAuthorize("hasRole('BUYER')")
	@PutMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable Long orderId)
	{
		return orderService.cancelOrder(orderId);
	}
	
	//farmer Order controller down here -->
	// farmer controller to see order 
	
	@PreAuthorize("hasRole('FARMER')")
	@GetMapping("/farmer/{farmerId}")
	public List<Order> getFarmerOrders(
			@PathVariable Long farmerId)
	{
		return orderService.getFarmerOrders(farmerId);
	}
	
	//Farmer Changes status here-->
	@PreAuthorize("hasRole('FARMER')")
	@PutMapping("/status/{orderId}")
	public String updateOrderStatus(
			@PathVariable Long orderId,
			@RequestParam OrderStatus status)
	{
		return orderService.updateOrderStatus(orderId, status);
	}
	
	
	
	
	
	
	
	
}
