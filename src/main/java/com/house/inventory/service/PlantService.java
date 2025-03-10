package com.house.inventory.service;

import com.house.inventory.model.Plant;
import com.house.inventory.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public Plant create(Plant plant){
        return plantRepository.save(plant);
    }

    public List<Plant> getAll(){
        return plantRepository.findAll();
    }

    public Plant editPlant(Long id, Plant plant){
        return plantRepository.findById(id).map(existingPlant -> {
            existingPlant.setName(plant.getName());
            existingPlant.setBoughtDate(plant.getBoughtDate());
            existingPlant.setEstimatedLifeExpectancy(plant.getEstimatedLifeExpectancy());
            existingPlant.setEnvironment(plant.getEnvironment());
            existingPlant.setCurrentAge(plant.getCurrentAge());
            return plantRepository.save(existingPlant);
        }).orElseThrow(() -> new NoSuchElementException("Plant not found with id " + id));
    }

    public Plant patchPlant(Long id, Map<String, Object> updates){
        return plantRepository.findById(id).map(existingPlant -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existingPlant.setName((String) value);
                    case "boughtDate" -> existingPlant.setBoughtDate((Date) value);
                    case "estimatedLifeExpectancy" -> existingPlant.setEstimatedLifeExpectancy((Integer) value);
                    case "environment" -> existingPlant.setEnvironment((String) value);
                    case "currentAge" -> existingPlant.setCurrentAge((Integer) value);
                }
            });
            return plantRepository.save(existingPlant);
        }).orElseThrow(() -> new NoSuchElementException("Plant not found with id " + id));
    }

    public void deleteById(Long id){
        plantRepository.deleteById(id);
    }
}
