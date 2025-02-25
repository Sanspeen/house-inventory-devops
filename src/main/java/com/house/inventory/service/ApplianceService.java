package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import com.house.inventory.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

}
