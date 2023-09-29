package uy.com.curso.pizzahurt.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = DomicilioValidator.class)
public @interface DomicilioConstraint {

    String message() default "Faltan datos en el domicilio o no son correctos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
