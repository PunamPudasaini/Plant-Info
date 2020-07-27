package com.example.plantinfo.dto;

public class HimalayanPlant {
    String plantName;
    String plantDescription;
    int plantImage;

    public HimalayanPlant() {
    }

    public HimalayanPlant(String plantName, String plantDescription, int plantImage) {
        this.plantName = plantName;
        this.plantDescription = plantDescription;
        this.plantImage = plantImage;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public int getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(int plantImage) {
        this.plantImage = plantImage;
    }
}

