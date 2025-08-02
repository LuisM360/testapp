package com.test.test2.controller;

import com.test.test2.dto.CustomerRequest;
import com.test.test2.dto.CustomerResponse;
import com.test.test2.service.CustomerService;
import jakarta.validation.Valid; // For @Valid annotation
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST Controller
@RequestMapping("/api/customers") // Base path for all endpoints in this controller
public class CustomerController {

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
        CustomerResponse createdCustomer = customerService.createCustomer(request);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED); // 201 Created
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
}
