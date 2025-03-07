package com.house.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Setter
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
}
