package com.agrolink.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.entity.User;
import com.agrolink.project.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

	public final UserService us;
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id)
	{
		return us.getUserById(id);
	}
	
	
	
	
}
