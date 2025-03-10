package com.house.inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.house.inventory.model.Furniture;
import com.house.inventory.repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class FurnitureServiceTest {

    @Mock
    private FurnitureRepository furnitureRepository;

    @InjectMocks
    private FurnitureService furnitureService;

    private Furniture furniture;

    @BeforeEach
    void setUp() {
        furniture = new Furniture();
        furniture.setId(1L);
        furniture.setName("Sofa");
        furniture.setBrand("BrandX");
        furniture.setBoughtDate(new Date());
        furniture.setIsNew(true);
        furniture.setEnergyConsumption(10.5);
        furniture.setWidth(100);
        furniture.setLength(200);
    }

    @Test
    void testCreateFurniture() {
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(furniture);
        Furniture savedFurniture = furnitureService.create(furniture);
        assertNotNull(savedFurniture);
        assertEquals("Sofa", savedFurniture.getName());
    }

    @Test
    void testGetAllFurniture() {
        List<Furniture> furnitureList = List.of(furniture);
        when(furnitureRepository.findAll()).thenReturn(furnitureList);
        List<Furniture> result = furnitureService.getAll();
        assertEquals(1, result.size());
        assertEquals("Sofa", result.get(0).getName());
    }

    @Test
    void testEditFurnitureSuccess() {
        when(furnitureRepository.findById(1L)).thenReturn(Optional.of(furniture));
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(furniture);

        Furniture updatedFurniture = new Furniture();
        updatedFurniture.setName("Chair");
        updatedFurniture.setBrand("BrandY");
        updatedFurniture.setIsNew(true); // <- Agregar este valor para evitar el NPE
        updatedFurniture.setBoughtDate(new Date());
        updatedFurniture.setEnergyConsumption(10.5);
        updatedFurniture.setWidth(50);
        updatedFurniture.setLength(100);

        Furniture result = furnitureService.editFurniture(1L, updatedFurniture);
        assertEquals("Chair", result.getName());
        assertEquals("BrandY", result.getBrand());
    }

    @Test
    void testEditFurnitureNotFound() {
        when(furnitureRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> furnitureService.editFurniture(1L, furniture));
    }

    @Test
    void testPatchFurnitureSuccess() {
        when(furnitureRepository.findById(1L)).thenReturn(Optional.of(furniture));
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(furniture);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Table");
        updates.put("width", 150);

        Furniture result = furnitureService.patchFurniture(1L, updates);
        assertEquals("Table", result.getName());
        assertEquals(150, result.getWidth());
    }

    @Test
    void testPatchFurnitureNotFound() {
        when(furnitureRepository.findById(1L)).thenReturn(Optional.empty());
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Table");
        assertThrows(NoSuchElementException.class, () -> furnitureService.patchFurniture(1L, updates));
    }

    @Test
    void testDeleteFurniture() {
        doNothing().when(furnitureRepository).deleteById(1L);
        assertDoesNotThrow(() -> furnitureService.deleteById(1L));
        verify(furnitureRepository, times(1)).deleteById(1L);
    }
}

