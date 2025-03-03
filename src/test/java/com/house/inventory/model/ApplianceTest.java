package com.house.inventory.model;

import com.house.inventory.model.Appliance;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;

public class ApplianceTest {
    @Test
    void shouldCreateApplianceSuccessfully() {
        Date purchaseDate = new Date();

        Appliance appliance = new Appliance(
                1L,
                "Refrigerator",
                "Samsung",
                purchaseDate,
                true,
                500.0,
                70,
                180
        );

        assertThat(appliance).isNotNull();
        assertThat(appliance.getId()).isEqualTo(1L);
        assertThat(appliance.getName()).isEqualTo("Refrigerator");
        assertThat(appliance.getBrand()).isEqualTo("Samsung");
        assertThat(appliance.getBoughtDate()).isEqualTo(purchaseDate);
        assertThat(appliance.getIsNew()).isTrue();
        assertThat(appliance.getEnergyConsumption()).isEqualTo(500.0);
        assertThat(appliance.getWidth()).isEqualTo(70);
        assertThat(appliance.getLength()).isEqualTo(180);
    }

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        Appliance appliance = new Appliance();

        appliance.setId(2L);
        appliance.setName("Washing Machine");
        appliance.setBrand("LG");
        appliance.setBoughtDate(null);
        appliance.setIsNew(false);
        appliance.setEnergyConsumption(300.0);
        appliance.setWidth(60);
        appliance.setLength(90);

        assertThat(appliance.getId()).isEqualTo(2L);
        assertThat(appliance.getName()).isEqualTo("Washing Machine");
        assertThat(appliance.getBrand()).isEqualTo("LG");
        assertThat(appliance.getBoughtDate()).isNull();
        assertThat(appliance.getIsNew()).isFalse();
        assertThat(appliance.getEnergyConsumption()).isEqualTo(300.0);
        assertThat(appliance.getWidth()).isEqualTo(60);
        assertThat(appliance.getLength()).isEqualTo(90);
    }

    @Test
    void shouldFailWhenRequiredFieldsAreMissing() {
        assertThatThrownBy(() -> new Appliance(null, null, null, null, null, null, null, null))
                .isInstanceOf(NullPointerException.class);
    }
}
