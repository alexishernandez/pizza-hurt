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
@Constraint(validatedBy = DomicilioPedidoValidator.class)
public @interface DomicilioPedidoConstraint {

    String message() default "Error en Domicilio: Faltan datos o no están completos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
