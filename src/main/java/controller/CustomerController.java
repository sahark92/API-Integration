package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelclasses.Customer;
import service.ApiService;

@RestController
@RequestMapping("/api/assignment") // Adjust the mapping as needed
public class CustomerController {

    private final ApiService ApiService;

    @Autowired
    public CustomerController(ApiService apiService) {
        this.ApiService = apiService;
    }

    @PostMapping("/create")
     public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String bearerToken) {
        // Validate that first_name and last_name are provided
        if (customer.getFirst_name() == null || customer.getLast_name() == null) {
            return ResponseEntity.badRequest().body("First Name or Last Name is missing");
        }

        ResponseEntity<String> response = ApiService.createCustomer(customer, bearerToken);

        

        
        if (response.getStatusCode().is2xxSuccessful()) {
            // Successful response
            return ResponseEntity.status(response.getStatusCode()).body("Customer created successfully.");
        } else {
            // Handle other error cases
            return ResponseEntity.status(response.getStatusCode()).body("Failed to create customer.");
        }
    }
}

