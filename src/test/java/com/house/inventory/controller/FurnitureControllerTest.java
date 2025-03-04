package com.house.inventory.controller;

import com.house.inventory.model.Furniture;
import com.house.inventory.service.FurnitureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FurnitureControllerTest {

    @Mock
    private FurnitureService furnitureService;

    @InjectMocks
    private FurnitureController furnitureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Furniture furniture = new Furniture();
        when(furnitureService.create(furniture)).thenReturn(furniture);

        ResponseEntity<Furniture> response = furnitureController.create(furniture);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(furniture, response.getBody());
    }

    @Test
    void testGetAll() {
        List<Furniture> furnitureList = Arrays.asList(new Furniture(), new Furniture());
        when(furnitureService.getAll()).thenReturn(furnitureList);

        ResponseEntity<List<Furniture>> response = furnitureController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(furnitureList, response.getBody());
    }

    @Test
    void testUpdateAppliance() {
        Long id = 1L;
        Furniture furniture = new Furniture();
        when(furnitureService.editFurniture(id, furniture)).thenReturn(furniture);

        ResponseEntity<Furniture> response = furnitureController.updateAppliance(id, furniture);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(furniture, response.getBody());
    }

    @Test
    void testPartiallyUpdateAppliance() {
        Long id = 1L;
        Map<String, Object> updates = new HashMap<>();
        Furniture updatedFurniture = new Furniture();
        when(furnitureService.patchFurniture(id, updates)).thenReturn(updatedFurniture);

        ResponseEntity<Furniture> response = furnitureController.partiallyUpdateAppliance(id, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFurniture, response.getBody());
    }

    @Test
    void testDeleteAppliance() {
        Long id = 1L;
        doNothing().when(furnitureService).deleteById(id);

        ResponseEntity<Void> response = furnitureController.deleteAppliance(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(furnitureService, times(1)).deleteById(id);
    }

    @Test
    void testGetHealth() {
        String response = furnitureController.getHealth();
        assertEquals("Hello world", response);
    }
}