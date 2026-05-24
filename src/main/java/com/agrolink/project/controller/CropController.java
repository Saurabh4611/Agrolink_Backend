package com.agrolink.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrolink.project.dto.CropRequest;
import com.agrolink.project.entity.Crop;
import com.agrolink.project.service.CropService;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data

@AllArgsConstructor
@RestController
@RequestMapping("/api/crops")
@CrossOrigin("*")
public class CropController {
	
	private final CropService cropService;
	
	
	
	@PostMapping
	public ResponseEntity<?> addcrop(
			@RequestBody CropRequest request)
	{
		return ResponseEntity.ok(cropService.addCrop(request));
	}
	
	@GetMapping
	public ResponseEntity<List<Crop>>
	getAllCrops()
	{
		return ResponseEntity.ok(cropService.getAllCrops());
	}
	
	@GetMapping("/farmer/{farmerId}")
	public ResponseEntity<List<Crop>>getFarmerCrops
	(@PathVariable Long farmerId)
	{
		
		return ResponseEntity.ok(cropService.getFarmerCrops(farmerId));
	}
	
	
	@DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(
            @PathVariable Long cropId) {

        cropService.deleteCrop(cropId);

        return ResponseEntity.ok(
                "Crop Deleted Successfully");
    
	}
	@GetMapping("/{id}")
	public Crop getCropById(@PathVariable Long id)
	{
		return cropService.getCropById(id);
	}
	
	@PutMapping("/{id}")
	public Crop updateCrop(@PathVariable Long id , @RequestBody Crop crop)
	{
		
		return cropService.updateCrop(id, crop);
		
		
		
	}
	@GetMapping("/search")
	public List<Crop> searchCrop(
			@RequestParam String keyword)
	{
		return cropService.searchCrop(keyword);
	}

}
