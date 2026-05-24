package com.agrolink.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrolink.project.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Long> {
List<Crop> findByFarmerId(Long farmerId);
Optional<Crop> findById(Long id);

List<Crop> findByCropNameContainingIgnoreCase(String cropName);
	
}
