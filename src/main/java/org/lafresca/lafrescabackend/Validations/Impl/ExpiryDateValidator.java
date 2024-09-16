package org.lafresca.lafrescabackend.Validations.Impl;

import org.lafresca.lafrescabackend.Validations.ValidExpiryDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ExpiryDateValidator implements ConstraintValidator<ValidExpiryDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate expiryDate, ConstraintValidatorContext context) {
        if (expiryDate == null) {
            // Disable default violation and set custom message for null expiry date
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Expiry date cannot be null")
                    .addConstraintViolation();
            return false;
        }

        if (LocalDate.parse(expiryDate.toString()).isBefore(LocalDate.now())) {
            // Disable default violation and set custom message for invalid expiry date
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid Expiry date")
                    .addConstraintViolation();
            return false;
        }

        // If price is valid, return true
        return true;
    }
}
