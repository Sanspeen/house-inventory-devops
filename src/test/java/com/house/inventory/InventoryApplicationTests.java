package com.house.inventory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class InventoryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodTest() {
        InventoryApplication.main(new String[] {}); // Ejecuta el main
    }
}