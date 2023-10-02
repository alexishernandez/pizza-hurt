package uy.com.curso.pizzahurt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No se encuentra el pedido")  // 404
public class PedidoNotFoundException extends Exception{
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
