package com.meditrack.billingservice.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Topic;

@Service
public class BillingProcessor {

    private final JmsTemplate topicJms;
    private final Topic notificationTopic;

    public BillingProcessor(JmsTemplate topicJms, Topic notificationTopic) {
        this.topicJms = topicJms;
        this.notificationTopic = notificationTopic;
    }

    @JmsListener(destination = "appointment.queue", containerFactory = "queueListenerFactory")
    public void handleAppointment(String payload) {
        System.out.println("🔁 BillingService received appointment payload: " + payload);
        // Simulate bill generation
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        String msg = "Bill generated for " + payload;
        topicJms.convertAndSend(notificationTopic, msg);
        System.out.println("✅ BillingService published: " + msg);
    }
}
