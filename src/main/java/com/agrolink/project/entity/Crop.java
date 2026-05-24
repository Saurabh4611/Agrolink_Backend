package com.agrolink.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crops")
public class Crop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cropName;
	
	@Column(length = 1000)
	private String description;
	
	private Double price;
	
	private Integer quantity;
	
	private String category;
	
	private String location;
	
	private String imageUrl;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private User farmer;

	public Crop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Crop(Long id, String cropName, String description, Double price, Integer quantity, String category,
			String location, String imageUrl, LocalDateTime createdAt, User farmer) {
		super();
		this.id = id;
		this.cropName = cropName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.location = location;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
		this.farmer = farmer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getFarmer() {
		return farmer;
	}

	public void setFarmer(User farmer) {
		this.farmer = farmer;
	}
	
	
	
	
}
