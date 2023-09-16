package uy.com.curso.pizzahurt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.entities.common.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {

    //Usuario que realiza pedido
    @ManyToOne
    private Usuario usuario;

    //Lugar de entrega
    private String nombreReceptor;
    private String ciudad;
    private String barrio;
    private String direccion;

    //Medio de Pago
    private String nroTarjeta;
    private String fechaVencimiento;
    private String codigoCVV;

    //ordenes
    @OneToMany
    private List<Pizza> pizzas = new ArrayList<>();
}
