package uy.com.curso.pizzahurt.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
    private String	nombre;

    private Integer	precio;
    
    @OneToOne
    @NotNull
    private Ingrediente masa;

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
