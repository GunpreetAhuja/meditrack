package com.meditrack.appointmentservice;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AppointmentIntegrationTest {
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").withDatabaseName("testdb").withUsername("postgres").withPassword("password");

    @Test
    void contextLoads() { /* use WebTestClient/RestTemplate to call endpoints */ }
}
