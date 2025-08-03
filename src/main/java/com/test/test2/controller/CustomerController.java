package com.test.test2.controller;

import com.test.test2.dto.CustomerRequest;
import com.test.test2.dto.CustomerResponse;
import com.test.test2.exception.ResourceNotFoundException;
import com.test.test2.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // Base path for all endpoints in this controller
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class); // Logger instance
    private final CustomerService customerService;

    // Constructor Injection for CustomerService
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Handles POST requests to create a new customer.
     * URL: POST /api/customers
     * @param request The CustomerRequest DTO containing customer data.
     * @return ResponseEntity with the created CustomerResponse and HTTP Status 201 Created.
     */
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        logger.info("Received request to create customer: {}", request.getName()); // Log creation attempt
        CustomerResponse createdCustomer = customerService.createCustomer(request);
        logger.info("Successfully created customer with ID: {}", createdCustomer.getId()); // Log success
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve a customer by their ID.
     * URL: GET /api/customers/{id}
     * @param id The ID of the customer to retrieve.
     * @return ResponseEntity with the CustomerResponse and HTTP Status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer); // 200 OK
    }

    /**
     * Handles GET requests to retrieve all customers.
     * URL: GET /api/customers
     * @return ResponseEntity with a list of CustomerResponse and HTTP Status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers); // 200 OK
    }

    /**
     * Handles PUT requests to update an existing customer.
     * URL: PUT /api/customers/{id}
     * @param id The ID of the customer to update.
     * @param request The CustomerRequest DTO with updated customer data.
     * @return ResponseEntity with the updated CustomerResponse and HTTP Status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(updatedCustomer); // 200 OK
    }

    /**
     * Handles DELETE requests to delete a customer by their ID.
     * URL: DELETE /api/customers/{id}
     * @param id The ID of the customer to delete.
     * @return ResponseEntity with no content and HTTP Status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

    @GetMapping("/log-demo")
    public ResponseEntity<String> logDemoEndpoint(@RequestParam(required = false) String level) {
        logger.debug("Debug message from /log-demo");
        logger.info("Info message from /log-demo");
        logger.warn("Warn message from /log-demo, level param: {}", level);

        if ("error".equalsIgnoreCase(level)) {
            logger.error("Error message from /log-demo due to request parameter 'level=error'");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Triggered an ERROR log.");
        } else if ("exception".equalsIgnoreCase(level)) {
            try {
                throw new RuntimeException("Simulated exception for logging demo!");
            } catch (Exception e) {
                logger.error("Caught and logged a simulated exception:", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Triggered an EXCEPTION log.");
            }
        }
        logger.info("Finished processing /log-demo");
        return ResponseEntity.ok("Check console for various log levels. Use ?level=error or ?level=exception for more.");
    }

    /**
     * Simulates a database-related error by trying to retrieve a non-existent customer.
     * This will trigger a ResourceNotFoundException which is handled by your GlobalExceptionHandler.
     */
    @GetMapping("/simulate-db-error")
    public ResponseEntity<String> simulateDbError() {
        logger.warn("Received request to simulate a database-related error.");
        // Use an ID that will definitely not exist
        long nonExistentId = Long.MAX_VALUE;
        try {
            customerService.getCustomerById(nonExistentId);
            return ResponseEntity.ok("Unexpected: No error occurred for non-existent ID. Check application state.");
        } catch (ResourceNotFoundException e) {
            logger.error("Simulated DB error successfully triggered by trying to get ID {}: {}", nonExistentId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Simulated database error by attempting to find a non-existent customer.");
        } catch (Exception e) {
            logger.error("An unexpected exception occurred during DB error simulation: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred during simulation.");
        }
    }

    /**
     * Simulates a NullPointerException within the application logic.
     */
    @GetMapping("/simulate-null-pointer")
    public ResponseEntity<String> simulateNullPointerException() {
        logger.warn("Received request to simulate a NullPointerException.");
        String nullString = null;
        try {
            nullString.length(); // This will throw NullPointerException
            return ResponseEntity.ok("Unexpected: NullPointerException did not occur.");
        } catch (NullPointerException e) {
            logger.error("Simulated NullPointerException successfully triggered: ", e);
            // The GlobalExceptionHandler will catch this as a generic Exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Simulated NullPointerException. Check logs for stack trace.");
        }
    }

}
