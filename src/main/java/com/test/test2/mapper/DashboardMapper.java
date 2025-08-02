package com.test.test2.mapper;

import com.test.test2.dto.RecentActivityDTO;
import com.test.test2.model.Payment;
import com.test.test2.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DashboardMapper {

    // No direct entity to DashboardMetricsResponse mapping, as it's an aggregate.
    // This will be manually constructed in DashboardService.

    @Mapping(target = "amount", source = "amountCents", qualifiedByName = "centsToDouble")
    @Mapping(target = "customerName", source = "customer.name") // Assuming Payment has a customer association
    RecentActivityDTO toRecentActivityDto(Payment payment);

    List<RecentActivityDTO> toRecentActivityDtoList(List<Payment> payments);

    @Named("centsToDouble")
    default Double centsToDouble(Long amountInCents) {
        return CurrencyConverter.fromCents(amountInCents);
    }
}
