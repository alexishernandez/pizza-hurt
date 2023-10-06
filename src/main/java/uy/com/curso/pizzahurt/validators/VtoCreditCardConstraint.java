package uy.com.curso.pizzahurt.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = VtoCreditCardValidator.class)
public @interface VtoCreditCardConstraint {

    String message() default "La tarjeta se encuentra vencida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
