package com.spectro.spectro.entity;

import com.spectro.spectro.enums.LaptopEnum;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="laptop")
@Data
public class LaptopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "producing_country")
    private String producingCountry;

    @Column(name = "OS")
    private String OS;

    @Column(name = "housing_material")
    private String housingMaterial;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "processor_type")
    private String processorType;

    @Column(name = "number_of_Cores")
    private String numberOfCores;

    @Column(name = "RAM")
    private String RAM;

    @Column(name = "memory")
    private String memory;

    @Column(name = "screen_size")
    private String screenSize;

    @Column(name = "screen_resolution")
    private String screenResolution;

    @Column(name = "camera")
    private String camera;

    @Column(name = "speaker")
    private String speaker;

    @Column(name = "headphone_jack")
    private String headphoneJack;

    @Column(name = "video_card")
    private String videoCard;

    @Column(name = "connectors")
    private String connectors;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LaptopEnum status;

    @Column(name="image")
    private String image;
}
