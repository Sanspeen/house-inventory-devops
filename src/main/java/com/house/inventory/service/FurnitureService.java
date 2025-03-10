package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import com.house.inventory.model.Furniture;
import com.house.inventory.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class FurnitureService {

    @Autowired
    FurnitureRepository furnitureRepository;

    public Furniture create(Furniture furniture){
        return furnitureRepository.save(furniture);
    }

    public List<Furniture> getAll(){
        return furnitureRepository.findAll();
    }

    public Furniture editFurniture(Long id, Furniture furniture){
        return furnitureRepository.findById(id).map(existingFurniture -> {
            existingFurniture.setName(furniture.getName());
            existingFurniture.setBrand(furniture.getBrand());
            existingFurniture.setBoughtDate(furniture.getBoughtDate());
            existingFurniture.setIsNew(furniture.getIsNew());
            existingFurniture.setEnergyConsumption(furniture.getEnergyConsumption());
            existingFurniture.setWidth(furniture.getWidth());
            existingFurniture.setLength(furniture.getLength());
            return furnitureRepository.save(existingFurniture);
        }).orElseThrow(() -> new NoSuchElementException("Furniture not found with id " + id));
    }

    public Furniture patchFurniture(Long id, Map<String, Object> updates){
        return furnitureRepository.findById(id).map(existingFurniture -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existingFurniture.setName((String) value);
                    case "brand" -> existingFurniture.setBrand((String) value);
                    case "boughtDate" -> existingFurniture.setBoughtDate((Date) value);
                    case "isNew" -> existingFurniture.setIsNew((Boolean) value);
                    case "energyConsumption" -> existingFurniture.setEnergyConsumption((Double) value);
                    case "width" -> existingFurniture.setWidth((Integer) value);
                    case "length" -> existingFurniture.setLength((Integer) value);
                }
            });
            return furnitureRepository.save(existingFurniture);
        }).orElseThrow(() -> new NoSuchElementException("Furniture not found with id " + id));
    }
    public void deleteById(Long id){
        furnitureRepository.deleteById(id);
    }


}
