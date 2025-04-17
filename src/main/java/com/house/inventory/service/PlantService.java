package com.house.inventory.service;

import com.house.inventory.model.Plant;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlantService {
    public Plant generateGenericPlant() {
        Plant plant = new Plant();
        plant.setName("Generic Plant");
        plant.setBoughtDate(new Date());
        plant.setEstimatedLifeExpectancy(365);
        plant.setEnvironment("Indoor");
        plant.setCurrentAge(30);
        return plant;
    }
}
