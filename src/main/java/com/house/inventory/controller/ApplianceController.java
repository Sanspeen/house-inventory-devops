package com.house.inventory.controller;

import com.house.inventory.model.Appliance;
import com.house.inventory.service.ApplianceService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/appliance")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;



    @PostMapping("/create")
    public ResponseEntity<Appliance> createAppliance(@RequestBody Appliance newAppliance){
        Appliance savedAppliance = applianceService.saveAppliance(newAppliance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppliance);
    }

}
