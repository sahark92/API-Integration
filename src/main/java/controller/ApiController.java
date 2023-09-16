package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import modelclasses.AuthenticationRequest;
import modelclasses.AuthenticationResponse;

@RestController
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/assignment_auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        // Make a POST request to the authentication API
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<AuthenticationResponse> responseEntity =
            restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp",
                                 HttpMethod.POST, entity, AuthenticationResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Assuming the token is returned in the response
            String token = responseEntity.getBody().getToken();
            
            // Store the token securely or use it for subsequent API calls
            return ResponseEntity.ok("Bearer Token: " + token);
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Authentication failed.");
        }
    }

    // Add methods to make authenticated API calls here
}
