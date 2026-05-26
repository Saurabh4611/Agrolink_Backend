package com.agrolink.project.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.dto.CartRequest;
import com.agrolink.project.entity.Cart;
import com.agrolink.project.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
	
	private final CartService cartService;
	
	@PreAuthorize("hasRole('BUYER')")
	@PostMapping("/add")
	public String addToCart(@RequestBody CartRequest request)
	{
		return cartService.addToCart(request);
	}
	@PreAuthorize("hasRole('BUYER')")
	@GetMapping("/{buyerId}")
	public List<Cart> getBuyerCart(@PathVariable Long buyerId)
	{
		return cartService.getBuyerCart(buyerId);
	}
	
	@PreAuthorize("hasRole('BUYER')")
	@DeleteMapping("/{cartId}")
	public String removeItem(@PathVariable Long cartId)
	{
		return cartService.removeCartItem(cartId);
	}

}
