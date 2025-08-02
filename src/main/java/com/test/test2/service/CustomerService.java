package com.test.test2.service;

import com.test.test2.dto.CustomerRequest;
import com.test.test2.dto.CustomerResponse;
import com.test.test2.dto.ServiceLocationDTO;
import com.test.test2.exception.ResourceNotFoundException;
import com.test.test2.mapper.CustomerMapper;
import com.test.test2.mapper.ServiceLocationMapper;
import com.test.test2.model.Customer;
import com.test.test2.model.ServiceLocation;
import com.test.test2.repository.CustomerRepository;
import com.test.test2.repository.ServiceLocationRepository;
import com.test.test2.util.CurrencyConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional; // For findById operations
import java.util.stream.Collectors;

@Service // Marks this class as a Spring Service component
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ServiceLocationRepository serviceLocationRepository; // Needed for nested locations
    private final CustomerMapper customerMapper;
    private final ServiceLocationMapper serviceLocationMapper;

    // Constructor Injection: Preferred way to inject dependencies
    public CustomerService(CustomerRepository customerRepository,
                           ServiceLocationRepository serviceLocationRepository,
                           CustomerMapper customerMapper,
                           ServiceLocationMapper serviceLocationMapper) {
        this.customerRepository = customerRepository;
        this.serviceLocationRepository = serviceLocationRepository;
        this.customerMapper = customerMapper;
        this.serviceLocationMapper = serviceLocationMapper;
    }

    @Transactional // Ensures the entire method runs in a single database transaction
    public CustomerResponse createCustomer(CustomerRequest request) {
        // 1. Convert DTO to Entity (CustomerMapper handles most fields, currency conversion)
        Customer customer = customerMapper.toEntity(request);

        // 2. Handle nested ServiceLocations if provided
        if (request.getServiceLocations() != null && !request.getServiceLocations().isEmpty()) {
            List<ServiceLocation> serviceLocations = request.getServiceLocations().stream()
                    .map(dto -> {
                        ServiceLocation location = serviceLocationMapper.toEntity(dto);
                        location.setCustomer(customer); // Set the parent customer relationship
                        return location;
                    })
                    .collect(Collectors.toList());
            customer.setServiceLocations(serviceLocations);
        }

        // 3. Save the Customer entity (cascades save to ServiceLocations if configured)
        Customer savedCustomer = customerRepository.save(customer);

        // 4. Convert the saved Entity back to Response DTO
        return customerMapper.toResponse(savedCustomer);
    }

    @Transactional(readOnly = true) // Optimize for read-only operations
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return customerMapper.toResponse(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toResponseList(customers);
    }

    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        // 1. Update main Customer fields using mapper's update method
        customerMapper.updateCustomerFromRequest(request, existingCustomer);

        // 2. Handle nested ServiceLocations update
        //    a) Clear existing children: This will trigger orphan removal for existing items.
        //       It must be done on the *managed collection itself*.
        existingCustomer.getServiceLocations().clear();

        //    b) Add new/updated children: ADD THEM DIRECTLY TO THE MANAGED COLLECTION.
        if (request.getServiceLocations() != null && !request.getServiceLocations().isEmpty()) {
            request.getServiceLocations().forEach(dto -> {
                ServiceLocation location = serviceLocationMapper.toEntity(dto);
                location.setCustomer(existingCustomer); // Link to parent
                existingCustomer.getServiceLocations().add(location); // ADD TO EXISTING MANAGED LIST
            });
        }
        // No need for existingCustomer.setServiceLocations(updatedServiceLocations); here

        Customer updatedCustomer = customerRepository.save(existingCustomer); // Saving the parent entity
        return customerMapper.toResponse(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id); // Cascades delete to child entities
    }
}
