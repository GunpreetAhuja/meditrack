package com.meditrack.appointmentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.jms.Queue;
import jakarta.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final JmsTemplate queueJms;
    private final Queue appointmentQueue;
    private final JmsTemplate topicJms;
    private final Topic notificationTopic;

    public AppointmentController(JmsTemplate queueJms, Queue appointmentQueue, JmsTemplate topicJms, Topic notificationTopic) {
        this.queueJms = queueJms;
        this.appointmentQueue = appointmentQueue;
        this.topicJms = topicJms;
        this.notificationTopic = notificationTopic;
    }

    @PostMapping("/book")
    public ResponseEntity<Map<String,Object>> book(@RequestParam Long patientId, @RequestParam Long doctorId, @RequestParam String time) {
        Map<String,Object> payload = new HashMap<>();
        payload.put("patientId", patientId);
        payload.put("doctorId", doctorId);
        payload.put("time", time);
        // enqueue for downstream billing/processing
        queueJms.convertAndSend(appointmentQueue, payload.toString());
        // publish notification to topic
        topicJms.convertAndSend(notificationTopic, "Appointment booked for patient " + patientId + " at " + time);
        Map<String,Object> resp = new HashMap<>();
        resp.put("status", "queued");
        resp.put("details", payload);
        return ResponseEntity.accepted().body(resp);
    }
}
