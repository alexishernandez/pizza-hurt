package uy.com.curso.pizzahurt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Usuario;

public class DomicilioPedidoValidator implements ConstraintValidator<DomicilioPedidoConstraint, Pedido> {
    @Override
    public void initialize(DomicilioPedidoConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Pedido domicilio, ConstraintValidatorContext context) {
        boolean isEmptyDomicilioBase=(domicilio.getCiudad() == null || domicilio.getCiudad().isEmpty()) &&
                (domicilio.getBarrio() == null || domicilio.getBarrio().isEmpty()) &&
                (domicilio.getCalle()  == null || domicilio.getCalle().isEmpty()) &&
                (domicilio.getNroPuerta()== null ||domicilio.getNroPuerta().isEmpty()) &&
                (domicilio.getCodigoPostal() == null || domicilio.getCodigoPostal() == 0);
        boolean isFullDomicilioBase= (domicilio.getCiudad() != null && !domicilio.getCiudad().isEmpty()) &&
                (domicilio.getBarrio() != null && !domicilio.getBarrio().isEmpty()) &&
                (domicilio.getCalle()  != null && !domicilio.getCalle().isEmpty()) &&
                (domicilio.getNroPuerta() != null && !domicilio.getNroPuerta().isEmpty()) &&
                (domicilio.getCodigoPostal() != null && !(domicilio.getCodigoPostal()==0));

        boolean isEmptyApto = (domicilio.getApto() == null || domicilio.getApto().isEmpty());
        boolean isEmptyObservaciones = (domicilio.getObservaciones() == null || domicilio.getObservaciones().isEmpty());

        if (isFullDomicilioBase || (isEmptyDomicilioBase && isEmptyApto && isEmptyObservaciones)){
            return true;
        } else
         return false;
    }
}
