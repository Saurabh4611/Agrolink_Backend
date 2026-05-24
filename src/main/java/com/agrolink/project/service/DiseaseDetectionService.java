package com.agrolink.project.service;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.agrolink.project.dto.DiseaseResponse;

@Service
public class DiseaseDetectionService {

    private final String API_URL =
        "https://serverless.roboflow.com/plants-detection-0nczh/3?api_key=d4HIBCfZZ1MwiQhknGOm";

    public DiseaseResponse detectDisease(MultipartFile file) {

        try {

            String base64Image =
                Base64.getEncoder().encodeToString(file.getBytes());

            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> response =
                restTemplate.postForObject(
                    API_URL,
                    base64Image,
                    Map.class
                );

            List<Map<String, Object>> predictions =
                (List<Map<String, Object>>) response.get("predictions");

            if (predictions == null || predictions.isEmpty()) {

                return new DiseaseResponse(
                        "No Disease",
                        0,
                        "Plant looks healthy"
                );
            }

            Map<String, Object> prediction = predictions.get(0);

            String disease =
                prediction.get("class").toString();

            Double confidence =
                Double.parseDouble(
                    prediction.get("confidence").toString()
                );

            
            String solution = getSolution(disease);

            return new DiseaseResponse(
                    disease,
                    confidence * 100,
                    solution
            );

        } catch (Exception e) {
            e.printStackTrace();

            return new DiseaseResponse(
                    "Error",
                    0,
                    "Detection failed"
            );
        }
    }

    
    private String getSolution(String disease) {

        switch (disease.toLowerCase()) {

            case "powdery-mildew":
                return "Use sulfur fungicide and avoid overwatering.";

            case "leaf-spot":
                return "Remove infected leaves and spray copper fungicide.";

            case "rust":
                return "Use neem oil spray and improve air circulation.";

            default:
                return "Consult agriculture expert.";
        }
    }
}