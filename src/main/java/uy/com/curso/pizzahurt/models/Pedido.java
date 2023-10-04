package uy.com.curso.pizzahurt.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;
import uy.com.curso.pizzahurt.validators.DomicilioConstraint;
import uy.com.curso.pizzahurt.validators.DomicilioPedidoConstraint;
import uy.com.curso.pizzahurt.validators.TarjetaConstraint;
import uy.com.curso.pizzahurt.validators.TarjetaPedidoConstraint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DomicilioPedidoConstraint
@TarjetaPedidoConstraint
public class Pedido extends AbstractEntity {

    //Usuario que realiza pedido
    @ManyToOne
    private Usuario usuario;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
    private Date fechaPedido;

    //Lugar de entrega
    @NotNull
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
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

    @Range(min=10000, max=99999,message = "El Código Postal debe tener 5 digitos")
    private Integer codigoPostal;


    private String	observaciones;

    //Medio de Pago
    @NotNull
    private String emisor;

    @NotNull
    @CreditCardNumber(message="El nro de tarjeta no es válido")
    private String nroTarjeta;

    @NotNull
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Fecha inválida, el formato correcto es: MM/YY")
    private String fechaVencimiento;

    @NotNull
    @Pattern(regexp="^[0-9]{3}$", message="CódigoCVV invalido")
    private String codigoCVV;

    //ordenes
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pizza> pizzas = new ArrayList<>();
}
