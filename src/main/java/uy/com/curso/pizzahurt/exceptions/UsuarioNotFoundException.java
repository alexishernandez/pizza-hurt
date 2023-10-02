package uy.com.curso.pizzahurt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No se encuentra el usuario")  // 404
public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
