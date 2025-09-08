package com.meditrack.inventoryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.jms.Topic;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final JmsTemplate topicJms;
    private final Topic notificationTopic;

    public InventoryController(JmsTemplate topicJms, Topic notificationTopic) {
        this.topicJms = topicJms;
        this.notificationTopic = notificationTopic;
    }

    @PostMapping("/restock")
    public ResponseEntity<String> restock(@RequestParam String item, @RequestParam int qty) {
        String msg = "Restocked " + qty + " of " + item;
        topicJms.convertAndSend(notificationTopic, msg);
        return ResponseEntity.ok(msg);
    }
}
