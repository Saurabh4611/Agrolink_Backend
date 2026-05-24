package com.agrolink.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agrolink.project.dto.DiseaseResponse;
import com.agrolink.project.service.DiseaseDetectionService;

@RestController
@RequestMapping("/api/disease")
@CrossOrigin("*")
public class DiseaseDetectionController {

    private final DiseaseDetectionService service;

    public DiseaseDetectionController(
            DiseaseDetectionService service) {

        this.service = service;
    }

    @PostMapping("/detect")
    public DiseaseResponse detectDisease(
            @RequestParam("file") MultipartFile file) {

        return service.detectDisease(file);
    }
}