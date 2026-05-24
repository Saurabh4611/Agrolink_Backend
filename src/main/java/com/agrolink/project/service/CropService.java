package com.agrolink.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agrolink.project.dto.CropRequest;
import com.agrolink.project.entity.Crop;
import com.agrolink.project.entity.User;
import com.agrolink.project.repository.CropRepository;
import com.agrolink.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;

   
    public String addCrop(CropRequest request) {

        User farmer = userRepository.findById(request.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Farmer Not Found"));

        Crop crop = new Crop();

        crop.setCropName(request.getCropName());
        crop.setDescription(request.getDescription());
        crop.setPrice(request.getPrice());
        crop.setCategory(request.getCategory());
        crop.setLocation(request.getLocation());
        crop.setImageUrl(request.getImageUrl());
        crop.setQuantity(request.getQuantity());
        crop.setCreatedAt(LocalDateTime.now());
        crop.setFarmer(farmer);

        cropRepository.save(crop);

        return "Crop Added Successfully";
    }

    
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    
    public List<Crop> getFarmerCrops(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }

  
    public void deleteCrop(Long cropId) {
        cropRepository.deleteById(cropId);
    }

    
    public Crop getCropById(Long id) {

        return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop Not Found"));
    }

    // SEARCH CROP
    public List<Crop> searchCrop(String keyword) {

        return cropRepository
                .findByCropNameContainingIgnoreCase(keyword);
    }

    // UPDATE CROP
    public Crop updateCrop(Long id, Crop crop) {

        Crop old = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop Not Found"));

        old.setCropName(crop.getCropName());
        old.setCategory(crop.getCategory());
        old.setDescription(crop.getDescription());
        old.setImageUrl(crop.getImageUrl());
        old.setLocation(crop.getLocation());
        old.setPrice(crop.getPrice());
        old.setQuantity(crop.getQuantity());
        old.setCreatedAt(LocalDateTime.now());

        return cropRepository.save(old);
    }
}