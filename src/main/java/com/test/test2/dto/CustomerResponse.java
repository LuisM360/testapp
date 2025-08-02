package com.test.test2.dto;
import com.test.test2.enums.InvoiceDisplayPreference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private Double monthlyRate; // DTO uses Double, convert from cents for client
    private String notes;
    private Boolean isActive;
    private InvoiceDisplayPreference invoiceDisplayPreference;
    private String customBillToText;
    private LocalDateTime createdAt;
    private List<ServiceLocationDTO> serviceLocations;

}
