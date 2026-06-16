package com.agrolink.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

   
    public String addCrop(CropRequest request) throws IOException {

        User farmer = userRepository.findById(request.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Farmer Not Found"));

        String fileName = request.getImage().getOriginalFilename();

        Path uploadPath = Paths.get("uploads");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(
                request.getImage().getInputStream(),
                uploadPath.resolve(fileName),
                StandardCopyOption.REPLACE_EXISTING
        );

        Crop crop = new Crop();

        crop.setCropName(request.getCropName());
        crop.setDescription(request.getDescription());
        crop.setPrice(request.getPrice());
        crop.setCategory(request.getCategory());
        crop.setLocation(request.getLocation());
        crop.setQuantity(request.getQuantity());

        crop.setImageUrl(fileName);

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
       
        old.setLocation(crop.getLocation());
        old.setPrice(crop.getPrice());
        old.setQuantity(crop.getQuantity());
        old.setCreatedAt(LocalDateTime.now());

        return cropRepository.save(old);
    }
}