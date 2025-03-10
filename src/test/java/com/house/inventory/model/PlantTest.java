package com.house.inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    private Plant plant;

    @BeforeEach
    void setUp() {
        plant = new Plant();
    }
        @Test
        void testNoArgsConstructor() {
            Plant plant = new Plant();
            assertNotNull(plant);
        }

        @Test
        void testAllArgsConstructor() {
            Date boughtDate = new Date();
            Plant plant = new Plant(1L, "Fern", boughtDate, 365, "Indoor", 100);

            assertEquals(1L, plant.getId());
            assertEquals("Fern", plant.getName());
            assertEquals(boughtDate, plant.getBoughtDate());
            assertEquals(365, plant.getEstimatedLifeExpectancy());
            assertEquals("Indoor", plant.getEnvironment());
            assertEquals(100, plant.getCurrentAge());
        }

    @Test
    void testPlantConstructorAndGetters() {
        Date boughtDate = new Date();
        Plant plant = new Plant(1L, "Fern", boughtDate, 365, "Indoor", 30);

        assertEquals(1L, plant.getId());
        assertEquals("Fern", plant.getName());
        assertEquals(boughtDate, plant.getBoughtDate());
        assertEquals(365, plant.getEstimatedLifeExpectancy());
        assertEquals("Indoor", plant.getEnvironment());
        assertEquals(30, plant.getCurrentAge());
    }

    @Test
    void testSetters() {
        Date boughtDate = new Date();
        plant.setId(2L);
        plant.setName("Bamboo");
        plant.setBoughtDate(boughtDate);
        plant.setEstimatedLifeExpectancy(730);
        plant.setEnvironment("Outdoor");
        plant.setCurrentAge(100);

        assertEquals(2L, plant.getId());
        assertEquals("Bamboo", plant.getName());
        assertEquals(boughtDate, plant.getBoughtDate());
        assertEquals(730, plant.getEstimatedLifeExpectancy());
        assertEquals("Outdoor", plant.getEnvironment());
        assertEquals(100, plant.getCurrentAge());
    }

    @Test
    void testNullSetters() {
        Date boughtDate = new Date();
        plant.setId(null);
        plant.setBoughtDate(null);

        assertEquals(1, plant.getId());
        assertEquals(null, plant.getBoughtDate());
    }

    @Test
    void testNonNullFields() {
        assertThrows(NullPointerException.class, () -> new Plant(null, null, new Date(), null, null, null));
    }

}
