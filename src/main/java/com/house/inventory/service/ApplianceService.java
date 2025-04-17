package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplianceService {
    public Appliance generateGenericAppliance(){
        return Appliance.builder()
                .isNew(true)
                .name("Generic")
                .width(23)
                .brand("Generic")
                .boughtDate(new Date())
                .energyConsumption(123.0)
                .length(12)
                .build();
    }

}
