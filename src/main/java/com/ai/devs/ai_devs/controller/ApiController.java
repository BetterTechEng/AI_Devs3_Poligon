package com.ai.devs.ai_devs.controller;
import com.ai.devs.ai_devs.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
//@RequestMapping("")
public class ApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    String key;

    @Autowired
    private RestService restService;

    @GetMapping("/dane.txt")
    public String getTwoStrings(String[] strings) {

        System.out.println(strings[0]);
        return String.format("The key is: %s!", key);
    }

    @GetMapping("/getArrayStrings")
    public ResponseEntity<String[]> getHelloWorld() {
        return restService.getTwoStrings();
    }
    @PostMapping("/verify")
    public ResponseEntity getInputData() {
        return restService.postTwoStrings();
    }
    @GetMapping("/data")
    public ResponseEntity<Object> getData() {
        // Define the URL of the external service
        String externalServiceUrl = "https://example.com/external-data";

        // Make a request to the external service
        ResponseEntity<byte[]> response = restTemplate.exchange(
                externalServiceUrl,
                HttpMethod.GET,
                null,
                byte[].class
        );

        HttpHeaders headers = response.getHeaders();
        HttpStatusCode statusCode = response.getStatusCode();
        byte[] body = response.getBody();

        // Process the response based on content type
        String contentType = headers.getContentType() != null ? headers.getContentType().toString() : "";

        if (contentType.contains("text") || contentType.contains("json")) {
            return ResponseEntity
                    .status(statusCode)
                    .body(new String(body)); // Handle as a String
        } else if (contentType.contains("application/octet-stream")) {
            return ResponseEntity
                    .status(statusCode)
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(body); // Handle as a byte array (file content)
        } else if (contentType.contains("image") || contentType.contains("pdf")) {
            return ResponseEntity
                    .status(statusCode)
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(body); // Handle as file (binary data)
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Unsupported media type");
        }
    }
}
