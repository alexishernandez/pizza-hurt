package uy.com.curso.pizzahurt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;

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

    private String telefono;

    private String password;

    private Boolean activo;

    //domicilio del usuario
    private String ciudad;

    private String barrio;

    private String direccion;

    //Tarjeta del usuario
    private String nroTarjeta;

    private String fechaVencimiento;

    @Digits(integer=3, fraction=0, message="Código CVV inválido")
    private String codigoCVV;

}