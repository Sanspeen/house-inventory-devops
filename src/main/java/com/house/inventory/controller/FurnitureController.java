package com.house.inventory.controller;

import com.house.inventory.model.Appliance;
import com.house.inventory.model.Furniture;
import com.house.inventory.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/furniture")
public class FurnitureController {

    @Autowired
    FurnitureService furnitureService;

    @PostMapping("/create")
    public ResponseEntity<Furniture> create(@RequestBody Furniture furniture){
        Furniture savedFurniture = furnitureService.create(furniture);
        return ResponseEntity.status(HttpStatus.CREATED).body(furniture);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Furniture>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Furniture> updateAppliance(@PathVariable Long id, @RequestBody Furniture furniture) {
        return ResponseEntity.ok(furnitureService.editFurniture(id, furniture));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Furniture> partiallyUpdateAppliance(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(furnitureService.patchFurniture(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppliance(@PathVariable Long id) {
        furnitureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-check")
    public String getHealth(){
        return "Hello world";
    }
}


