package com.house.inventory;

import com.house.inventory.service.AzureServiceBusSender;
import com.house.inventory.subscriber.AzureServiceBusReceiver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class InventoryApplicationTests {

    @MockBean
    private AzureServiceBusSender azureServiceBusSender;
    @MockBean
    private AzureServiceBusReceiver azureServiceBusReceiver;

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodTest() {
        InventoryApplication.main(new String[] {}); // Ejecuta el main
    }
}