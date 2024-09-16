package org.lafresca.lafrescabackend.Validations.Impl;

import org.lafresca.lafrescabackend.Validations.ValidAmount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<ValidAmount, Double> {

    @Override
    public boolean isValid(Double amount, ConstraintValidatorContext context) {
        if (amount == null) {
            // Disable default violation and set custom message for null amount
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Amount cannot be null")
                    .addConstraintViolation();
            return false;
        }

        if (amount <= 0) {
            // Disable default violation and set custom message for negative amount
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Amount should be positive")
                    .addConstraintViolation();
            return false;
        }

        // If price is valid, return true
        return true;
    }
}
