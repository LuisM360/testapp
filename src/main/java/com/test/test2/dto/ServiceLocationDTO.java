package com.test.test2.dto;

import com.test.test2.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocationDTO {
    private Long id;

    @NotBlank(message = "Street address is required")
    @Size(max = 200, message = "Street address cannot exceed 200 characters")
    private String streetAddress;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 2, message = "State must be 2 characters (e.g., FL)")
    private String state;

    @NotBlank(message = "Zip Code is required")
    @Size(max = 10, message = "Zip Code cannot exceed 10 characters")
    private String zipCode;

    @Size(max = 1000, message = "Location notes cannot exceed 1000 characters")
    private String locationNotes;

    private Boolean isPrimaryAddress = false;

    private AddressType addressType;


}
