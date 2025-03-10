package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import com.house.inventory.repository.ApplianceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplianceServiceTest {

    @Mock
    private ApplianceRepository applianceRepository;

    @InjectMocks
    private ApplianceService applianceService;

    private Appliance appliance;

    @BeforeEach
    void setUp() {
        appliance = new Appliance(1L, "Fridge", "Samsung", new Date(), true, 150.5, 60, 80);
    }

    @Test
    void testSaveAppliance() {
        when(applianceRepository.save(any(Appliance.class))).thenReturn(appliance);
        Appliance savedAppliance = applianceService.saveAppliance(appliance);
        assertNotNull(savedAppliance);
        assertEquals("Fridge", savedAppliance.getName());
        verify(applianceRepository, times(1)).save(any(Appliance.class));
    }

    @Test
    void testGetAll() {
        when(applianceRepository.findAll()).thenReturn(List.of(appliance));
        List<Appliance> appliances = applianceService.getAll();
        assertFalse(appliances.isEmpty());
        assertEquals(1, appliances.size());
        verify(applianceRepository, times(1)).findAll();
    }

    @Test
    void testEditAppliance() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.of(appliance));
        when(applianceRepository.save(any(Appliance.class))).thenReturn(appliance);
        Appliance updatedAppliance = applianceService.editAppliance(1L, appliance);
        assertNotNull(updatedAppliance);
        assertEquals("Fridge", updatedAppliance.getName());
        verify(applianceRepository, times(1)).save(any(Appliance.class));
    }

    @Test
    void testEditAppliance_NotFound() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> applianceService.editAppliance(1L, appliance));
    }

    @Test
    void testPatchAppliance() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Microwave");
        updates.put("brand", "LG");
        updates.put("isNew", false);
        updates.put("energyConsumption", 100.0);
        updates.put("width", 50);
        updates.put("length", 40);

        when(applianceRepository.findById(1L)).thenReturn(Optional.of(appliance));
        when(applianceRepository.save(any(Appliance.class))).thenReturn(appliance);

        Appliance patchedAppliance = applianceService.patchAppliance(1L, updates);

        assertEquals("Microwave", patchedAppliance.getName());
        assertEquals("LG", patchedAppliance.getBrand());
        assertFalse(patchedAppliance.getIsNew());
        assertEquals(100.0, patchedAppliance.getEnergyConsumption());
        assertEquals(50, patchedAppliance.getWidth());
        assertEquals(40, patchedAppliance.getLength());
        verify(applianceRepository, times(1)).save(any(Appliance.class));
    }

    @Test
    void testPatchAppliance_NotFound() {
        when(applianceRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> applianceService.patchAppliance(1L, new HashMap<>()));
    }

    @Test
    void testDeleteAppliance() {
        doNothing().when(applianceRepository).deleteById(1L);
        applianceService.deleteById(1L);
        verify(applianceRepository, times(1)).deleteById(1L);
    }

    @Test
    void patchAppliance_ShouldUpdateBoughtDate_WhenValidIdProvided() {
        // Arrange
        Long id = 1L;
        Date newBoughtDate = new Date();
        Appliance existingAppliance = new Appliance(1L, "Fridge", "Samsung", new Date(), true, 150.5, 80, 60);
        when(applianceRepository.findById(id)).thenReturn(Optional.of(existingAppliance));
        when(applianceRepository.save(existingAppliance)).thenReturn(existingAppliance);

        // Act
        Appliance updatedAppliance = applianceService.patchAppliance(id, Map.of("boughtDate", newBoughtDate));

        // Assert
        assertEquals(newBoughtDate, updatedAppliance.getBoughtDate());
        verify(applianceRepository).save(existingAppliance);
    }

    @Test
    void patchAppliance_ShouldThrowException_WhenApplianceNotFound() {
        // Arrange
        Long id = 99L;
        when(applianceRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> applianceService.patchAppliance(id, Map.of("boughtDate", new Date())));
        assertEquals("Appliance not found with id 99", exception.getMessage());
    }
}
