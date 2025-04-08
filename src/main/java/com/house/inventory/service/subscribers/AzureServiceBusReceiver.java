package com.house.inventory.service.subscribers;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureServiceBusReceiver {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.queue-name}")
    private String queueName;

    @PostConstruct
    public void receiveMessages() {
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(context -> {
                    String body = context.getMessage().getBody().toString();
                    System.out.println("Received message: " + body);
                })
                .processError(context -> {
                    System.err.println("Error occurred while receiving message: " + context.getException());
                })
                .buildProcessorClient();

        processorClient.start();
    }
}