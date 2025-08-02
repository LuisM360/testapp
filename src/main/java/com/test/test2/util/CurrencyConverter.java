package com.test.test2.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for converting between monetary values represented as Doubles (dollars and cents)
 * and Longs (cents for database storage), handling precision.
 */
public class CurrencyConverter {

    private static final int SCALE = 2; // Number of decimal places for currency
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP; // Standard rounding mode

    /**
     * Converts a Double value (e.g., 123.45) representing dollars and cents to a Long value
     * representing cents (e.g., 12345).
     *
     * @param amount The monetary amount as a Double.
     * @return The amount in cents as a Long.
     * @throws IllegalArgumentException if the amount is null.
     */
    public static Long toCents(Double amount) {
        if (amount == null) {
            // Or return null, depending on desired behavior for nullable amounts.
            // For now, let's assume non-null input for conversion or handle nulls before calling this.
            throw new IllegalArgumentException("Amount cannot be null for conversion to cents.");
        }
        // Use BigDecimal for accurate arithmetic to avoid floating-point issues
        return BigDecimal.valueOf(amount)
                .setScale(SCALE, ROUNDING_MODE) // Ensure 2 decimal places with proper rounding
                .multiply(BigDecimal.valueOf(100))
                .longValue();
    }

    /**
     * Converts a Long value representing cents (e.g., 12345) to a Double value
     * representing dollars and cents (e.g., 123.45).
     *
     * @param amountInCents The monetary amount in cents as a Long.
     * @return The amount in dollars and cents as a Double.
     * @throws IllegalArgumentException if the amountInCents is null.
     */
    public static Double fromCents(Long amountInCents) {
        if (amountInCents == null) {
            // Or return null, depending on desired behavior for nullable amounts.
            // For now, let's assume non-null input for conversion or handle nulls before calling this.
            throw new IllegalArgumentException("Amount in cents cannot be null for conversion from cents.");
        }
        // Use BigDecimal for accurate arithmetic and then convert to Double
        // It's generally better to return BigDecimal from this, but since DTOs use Double,
        // we'll stick to Double here, assuming precision needs are met.
        return BigDecimal.valueOf(amountInCents)
                .divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE)
                .doubleValue();
    }

    /**
     * Converts a Long value representing cents to a String formatted as currency (e.g., "$123.45").
     * This is useful for direct display, especially in the PDF generation.
     *
     * @param amountInCents The monetary amount in cents as a Long.
     * @return The formatted currency string.
     */
    public static String formatCentsToDollars(Long amountInCents) {
        if (amountInCents == null) {
            return "$0.00"; // Or throw an exception, depending on desired behavior
        }
        BigDecimal dollars = BigDecimal.valueOf(amountInCents)
                .divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
        return String.format("$%,.2f", dollars); // Formats with comma for thousands and 2 decimal places
    }

    /**
     * Converts a Double value to a String formatted as currency (e.g., "$123.45").
     *
     * @param amount The monetary amount as a Double.
     * @return The formatted currency string.
     */
    public static String formatDoubleToDollars(Double amount) {
        if (amount == null) {
            return "$0.00";
        }
        BigDecimal dollars = BigDecimal.valueOf(amount).setScale(SCALE, ROUNDING_MODE);
        return String.format("$%,.2f", dollars);
    }
}
