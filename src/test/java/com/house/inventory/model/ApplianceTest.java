package com.house.inventory.model;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApplianceTest {

    @Test
    void testNoArgsConstructor() {
        Appliance appliance = new Appliance();
        assertNotNull(appliance);
    }

    @Test
    void testAllArgsConstructor() {
        Date boughtDate = new Date();
        Appliance appliance = new Appliance(1L, "Refrigerator", "Samsung", boughtDate, true, 500.0, 70, 180);

        assertEquals(1L, appliance.getId());
        assertEquals("Refrigerator", appliance.getName());
        assertEquals("Samsung", appliance.getBrand());
        assertEquals(boughtDate, appliance.getBoughtDate());
        assertTrue(appliance.getIsNew());
        assertEquals(500.0, appliance.getEnergyConsumption());
        assertEquals(70, appliance.getWidth());
        assertEquals(180, appliance.getLength());
    }

    @Test
    void testSettersAndGetters() {
        Appliance appliance = new Appliance();
        Date boughtDate = new Date();

        appliance.setId(2L);
        appliance.setName("Oven");
        appliance.setBrand("LG");
        appliance.setBoughtDate(boughtDate);
        appliance.setIsNew(false);
        appliance.setEnergyConsumption(800.0);
        appliance.setWidth(60);
        appliance.setLength(90);

        assertEquals(2L, appliance.getId());
        assertEquals("Oven", appliance.getName());
        assertEquals("LG", appliance.getBrand());
        assertEquals(boughtDate, appliance.getBoughtDate());
        assertFalse(appliance.getIsNew());
        assertEquals(800.0, appliance.getEnergyConsumption());
        assertEquals(60, appliance.getWidth());
        assertEquals(90, appliance.getLength());
    }

    @Test
    void testEqualsAndHashCode() {
        Date boughtDate = new Date();
        Appliance appliance1 = new Appliance(1L, "Microwave", "Panasonic", boughtDate, true, 1200.0, 50, 40);
        Appliance appliance2 = new Appliance(1L, "Microwave", "Panasonic", boughtDate, true, 1200.0, 50, 40);
        Appliance appliance3 = new Appliance(2L, "Blender", "Philips", boughtDate, false, 300.0, 20, 30);

        assertEquals(appliance1, appliance2);
        assertNotEquals(appliance1, appliance3);
        assertEquals(appliance1.hashCode(), appliance2.hashCode());
        assertNotEquals(appliance1.hashCode(), appliance3.hashCode());
    }

    @Test
    void testToString() {
        Date boughtDate = new Date();
        Appliance appliance = new Appliance(3L, "Air Conditioner", "Daikin", boughtDate, false, 2000.0, 100, 200);

        String expectedToString = "Appliance(id=3, name=Air Conditioner, brand=Daikin, boughtDate=" + boughtDate + ", isNew=false, energyConsumption=2000.0, width=100, length=200)";

        assertEquals(expectedToString, appliance.toString());
    }
}

