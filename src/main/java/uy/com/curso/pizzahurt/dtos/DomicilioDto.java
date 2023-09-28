package uy.com.curso.pizzahurt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.validators.DomicilioConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DomicilioConstraint
public class DomicilioDto {

    private String ciudad;

    private String barrio;

    private String calle;

    private String nroPuerta;

    private String apto;

    private String codigoPostal;

    private String observaciones;
}
