package com.house.inventory.subscriber;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.inventory.model.Appliance;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AzureServiceBusReceiver {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.topic-name}")
    private String topicName;

    @Value("${azure.servicebus.subscription-name}")
    private String subscriptionName;

    private final ObjectMapper objectMapper;

    public AzureServiceBusReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void startReceiver() {
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .topicName(topicName)
                .subscriptionName(subscriptionName)
                .processMessage(context -> {
                    String json = context.getMessage().getBody().toString();
                    String source = (String) context.getMessage().getApplicationProperties().get("source");
                    String destination = (String) context.getMessage().getApplicationProperties().get("destination");
                    System.out.printf("Message received in %s from %s to %s: %s%n", subscriptionName, source, destination, json);

                    try {
                        Map<String, Object> messageMap = objectMapper.readValue(json, Map.class);
                        System.out.println("PROCESSED JSON: " + messageMap);
                        String msToSend = String.valueOf(messageMap.get("sendTo"));
                        switch (msToSend){
                            case "microservice1" -> System.out.println("REDIRECTED TO MS1");
                            case "microservice2" -> System.out.println("REDIRECTED TO MS2");
                            case "microservice3" -> System.out.println("REDIRECTED TO MS3");
                            case "coordinator" -> System.out.println("REDIRECTED TO COORDINATOR");
                            default -> System.out.println("IDK THIS MS");
                        }
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

    @PostConstruct
    public void startReceiverMS1() {
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .topicName(topicName)
                .subscriptionName(subscriptionName)
                .processMessage(context -> {
                    String json = context.getMessage().getBody().toString();
                    String source = (String) context.getMessage().getApplicationProperties().get("source");
                    String destination = (String) context.getMessage().getApplicationProperties().get("destination");

                    System.out.printf("Message received in %s from %s to %s: %s%n", subscriptionName, source, destination, json);

                    try {
                        Map<String, Object> messageMap = objectMapper.readValue(json, Map.class);
                        System.out.println("PROCESSED JSON: " + messageMap);
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
