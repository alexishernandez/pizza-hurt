package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.entities.common.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends AbstractEntity {

    private String nombreCompleto;
    @Email
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
    private String codigoCVV;

}
