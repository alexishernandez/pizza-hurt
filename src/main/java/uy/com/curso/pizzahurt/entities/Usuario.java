package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
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
    private String email;
    private String telefono;
    private String password;
    private Boolean activo;

}
