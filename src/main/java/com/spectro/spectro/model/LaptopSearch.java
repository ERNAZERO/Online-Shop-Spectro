package com.spectro.spectro.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LaptopSearch {
    private String model;
    private String manufacturer;
    private String producingCountry;
    private String OS;
    private String housingMaterial;
    private String dimensions;
    private String processorType;
    private String numberOfCores;
    private String RAM;
    private String memory;
    private String screenSize;
    private String screenResolution;
    private String camera;
    private String speaker;
    private String headphoneJack;
    private String videoCard;
    private String connectors;
    private BigDecimal maxBigDecimal;
    private BigDecimal minBigDecimal;

}