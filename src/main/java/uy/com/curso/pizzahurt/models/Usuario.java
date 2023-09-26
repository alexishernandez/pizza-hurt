package uy.com.curso.pizzahurt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;
import uy.com.curso.pizzahurt.validators.EmailUniqueConstraint;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends AbstractEntity {

    @NotNull
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombreCompleto;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    private String telefono;

    private String password;

    private Boolean activo;

    //domicilio del usuario
    @NotNull
    @NotBlank
    private String ciudad;

    @NotNull
    @NotBlank
    private String barrio;

    @NotNull
    @NotBlank
    private String calle;

    @NotNull
    @NotBlank
    private  String nroPuerta;

    private String apto;

    @NotNull
    @NotBlank
    private String codigoPostal;

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