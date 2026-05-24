package com.agrolink.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrolink.project.dto.AuthResponse;
import com.agrolink.project.dto.LoginRequest;
import com.agrolink.project.dto.RegisterRequest;
import com.agrolink.project.entity.User;
import com.agrolink.project.repository.UserRepository;
import com.agrolink.project.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public String register(RegisterRequest request)
	{
		System.out.println(request.getFullName());
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		User user = User.builder()
		.fullName(request.getFullName())
		.email(request.getEmail())
		.password(request.getPassword())
		.phone(request.getPhone())
		.address(request.getAddress())
		.role(request.getRole())
		.build();
		
		userRepository.save(user);
		
		return "User Registered Sucessfully";
	}
	
	public AuthResponse login(LoginRequest request)
	{
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()-> new RuntimeException("Invaild Email"));
		
		boolean matched = passwordEncoder.matches(
				request.getPassword(),
				user.getPassword());
		
		if(!matched)
		{
			throw new RuntimeException("Invaild Password");
		}
		
		String token = jwtUtil.generateToken(user.getEmail());
		
		return new AuthResponse(token);
	}
	

}
