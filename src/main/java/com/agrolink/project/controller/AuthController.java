package com.agrolink.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.dto.AuthResponse;
import com.agrolink.project.dto.LoginRequest;
import com.agrolink.project.dto.RegisterRequest;
import com.agrolink.project.service.AuthService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request)
	{
		return ResponseEntity.ok(authService.register(request));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login
	(@RequestBody LoginRequest request)
	{
		return ResponseEntity.ok(authService.login(request));
	}

}
