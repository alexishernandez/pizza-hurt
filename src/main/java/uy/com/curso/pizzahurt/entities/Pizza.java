package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.entities.common.AbstractEntity;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends AbstractEntity {

    @NotNull
    private String	nombre;

    private Integer	precio;

    @NotNull
    @Size(min=1, message="La pizza debe tener al menos 1 ingrediente")
    @ManyToMany
    private List<Ingrediente> ingredientes = new LinkedList<>();

}
