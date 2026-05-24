package com.agrolink.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropRequest {
	
	
	private String cropName;
	private String description;
	private Double price;
	private Integer quantity;
	private String category;
	private String location;
	private String imageUrl;
	private Long farmerId;
	
	

}
