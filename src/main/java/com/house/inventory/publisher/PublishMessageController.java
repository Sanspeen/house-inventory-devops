package com.house.inventory.publisher;

import com.house.inventory.model.MessageRequest;
import com.house.inventory.service.AzureServiceBusSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/messages")
public class PublishMessageController {
    private final AzureServiceBusSender sender;

    public PublishMessageController(AzureServiceBusSender sender) {
        this.sender = sender;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> postMessage(@RequestBody MessageRequest request,
                                              @RequestHeader("X-Source") String source,
                                              @RequestHeader("X-Destination") String destination) {
        sender.sendMessage(request.getMessage(), source, destination);
        return ResponseEntity.ok("Message sent from " + source + " to " + destination);
    }
}
