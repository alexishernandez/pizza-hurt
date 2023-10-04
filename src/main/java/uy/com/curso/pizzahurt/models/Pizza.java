package uy.com.curso.pizzahurt.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Pizza extends AbstractEntity {

    @NotNull
    @NotBlank(message = "El campo nombre no puede ser vacío")
    @Size(min=6, max=40, message = "El nombre debe contener entre {min} y {max} caracteres")
    private String	nombre;

    private Integer	precio;

    @NotNull
    @ManyToMany
    private List<Ingrediente> masa = new LinkedList<>();

    @NotNull
    @Size(min=1, message="La pizza debe tener al menos 1 salsa")
    @ManyToMany
    private List<Ingrediente> salsas = new LinkedList<>();
    
    
    @NotNull
    @Size(min=1, message="La pizza debe tener al menos 1 queso")
    @ManyToMany
    private List<Ingrediente> quesos = new LinkedList<>();
    
    
    @NotNull
    @ManyToMany
    private List<Ingrediente> toppings = new LinkedList<>();
    
    

}
