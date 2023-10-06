package uy.com.curso.pizzahurt.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
import uy.com.curso.pizzahurt.validators.*;

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
    @NotNull(message = "El campo Nombre no puede estar vacío")
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String	nombreReceptor;

    @NotNull(message = "El campo Ciudad no puede estar vacío")
    private String	ciudad;

    @NotNull(message = "El campo Barrio no puede estar vacío")
    private String	barrio;

    @NotNull(message = "El campo Calle no puede estar vacío")
    private String	calle;

    @NotNull(message = "El campo Nro. de Puerta no puede estar vacío")
    private String	nroPuerta;

    private String	apto;

    @NotNull(message = "El campo Código Postal no puede estar vacío")
    @Range(min=10000, max=99999,message = "El Código Postal debe tener 5 digitos")
    private Integer codigoPostal;


    private String	observaciones;

    //Medio de Pago
    @NotNull(message = "El campo Emisor no puede estar vacío")
    private String emisor;

    @NotNull(message = "El campo Nombre no puede estar vacío")
    private String nombreTarjeta;

    @NotNull(message = "El campo Nro. de Tarjeta no puede estar vacío")
    @CreditCardNumber(message="El campo Nro. de tarjeta no es válido")
    private String nroTarjeta;

    @NotNull(message = "El campo Fecha de Vencimiento no puede estar vacío")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Fecha inválida, el formato correcto es: MM/YY")
    @VtoCreditCardConstraint(message = "La tarjeta está vencida")
    private String fechaVencimiento;

    @NotNull(message = "El campo CVV no puede estar vacío")
    @Pattern(regexp="^[0-9]{3}$", message="CódigoCVV invalido")
    private String codigoCVV;

    //ordenes
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pizza> pizzas = new ArrayList<>();
}
