package com.house.inventory.subscriber;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AzureServiceBusReceiver {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.queue-name}")
    private String queueName;

    private final ObjectMapper objectMapper;

    public AzureServiceBusReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void startReceiver() {
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(context -> {
                    String json = context.getMessage().getBody().toString();
                    System.out.println("Raw message: " + json);

                    try {
                        // Dynamically parse JSON into Map
                        Map<String, Object> messageMap = objectMapper.readValue(json, Map.class);

                        System.out.println("PROCESSED JSON" + messageMap);

                    } catch (Exception e) {
                        System.err.println("Failed to parse JSON message: " + e.getMessage());
                    }

                })
                .processError(context -> {
                    System.err.println("Error in message processing: " + context.getException());
                })
                .buildProcessorClient();

        processorClient.start();
    }
}
