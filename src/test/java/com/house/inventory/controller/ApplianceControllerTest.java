package com.house.inventory.controller;

import com.house.inventory.model.Appliance;
import com.house.inventory.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplianceControllerTest {

    @Mock
    private ApplianceService applianceService;

    @InjectMocks
    private ApplianceController applianceController;

    private Appliance appliance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appliance = new Appliance(1L, "Refrigerator", "LG", null, true, 200.0, 70, 180);
    }

    @Test
    void testCreateAppliance() {
        when(applianceService.saveAppliance(any(Appliance.class))).thenReturn(appliance);
        ResponseEntity<Appliance> response = applianceController.createAppliance(appliance);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(appliance, response.getBody());
    }

    @Test
    void testGetAllAppliances() {
        when(applianceService.getAll()).thenReturn(List.of(appliance));
        ResponseEntity<List<Appliance>> response = applianceController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testUpdateAppliance() {
        when(applianceService.editAppliance(eq(1L), any(Appliance.class))).thenReturn(appliance);
        ResponseEntity<Appliance> response = applianceController.updateAppliance(1L, appliance);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(appliance, response.getBody());
    }

    @Test
    void testPatchAppliance() {
        when(applianceService.patchAppliance(eq(1L), any(Map.class))).thenReturn(appliance);
        ResponseEntity<Appliance> response = applianceController.partiallyUpdateAppliance(1L, Map.of("name", "New Fridge"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(appliance, response.getBody());
    }

    @Test
    void testDeleteAppliance() {
        doNothing().when(applianceService).deleteById(1L);
        ResponseEntity<Void> response = applianceController.deleteAppliance(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testGetHealth() {
        String health = applianceController.getHealth();
        assertEquals("Hello world", health);
    }
}
