package com.test.test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetricsResponse {
    private Double totalUnpaidInvoices; // Sum of outstanding balances
    private Long activeCustomersCount;
    private Double currentMonthPayments; // Sum of payments for current month
    private Long overdueCustomersCount;

    private List<RecentActivityDTO> recentActivity; // Nested DTO for recent payments
}
