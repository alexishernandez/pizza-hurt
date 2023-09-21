package uy.com.curso.pizzahurt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;
import uy.com.curso.pizzahurt.enums.TipoIngrediente;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente extends AbstractEntity {

    @NotNull
    @Size(min=5, max=20, message="El nombre del ingrediente debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoIngrediente tipoIngrediente;

}
