package com.house.inventory.controller;

import com.house.inventory.model.Appliance;
import com.house.inventory.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/get-all")
    public ResponseEntity<List<Appliance>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(applianceService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appliance> updateAppliance(@PathVariable Long id, @RequestBody Appliance appliance) {
        return ResponseEntity.ok(applianceService.editAppliance(id, appliance));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Appliance> partiallyUpdateAppliance(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(applianceService.patchAppliance(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppliance(@PathVariable Long id) {
        applianceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-check")
    public String getHealth(){
        return "Hello world";
    }
}
