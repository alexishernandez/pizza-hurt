package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import uy.com.curso.pizzahurt.entities.common.AbstractEntity;
import uy.com.curso.pizzahurt.enums.TipoIngrediente;

@Entity
@Data
@AllArgsConstructor
public class Ingrediente extends AbstractEntity {

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoIngrediente tipoIngrediente;

}
