package com.meditrack.notificationservice.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationSubscriber {

    @JmsListener(destination = "meditrack.notifications", containerFactory = "topicListenerFactory")
    public void onNotification(String msg) {
        System.out.println("🔔 NotificationService received: " + msg);
    }
}
