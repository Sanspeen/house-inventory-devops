package com.house.inventory.service;

import com.house.inventory.model.Furniture;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class FurnitureService {
    public Furniture generateGenericFurniture() {
        Furniture furniture = new Furniture();
        furniture.setIsNew(true);
        furniture.setName("Generic Furniture");
        furniture.setWidth(40);
        furniture.setBrand("Generic");
        furniture.setBoughtDate(new Date());
        furniture.setEnergyConsumption(50.0);
        furniture.setLength(30);
        return furniture;
    }
}
