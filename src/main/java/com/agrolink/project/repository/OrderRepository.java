package com.agrolink.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolink.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	
	List<Order> findByBuyerId(Long buyerId);
}
