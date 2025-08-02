package com.test.test2.dto;

import com.test.test2.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long invoiceId;
    private Long customerId;
    private LocalDate paymentDate;
    private Double amountPaid; // DTO uses Double, convert from cents
    private PaymentMethod paymentMethod;
    private String checkNumber;
    private String transactionId;
    private String notes;
}