package com.house.inventory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class FurnitureTest {

    @Test
    void testCreateFurnitureSuccess() {
        Date boughtDate = new Date();
        Furniture furniture = new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, 100, 200);

        assertEquals(1L, furniture.getId());
        assertEquals("Table", furniture.getName());
        assertEquals("BrandX", furniture.getBrand());
        assertEquals(boughtDate, furniture.getBoughtDate());
        assertTrue(furniture.getIsNew());
        assertEquals(20.5, furniture.getEnergyConsumption());
        assertEquals(100, furniture.getWidth());
        assertEquals(200, furniture.getLength());
    }

    @Test
    void testSetters() {
        Furniture furniture = new Furniture();
        Date boughtDate = new Date();

        furniture.setId(2L);
        furniture.setName("Chair");
        furniture.setBrand("BrandY");
        furniture.setBoughtDate(boughtDate);
        furniture.setIsNew(false);
        furniture.setEnergyConsumption(15.0);
        furniture.setWidth(50);
        furniture.setLength(120);

        assertEquals(2L, furniture.getId());
        assertEquals("Chair", furniture.getName());
        assertEquals("BrandY", furniture.getBrand());
        assertEquals(boughtDate, furniture.getBoughtDate());
        assertFalse(furniture.getIsNew());
        assertEquals(15.0, furniture.getEnergyConsumption());
        assertEquals(50, furniture.getWidth());
        assertEquals(120, furniture.getLength());
    }

    @Test
    void testPartialUpdateWithSetters() {
        Furniture furniture = new Furniture(3L, "Desk", "BrandZ", new Date(), true, 25.5, 80, 150);

        // Solo actualizamos algunos valores
        furniture.setName("Office Desk");
        furniture.setEnergyConsumption(30.0);
        furniture.setWidth(85);

        assertEquals("Office Desk", furniture.getName());
        assertEquals(30.0, furniture.getEnergyConsumption());
        assertEquals(85, furniture.getWidth());

        // Verificamos que los otros valores se mantuvieron
        assertEquals("BrandZ", furniture.getBrand());
        assertEquals(150, furniture.getLength());
        assertTrue(furniture.getIsNew());
    }

    @Test
    void testAllowNullBoughtDate() {
        Furniture furniture = new Furniture();
        furniture.setBoughtDate(null);
        assertNull(furniture.getBoughtDate());
    }

    @Test
    void testNonNullFieldsShouldThrowException() {
        Date boughtDate = new Date();

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, null, "BrandX", boughtDate, true, 20.5, 100, 200)
        );

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, "Table", null, boughtDate, true, 20.5, 100, 200)
        );

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, "Table", "BrandX", boughtDate, null, 20.5, 100, 200)
        );

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, "Table", "BrandX", boughtDate, true, null, 100, 200)
        );

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, null, 200)
        );

        assertThrows(NullPointerException.class, () ->
                new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, 100, null)
        );
    }
}
