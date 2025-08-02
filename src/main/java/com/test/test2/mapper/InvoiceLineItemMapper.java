package com.test.test2.mapper;

import com.test.test2.dto.InvoiceLineItemDTO;
import com.test.test2.model.InvoiceLineItem;
import com.test.test2.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = CurrencyMapper.class)
public interface InvoiceLineItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true) // Invoice is set in service layer
    @Mapping(target = "serviceLocation", ignore = true) // ServiceLocation is set by ID in service layer
    @Mapping(target = "amountChargedCents", source = "amountCharged", qualifiedByName = "doubleToCents")
    InvoiceLineItem toEntity(InvoiceLineItemDTO dto);

    @Mapping(target = "serviceLocationId", source = "serviceLocation.id")
    @Mapping(target = "amountCharged", source = "amountChargedCents", qualifiedByName = "centsToDouble")
    InvoiceLineItemDTO toDto(InvoiceLineItem entity);

    List<InvoiceLineItemDTO> toDtoList(List<InvoiceLineItem> entities);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "serviceLocation", ignore = true)
    @Mapping(target = "amountChargedCents", source = "amountCharged", qualifiedByName = "doubleToCents")
    void updateLineItemFromDto(InvoiceLineItemDTO dto, @MappingTarget InvoiceLineItem entity);
}
