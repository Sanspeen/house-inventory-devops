package com.house.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private Date boughtDate;

    @NonNull
    private Integer estimatedLifeExpectancy; // This value is made in expected days of life.

    @NonNull
    private String environment;

    @NonNull
    private Integer currentAge; // Value in days.

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public void setEstimatedLifeExpectancy(Integer estimatedLifeExpectancy) {
        this.estimatedLifeExpectancy = estimatedLifeExpectancy;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setCurrentAge(Integer currentAge) {
        this.currentAge = currentAge;
    }
}
