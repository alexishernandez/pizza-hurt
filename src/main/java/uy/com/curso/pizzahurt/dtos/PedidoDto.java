package uy.com.curso.pizzahurt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    private Long id;
    private String fecha;
    private String receptor;
    private String direccion;
    private String pizzas;
}
