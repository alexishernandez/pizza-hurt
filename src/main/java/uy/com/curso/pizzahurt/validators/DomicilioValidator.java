package uy.com.curso.pizzahurt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.com.curso.pizzahurt.dtos.DomicilioDto;

public class DomicilioValidator  implements ConstraintValidator<DomicilioConstraint, DomicilioDto> {
    @Override
    public void initialize(DomicilioConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(DomicilioDto domicilio, ConstraintValidatorContext context) {
        if (domicilio.getCiudad() == null ^ domicilio.getBarrio() == null ^ domicilio.getCalle()== null ^
                domicilio.getNroPuerta()== null ^ domicilio.getApto()== null ^ domicilio.getCodigoPostal() == null ^
                domicilio.getObservaciones()== null) {
            return false;
        } else {
            return true;
        }
    }
}
