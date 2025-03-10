package com.house.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "appliance")
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String brand;

    private Date boughtDate;

    @NonNull
    private Boolean isNew;

    @NonNull
    private Double energyConsumption;

    @NonNull
    private Integer width;

    @NonNull
    private Integer length;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
