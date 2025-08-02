package com.test.test2.dto;

import com.test.test2.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    // For POST, invoiceId is often part of the URL path (e.g., /api/invoices/{invoiceId}/payments)
    // private Long invoiceId;
    // For POST, customerId will be derived from invoiceId in service layer.

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    @NotNull(message = "Amount paid is required")
    @Positive(message = "Amount paid must be a positive value")
    private Double amountPaid; // DTO uses Double, convert to cents

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @Size(max = 50, message = "Check number cannot exceed 50 characters")
    private String checkNumber;

    @Size(max = 100, message = "Transaction ID cannot exceed 100 characters")
    private String transactionId;

    @Size(max = 1000, message = "Payment notes cannot exceed 1000 characters")
    private String notes;
}
