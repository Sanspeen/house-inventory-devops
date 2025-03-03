package com.house.inventory.service;

import com.house.inventory.model.Appliance;
import com.house.inventory.repository.ApplianceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        appliance = new Appliance(null, "Fridge", "Samsung", null, true, 150.0, 70, 180);
    }

    @Test
    void saveAppliance_ShouldSaveApplianceWithCurrentDate() {
        when(applianceRepository.save(any(Appliance.class))).thenAnswer(invocation -> {
            Appliance savedAppliance = invocation.getArgument(0);
            savedAppliance.setId(1L);
            return savedAppliance;
        });

        Appliance savedAppliance = applianceService.saveAppliance(appliance);

        assertNotNull(savedAppliance);
        assertNotNull(savedAppliance.getBoughtDate());
        assertEquals(1L, savedAppliance.getId());
        verify(applianceRepository, times(1)).save(appliance);
    }

    @Test
    void getAll_ShouldReturnListOfAppliances() {
        List<Appliance> appliances = Arrays.asList(
                new Appliance(1L, "Fridge", "Samsung", new Date(), true, 150.0, 70, 180),
                new Appliance(2L, "Washing Machine", "LG", new Date(), false, 200.0, 60, 100)
        );

        when(applianceRepository.findAll()).thenReturn(appliances);

        List<Appliance> result = applianceService.getAll();

        assertEquals(2, result.size());
        assertEquals("Fridge", result.get(0).getName());
        assertEquals("Washing Machine", result.get(1).getName());
        verify(applianceRepository, times(1)).findAll();
    }
}
