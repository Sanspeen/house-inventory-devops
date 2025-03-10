package com.house.inventory.controller;

import com.house.inventory.model.Plant;
import com.house.inventory.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    private Plant samplePlant;

    @BeforeEach
    void setUp() {
        samplePlant = new Plant(1L, "Fern", null, 365, "Indoor", 30);
    }

    @Test
    void testCreatePlant() {
        when(plantService.create(any(Plant.class))).thenReturn(samplePlant);
        ResponseEntity<Plant> response = plantController.createPlant(samplePlant);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(samplePlant, response.getBody());
    }

    @Test
    void testGetAllPlants() {
        List<Plant> plants = Arrays.asList(samplePlant);
        when(plantService.getAll()).thenReturn(plants);
        ResponseEntity<List<Plant>> response = plantController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testUpdatePlant() {
        when(plantService.editPlant(eq(1L), any(Plant.class))).thenReturn(samplePlant);
        ResponseEntity<Plant> response = plantController.updatePlant(1L, samplePlant);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePlant, response.getBody());
    }

    @Test
    void testPatchPlant() {
        when(plantService.patchPlant(eq(1L), any(Map.class))).thenReturn(samplePlant);
        ResponseEntity<Plant> response = plantController.partiallyUpdatePlant(1L, Map.of("environment", "Outdoor"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePlant, response.getBody());
    }

    @Test
    void testDeletePlant() {
        doNothing().when(plantService).deleteById(1L);
        ResponseEntity<Void> response = plantController.deletePlant(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(plantService, times(1)).deleteById(1L);
    }

    @Test
    void testHealthCheck() {
        String response = plantController.getHealth();
        assertEquals("Hello world", response);
    }
}
