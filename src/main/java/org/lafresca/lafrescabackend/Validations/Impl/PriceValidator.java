package org.lafresca.lafrescabackend.Validations.Impl;

import org.lafresca.lafrescabackend.Validations.ValidPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<ValidPrice, Double> {

    @Override
    public boolean isValid(Double price, ConstraintValidatorContext context) {
        if (price == null) {
            // Disable default violation and set custom message for null price
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price cannot be null")
                    .addConstraintViolation();
            return false;
        }

        if (price <= 0) {
            // Disable default violation and set custom message for negative price
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price should be positive")
                    .addConstraintViolation();
            return false;
        }

        // If price is valid, return true
        return true;
    }
}
