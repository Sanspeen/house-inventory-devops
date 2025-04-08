package com.house.inventory.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AzureServiceBusSender {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;
    @Value("${azure.servicebus.queue-name}")
    private String queueName;

    public void sendMessage(String jsonString) {
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        senderClient.sendMessage(new ServiceBusMessage(jsonString));
        System.out.println("Sent raw JSON message: " + jsonString);
        senderClient.close();
    }
}
