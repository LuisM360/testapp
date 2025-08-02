package com.test.test2.mapper;

import com.test.test2.dto.InvoiceResponse;
import com.test.test2.dto.InvoiceRequest;
import com.test.test2.model.Invoice;
import com.test.test2.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InvoiceLineItemMapper.class, CurrencyMapper.class})
public interface InvoiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true) // Customer is set in service layer
    @Mapping(target = "billingAddressLocation", ignore = true) // Set by ID in service layer
    @Mapping(target = "createdAt", ignore = true) // Handled by @CreationTimestamp
    @Mapping(target = "sentDate", ignore = true) // Set explicitly when sent
    @Mapping(target = "subtotalCents", source = "subtotal", qualifiedByName = "doubleToCents")
    @Mapping(target = "taxAmountCents", source = "taxAmount", qualifiedByName = "doubleToCents")
    @Mapping(target = "totalAmountCents", source = "totalAmount", qualifiedByName = "doubleToCents")
    @Mapping(target = "amountPaidCents", source = "amountPaid", qualifiedByName = "doubleToCents")
    @Mapping(target = "balanceDueCents", source = "balanceDue", qualifiedByName = "doubleToCents")
    Invoice toEntity(InvoiceRequest request);

    @Mapping(target = "customerId", source = "customer.id") // Map customer entity to its ID
    @Mapping(target = "billingAddressLocationId", source = "billingAddressLocation.id") // Map service location entity to its ID
    @Mapping(target = "subtotal", source = "subtotalCents", qualifiedByName = "centsToDouble")
    @Mapping(target = "taxAmount", source = "taxAmountCents", qualifiedByName = "centsToDouble")
    @Mapping(target = "totalAmount", source = "totalAmountCents", qualifiedByName = "centsToDouble")
    @Mapping(target = "amountPaid", source = "amountPaidCents", qualifiedByName = "centsToDouble")
    @Mapping(target = "balanceDue", source = "balanceDueCents", qualifiedByName = "centsToDouble")
    InvoiceResponse toResponse(Invoice invoice);

    List<InvoiceResponse> toResponseList(List<Invoice> invoices);



    // For updates, ignoring collections as they are usually managed separately
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "billingAddressLocation", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "sentDate", ignore = true)
    @Mapping(target = "lineItems", ignore = true) // Line items usually updated via separate endpoints or logic
    @Mapping(target = "payments", ignore = true) // Payments usually updated via separate endpoints or logic
    @Mapping(target = "subtotalCents", source = "subtotal", qualifiedByName = "doubleToCents")
    @Mapping(target = "taxAmountCents", source = "taxAmount", qualifiedByName = "doubleToCents")
    @Mapping(target = "totalAmountCents", source = "totalAmount", qualifiedByName = "doubleToCents")
    @Mapping(target = "amountPaidCents", source = "amountPaid", qualifiedByName = "doubleToCents")
    @Mapping(target = "balanceDueCents", source = "balanceDue", qualifiedByName = "doubleToCents")
    void updateInvoiceFromRequest(InvoiceRequest request, @MappingTarget Invoice invoice);
}