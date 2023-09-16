package com.sunbasedata.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import modelclasses.Customer;
import service.ApiService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApiServiceTest {

    private ApiService apiService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        apiService = new ApiService(restTemplate);
    }

    @Test
    void testCreateCustomer_Success() {
        // Arrange
        Customer customer = new Customer(); // Initialize a customer object
        String bearerToken = "your_bearer_token";

        ResponseEntity<String> successResponse = ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(successResponse);

        // Act
        ResponseEntity<String> response = apiService.createCustomer(customer, bearerToken);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Customer created successfully", response.getBody());
    }

    @Test
    void testCreateCustomer_Failure() {
        // Arrange
        Customer customer = new Customer(); // Initialize a customer object
        String bearerToken = "your_bearer_token";

        ResponseEntity<String> failureResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
            .thenReturn(failureResponse);

        // Act
        ResponseEntity<String> response = apiService.createCustomer(customer, bearerToken);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to create customer", response.getBody());
    }
}

