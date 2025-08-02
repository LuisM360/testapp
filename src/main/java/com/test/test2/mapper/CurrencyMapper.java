package com.test.test2.mapper;

import com.test.test2.util.CurrencyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * Global MapStruct mapper for currency conversions between Double and Long (cents).
 * Other mappers can "use" this mapper for consistent currency handling.
 */
@Mapper(componentModel = "spring") // Make this a Spring component
public interface CurrencyMapper {

    @Named("doubleToCents")
    default Long doubleToCents(Double amount) {
        return CurrencyConverter.toCents(amount);
    }

    @Named("centsToDouble")
    default Double centsToDouble(Long amountInCents) {
        return CurrencyConverter.fromCents(amountInCents);
    }
}
