package com.agrolink.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.dto.OrderRequest;
import com.agrolink.project.entity.Order;
import com.agrolink.project.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

	
	private final OrderService orderService;
	
	//placing Order Here
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
	
	@PutMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable Long orderId)
	{
		return orderService.cancelOrder(orderId);
	}
	
	
	
	
	
}
