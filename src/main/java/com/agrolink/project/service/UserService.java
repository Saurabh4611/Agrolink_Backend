package com.agrolink.project.service;

import org.springframework.stereotype.Service;

import com.agrolink.project.entity.User;
import com.agrolink.project.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	
	
	public final UserRepository re;
	
	
	public User getUserById(Long id)
	{
		return (User) re.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
	}

}
