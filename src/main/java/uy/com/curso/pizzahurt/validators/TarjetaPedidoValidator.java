package uy.com.curso.pizzahurt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Usuario;

public class TarjetaPedidoValidator implements ConstraintValidator<TarjetaPedidoConstraint, Pedido> {
    @Override
    public void initialize(TarjetaPedidoConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Pedido tarjeta, ConstraintValidatorContext context) {
        boolean isEmptyDatosTarjeta=
                (tarjeta.getEmisor() == null || tarjeta.getEmisor().isEmpty()) &&
                (tarjeta.getNombreTarjeta() == null || tarjeta.getNombreTarjeta().isEmpty()) &&
                (tarjeta.getNroTarjeta()== null ||tarjeta.getNroTarjeta().isEmpty()) &&
                (tarjeta.getFechaVencimiento()  == null || tarjeta.getFechaVencimiento().isEmpty()) &&
                (tarjeta.getCodigoCVV() == null || tarjeta.getCodigoCVV().isEmpty());

        boolean isFullDatosTarjeta=
                (tarjeta.getEmisor() != null && !tarjeta.getEmisor().isEmpty()) &&
                (tarjeta.getNombreTarjeta() != null && !tarjeta.getNombreTarjeta().isEmpty()) &&
                (tarjeta.getNroTarjeta() != null && !tarjeta.getNroTarjeta().isEmpty()) &&
                (tarjeta.getFechaVencimiento() != null && !tarjeta.getFechaVencimiento().isEmpty()) &&
                (tarjeta.getCodigoCVV() != null && !tarjeta.getCodigoCVV().isEmpty());

        if (isEmptyDatosTarjeta || isFullDatosTarjeta){
            return true;
        } else
         return false;
    }
}
