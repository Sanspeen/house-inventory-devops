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

    @Value("${azure.servicebus.topic-name}")
    private String topicName;

    public void sendMessage(String messageBody, String source, String destination) {
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .topicName(topicName)
                .buildClient();

        ServiceBusMessage message = new ServiceBusMessage(messageBody);
        message.setSessionId(destination); // Important for ordered delivery
        message.getApplicationProperties().put("source", source);
        message.getApplicationProperties().put("destination", destination);

        senderClient.sendMessage(message);
        System.out.println("Sent ordered message from " + source + " to " + destination + ": " + messageBody);

        senderClient.close();
    }
}


