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
    void testNonNullFields() {
        assertThrows(NullPointerException.class, () -> new Appliance(4L, null, "Sony", new Date(), true, 100.0, 30, 40));
        assertThrows(NullPointerException.class, () -> new Appliance(4L, "Speaker", null, new Date(), true, 100.0, 30, 40));
        assertThrows(NullPointerException.class, () -> new Appliance(4L, "Speaker", "Sony", new Date(), null, 100.0, 30, 40));
        assertThrows(NullPointerException.class, () -> new Appliance(4L, "Speaker", "Sony", new Date(), true, null, 30, 40));
        assertThrows(NullPointerException.class, () -> new Appliance(4L, "Speaker", "Sony", new Date(), true, 100.0, null, 40));
        assertThrows(NullPointerException.class, () -> new Appliance(4L, "Speaker", "Sony", new Date(), true, 100.0, 30, null));
    }
}
