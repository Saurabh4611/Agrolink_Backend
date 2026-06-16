package com.agrolink.project.dto;

import com.agrolink.project.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
	
	
	private String token;
	private UserRole role;
	private Long userId;

}
