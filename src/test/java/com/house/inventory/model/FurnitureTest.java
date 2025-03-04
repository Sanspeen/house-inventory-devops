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

    @Test
    void testEqualsAndHashCode() {
        Date boughtDate = new Date();
        Furniture furniture1 = new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, 100, 200);
        Furniture furniture2 = new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, 100, 200);

        assertEquals(furniture1, furniture2);
        assertEquals(furniture1.hashCode(), furniture2.hashCode());
    }

    @Test
    void testToString() {
        Date boughtDate = new Date();
        Furniture furniture = new Furniture(1L, "Table", "BrandX", boughtDate, true, 20.5, 100, 200);

        String expectedString = "Furniture(id=1, name=Table, brand=BrandX, boughtDate=" + boughtDate +
                ", isNew=true, energyConsumption=20.5, width=100, length=200)";
        assertEquals(expectedString, furniture.toString());
    }
}
