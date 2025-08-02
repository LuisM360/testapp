package com.test.test2.dto;

import com.test.test2.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private Long id;
    private Long customerId; // Just the ID is often sufficient for responses
    private Long billingAddressLocationId; // ID of the linked service location if any
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double subtotal;
    private Double taxAmount;
    private Double totalAmount;
    private Double amountPaid;
    private Double balanceDue;
    private InvoiceStatus status;
    private String notes;
    private LocalDateTime sentDate;
    private LocalDateTime createdAt;
    private List<InvoiceLineItemDTO> lineItems;
}
