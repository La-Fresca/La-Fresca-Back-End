package org.lafresca.lafrescabackend.Validations;

import org.lafresca.lafrescabackend.Validations.Impl.PriceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPrice {
    String message() default "Invalid price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
