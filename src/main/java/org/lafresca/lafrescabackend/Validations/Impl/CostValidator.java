package org.lafresca.lafrescabackend.Validations.Impl;

import org.lafresca.lafrescabackend.Validations.ValidCost;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CostValidator implements ConstraintValidator<ValidCost, Double> {

    @Override
    public boolean isValid(Double cost, ConstraintValidatorContext context) {
        if (cost == null) {
            // Disable default violation and set custom message for null cost
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Cost cannot be null")
                    .addConstraintViolation();
            return false;
        }

        if (cost < 0) {
            // Disable default violation and set custom message for negative cost
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Cost cannot be negative")
                    .addConstraintViolation();
            return false;
        }

        // If cost is valid, return true
        return true;
    }
}
