package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import com.house.inventory.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    public Appliance saveAppliance(Appliance newAppliance){
        newAppliance.setBoughtDate(new Date());
        return applianceRepository.save(newAppliance);
    }

    public List<Appliance> getAll(){
        return applianceRepository.findAll();
    }

    public Appliance editAppliance(Long id, Appliance appliance){
        return applianceRepository.findById(id).map(existingAppliance -> {
            existingAppliance.setName(appliance.getName());
            existingAppliance.setBrand(appliance.getBrand());
            existingAppliance.setBoughtDate(appliance.getBoughtDate());
            existingAppliance.setIsNew(appliance.getIsNew());
            existingAppliance.setEnergyConsumption(appliance.getEnergyConsumption());
            existingAppliance.setWidth(appliance.getWidth());
            existingAppliance.setLength(appliance.getLength());
            return applianceRepository.save(existingAppliance);
        }).orElseThrow(() -> new NoSuchElementException("Appliance not found with id " + id));
    }

    public Appliance patchAppliance(Long id, Map<String, Object> updates){
        return applianceRepository.findById(id).map(existingAppliance -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existingAppliance.setName((String) value);
                    case "brand" -> existingAppliance.setBrand((String) value);
                    case "boughtDate" -> existingAppliance.setBoughtDate((Date) value);
                    case "isNew" -> existingAppliance.setIsNew((Boolean) value);
                    case "energyConsumption" -> existingAppliance.setEnergyConsumption((Double) value);
                    case "width" -> existingAppliance.setWidth((Integer) value);
                    case "length" -> existingAppliance.setLength((Integer) value);
                }
            });
            return applianceRepository.save(existingAppliance);
        }).orElseThrow(() -> new NoSuchElementException("Appliance not found with id " + id));
    }
    public void deleteById(Long id){
        applianceRepository.deleteById(id);
    }
}
