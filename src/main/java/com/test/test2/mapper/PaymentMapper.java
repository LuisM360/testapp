package com.test.test2.mapper;

import com.test.test2.dto.PaymentRequest;
import com.test.test2.dto.PaymentResponse;
import com.test.test2.model.Payment;
import com.test.test2.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = CurrencyMapper.class)
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true) // Invoice set in service layer
    @Mapping(target = "customer", ignore = true) // Customer set in service layer (from invoice)
    @Mapping(target = "amountCents", source = "amountPaid", qualifiedByName = "doubleToCents")
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "amountPaid", source = "amountCents", qualifiedByName = "centsToDouble")
    PaymentResponse toResponse(Payment payment);

    List<PaymentResponse> toResponseList(List<Payment> payments);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "amountCents", source = "amountPaid", qualifiedByName = "doubleToCents")
    void updatePaymentFromRequest(PaymentRequest request, @MappingTarget Payment payment);
}