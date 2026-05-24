package com.agrolink.project.dto;

import lombok.Data;

@Data

public class CartRequest {
	
	private Long buyerId;
	private Long cropId;
	private Integer quantity;

}
