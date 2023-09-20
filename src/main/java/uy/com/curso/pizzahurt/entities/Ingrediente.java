package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import uy.com.curso.pizzahurt.entities.common.AbstractEntity;
import uy.com.curso.pizzahurt.enums.TipoIngrediente;

@Entity
@Data
@AllArgsConstructor
public class Ingrediente extends AbstractEntity {

    @NotNull
    @Size(min=5, max=20, message="El nombre del ingrediente debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoIngrediente tipoIngrediente;

}
