package com.test.test2.dto;

import com.test.test2.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentActivityDTO {
    private LocalDate date;
    private Double amount;
    private PaymentMethod method;
    private String customerName;
}
