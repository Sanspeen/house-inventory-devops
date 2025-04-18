package com.house.inventory;

import com.house.inventory.service.AzureServiceBusSender;
import com.house.inventory.subscriber.AzureServiceBusReceiver;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockedBeansConfig {
    @Bean
    public AzureServiceBusReceiver azureServiceBusReceiver() {
        return Mockito.mock(AzureServiceBusReceiver.class);
    }

    @Bean
    public AzureServiceBusSender azureServiceBusSender() {
        return Mockito.mock(AzureServiceBusSender.class);
    }
}
