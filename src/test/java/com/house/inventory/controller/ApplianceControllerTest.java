package com.house.inventory.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.inventory.model.Appliance;
import com.house.inventory.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ApplianceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ApplianceService applianceService;

    @InjectMocks
    private ApplianceController applianceController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Appliance appliance;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applianceController).build();

        appliance = new Appliance(1L, "Refrigerator", "Samsung", new Date(), true, 500.0, 70, 180);
    }

    @Test
    void shouldCreateApplianceSuccessfully() throws Exception {
        when(applianceService.saveAppliance(appliance)).thenReturn(appliance);

        mockMvc.perform(post("/v1/appliance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appliance)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(appliance.getId()))
                .andExpect(jsonPath("$.name").value(appliance.getName()))
                .andExpect(jsonPath("$.brand").value(appliance.getBrand()));
    }

    @Test
    void shouldGetAllAppliancesSuccessfully() throws Exception {
        List<Appliance> appliances = Arrays.asList(appliance, new Appliance(2L, "Washing Machine", "LG", new Date(), false, 300.0, 60, 90));

        when(applianceService.getAll()).thenReturn(appliances);

        mockMvc.perform(get("/v1/appliance/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(appliances.size()))
                .andExpect(jsonPath("$[0].name").value(appliance.getName()))
                .andExpect(jsonPath("$[1].name").value("Washing Machine"));
    }

    @Test
    void shouldReturnHealthCheckMessage() throws Exception {
        mockMvc.perform(get("/v1/appliance/health-check"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"));
    }
}