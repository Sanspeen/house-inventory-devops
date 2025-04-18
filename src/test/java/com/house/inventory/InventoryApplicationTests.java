package com.house.inventory;

import com.house.inventory.service.AzureServiceBusSender;
import com.house.inventory.subscriber.AzureServiceBusReceiver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ComponentScan(
        basePackages = "com.house.inventory",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AzureServiceBusSender.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AzureServiceBusReceiver.class)
        }
)
class InventoryApplicationTests {
    @MockBean
    private AzureServiceBusSender azureServiceBusSender;

    @MockBean
    private AzureServiceBusReceiver azureServiceBusReceiver;

    @Test
    void contextLoads() {
    }
}