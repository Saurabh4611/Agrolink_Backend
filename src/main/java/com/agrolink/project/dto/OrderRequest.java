package com.agrolink.project.dto;

import lombok.Data;

@Data
public class OrderRequest {
	
	
	private Long buyerId;
	private Long cropId;
	private Integer quantity;

}
