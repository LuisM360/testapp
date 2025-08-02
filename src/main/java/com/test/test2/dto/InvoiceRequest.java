package com.test.test2.dto;

import com.test.test2.enums.InvoiceStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    // For POST, customerId is often part of the URL path (e.g., /api/customers/{customerId}/invoices)
    // For PUT/PATCH, no customerId needed if already identified by InvoiceId
    // private Long customerId;

    private Long billingAddressLocationId; // Optional FK to ServiceLocation for specific billing address

    @NotBlank(message = "Invoice number is required")
    @Size(max = 50, message = "Invoice number cannot exceed 50 characters")
    private String invoiceNumber;

    @NotNull(message = "Invoice date is required")
    private LocalDate invoiceDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    // Totals are usually calculated server-side based on line items, but might be sent for initial proposals
    @NotNull(message = "Subtotal is required")
    @PositiveOrZero(message = "Subtotal must be a non-negative value")
    private Double subtotal; // DTO uses Double

    @NotNull(message = "Tax amount is required")
    @PositiveOrZero(message = "Tax amount must be a non-negative value")
    private Double taxAmount = 0.0;

    @NotNull(message = "Total amount is required")
    @PositiveOrZero(message = "Total amount must be a non-negative value")
    private Double totalAmount;

    @NotNull(message = "Amount paid is required")
    @PositiveOrZero(message = "Amount paid must be a non-negative value")
    private Double amountPaid = 0.0;

    @NotNull(message = "Balance due is required")
    @PositiveOrZero(message = "Balance due must be a non-negative value")
    private Double balanceDue;

    @NotNull(message = "Invoice status is required")
    private InvoiceStatus status;

    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes; // Corresponds to the "FOR:" field on the template

    @Valid // Validates nested InvoiceLineItemDTOs
    @NotNull(message = "Invoice must contain at least one line item")
    private List<InvoiceLineItemDTO> lineItems;
}
