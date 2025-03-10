package com.house.inventory.controller;

import com.house.inventory.model.Plant;
import com.house.inventory.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @PostMapping("/create")
    public ResponseEntity<Plant> createPlant(@RequestBody Plant newPlant) {
        Plant savedPlant = plantService.create(newPlant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Plant>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(plantService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant plant) {
        return ResponseEntity.ok(plantService.editPlant(id, plant));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Plant> partiallyUpdatePlant(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(plantService.patchPlant(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-check")
    public String getHealth() {
        return "Hello world";
    }
}
