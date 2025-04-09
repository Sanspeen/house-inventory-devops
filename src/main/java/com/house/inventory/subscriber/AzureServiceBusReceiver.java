package com.house.inventory.subscriber;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.inventory.model.Appliance;
import com.house.inventory.service.ApplianceService;
import com.house.inventory.service.AzureServiceBusSender;
import com.house.inventory.service.FurnitureService;
import com.house.inventory.service.PlantService;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ApplianceService applianceService;
    @Autowired
    FurnitureService furnitureService;
    @Autowired
    PlantService plantService;

    @Autowired
    AzureServiceBusSender azureServiceBusSender;

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
                        messageMap.put("appliance", applianceService.generateGenericAppliance());
                        messageMap.put("furniture", furnitureService.generateGenericFurniture());
                        messageMap.put("plant", plantService.generateGenericPlant());
                        System.out.println("PROCESSED JSON: " + messageMap);
                        String msToSend = String.valueOf(messageMap.get("sendTo"));
                        String enrichedJson = objectMapper.writeValueAsString(messageMap);

                        switch (msToSend){
                            case "microservice1" -> redirectTo(enrichedJson, source, "microservice1");
                            case "microservice2" -> redirectTo(enrichedJson, source, "microservice2");
                            case "microservice3" -> redirectTo(enrichedJson, source, "microservice3");
                            case "coordinator" -> redirectTo(enrichedJson, source, "coordinator");
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

    private void redirectTo(String dynamicJsonString, String source, String destination) throws JsonProcessingException {
        azureServiceBusSender.sendMessage(dynamicJsonString, source, destination);
    }
}
