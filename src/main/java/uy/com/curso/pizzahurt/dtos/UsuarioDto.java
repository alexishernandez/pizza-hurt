package uy.com.curso.pizzahurt.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import uy.com.curso.pizzahurt.validators.DomicilioConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DomicilioConstraint
public class UsuarioDto {

    @NotNull
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombreCompleto;

    @Email
    @Column(unique = true)
    private String email;

    private String telefono;

    private String password;

    private Boolean activo;

    //domicilio del usuario
    private String ciudad;

    private String barrio;

    private String calle;

    private  String nroPuerta;

    private String apto;

    private String codigoPostal;

    private String observaciones;

    //Tarjeta del usuario
    private String emisor;

    private String nombreTarjeta;

    @CreditCardNumber(message="El nro de tarjeta no es válido")
    private String nroTarjeta;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Fecha inválida, el formato correcto es: MM/YY")
    private String fechaVencimiento;

    @Pattern(regexp="^[0-9]{3}$", message="CódigoCVV invalido")
    private String codigoCVV;

}
