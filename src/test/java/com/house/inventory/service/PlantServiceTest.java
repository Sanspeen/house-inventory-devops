package com.house.inventory.service;

import com.house.inventory.model.Plant;
import com.house.inventory.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantService plantService;

    private Plant samplePlant;

    @BeforeEach
    void setUp() {
        samplePlant = new Plant(1L, "Fern", null, 365, "Indoor", 30);
    }

    @Test
    void testCreatePlant() {
        when(plantRepository.save(samplePlant)).thenReturn(samplePlant);
        Plant result = plantService.create(samplePlant);
        assertEquals(samplePlant, result);
    }

    @Test
    void testGetAllPlants() {
        List<Plant> plants = Arrays.asList(samplePlant);
        when(plantRepository.findAll()).thenReturn(plants);
        List<Plant> result = plantService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testEditPlant_Success() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(samplePlant));
        when(plantRepository.save(any(Plant.class))).thenReturn(samplePlant);
        Plant result = plantService.editPlant(1L, samplePlant);
        assertEquals(samplePlant, result);
    }

    @Test
    void testEditPlant_NotFound() {
        when(plantRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> plantService.editPlant(1L, samplePlant));
    }

    @Test
    void testPatchPlant_Success() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(samplePlant));
        when(plantRepository.save(any(Plant.class))).thenReturn(samplePlant);
        Plant result = plantService.patchPlant(1L, Map.of("environment", "Outdoor"));
        assertEquals(samplePlant, result);
    }

    @Test
    void testPatchPlant_NotFound() {
        when(plantRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> plantService.patchPlant(1L, Map.of("environment", "Outdoor")));
    }

    @Test
    void testDeletePlant() {
        doNothing().when(plantRepository).deleteById(1L);
        assertDoesNotThrow(() -> plantService.deleteById(1L));
        verify(plantRepository, times(1)).deleteById(1L);
    }
}
