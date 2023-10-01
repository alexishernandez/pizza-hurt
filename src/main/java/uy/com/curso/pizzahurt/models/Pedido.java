package uy.com.curso.pizzahurt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {

    //Usuario que realiza pedido
    @ManyToOne
    private Usuario usuario;

    private Date fechaPedido;

    //Lugar de entrega
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    @NotNull
    private String	nombreReceptor;

    @NotNull
    private String	ciudad;

    @NotNull
    private String	barrio;

    @NotNull
    private String	calle;

    @NotNull
    private String	nroPuerta;

    @NotNull
    private String	apto;

    @NotNull
    private Integer codigoPostal;


    private String	observaciones;

    //Medio de Pago
    @NotNull
    private String emisor;

    @NotNull
    private String nroTarjeta;

    @NotNull
    private String fechaVencimiento;

    @NotNull
    @Digits(integer=3, fraction=0, message="Código CVV inválido")
    private String codigoCVV;

    //ordenes
    @OneToMany
    private List<Pizza> pizzas = new ArrayList<>();
}
