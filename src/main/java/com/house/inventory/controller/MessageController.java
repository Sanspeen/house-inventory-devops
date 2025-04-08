package com.house.inventory.controller;

import com.house.inventory.model.MessageRequest;
import com.house.inventory.service.AzureServiceBusSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final AzureServiceBusSender sender;

    public MessageController(AzureServiceBusSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody MessageRequest request) {
        sender.sendMessage(request.getContent());
        return ResponseEntity.ok("Message sent");
    }
}