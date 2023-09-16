package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import modelclasses.Customer;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createCustomer(Customer customer, String bearerToken) {
        ApiService apiService = new ApiService(new RestTemplate()); // Create an instance of ApiService
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);
    
        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
    
        ResponseEntity<String> response = apiService.restTemplate.exchange(
            "https://qa2sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create",
            HttpMethod.POST,
            requestEntity,
            String.class
        );
    
        return response;
    }
    
}

