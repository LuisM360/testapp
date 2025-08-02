package com.test.test2.dto;

import com.test.test2.enums.InvoiceDisplayPreference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Email(message = "Email must be a valid email address")
    @Size(max = 120, message = "Email cannot exceed 120 characters")
    private String email;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phoneNumber;

    @NotBlank(message = "Primary street address is required")
    @Size(max = 200, message = "Street address cannot exceed 200 characters")
    private String streetAddress;

    @NotBlank(message = "Primary city is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @NotBlank(message = "Primary state is required")
    @Size(max = 2, message = "State must be 2 characters (e.g., FL)")
    private String state;

    @NotBlank(message = "Primary zip code is required")
    @Size(max = 10, message = "Zip code cannot exceed 10 characters")
    private String zipCode;

    @NotNull(message = "Monthly rate is required")
    @PositiveOrZero(message = "Monthly rate must be a non-negative value")
    private Double monthlyRate; // DTO uses Double (e.g., 200.00), convert to cents in service layer

    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes;

    private Boolean isActive = true; // Default to true

    @NotNull(message = "Invoice display preference is required")
    private InvoiceDisplayPreference invoiceDisplayPreference;

    @Size(max = 2000, message = "Custom bill to text cannot exceed 2000 characters")
    private String customBillToText;

    @Valid // Validates nested ServiceLocationDTOs
    private List<ServiceLocationDTO> serviceLocations;

}
