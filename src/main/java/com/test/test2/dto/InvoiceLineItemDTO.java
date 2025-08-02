package com.test.test2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineItemDTO {

    private Long id;

    private Long serviceLocationId;

    @NotNull(message = "Month billed is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    private Integer monthBilled;

    @NotNull(message = "Year billed is required")
    private Integer yearBilled;

    @NotNull(message = "Number of services is required")
    @PositiveOrZero(message = "Number of services must be non-negative")
    private Integer numberOfServices; // From UI (e.g., 1, 2, 3, 4)

    @NotBlank(message = "Item summary text is required")
    @Size(max = 1000, message = "Item summary text cannot exceed 1000 characters")
    private String itemSummaryText; // "From: January 1 - January 30 (4 services)"

    @NotNull(message = "Amount charged is required")
    @PositiveOrZero(message = "Amount charged must be a non-negative value")
    private Double amountCharged;

}
