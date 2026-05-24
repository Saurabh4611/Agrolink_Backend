package com.agrolink.project.dto;

public class DiseaseResponse {

    private String disease;
    private double confidence;
    private String solution;

    public DiseaseResponse() {
    }

    public DiseaseResponse(
            String disease,
            double confidence,
            String solution) {

        this.disease = disease;
        this.confidence = confidence;
        this.solution = solution;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}