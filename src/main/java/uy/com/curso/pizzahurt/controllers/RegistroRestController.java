package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uy.com.curso.pizzahurt.dtos.CredencialDto;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.dtos.RegistroRestDto;
import uy.com.curso.pizzahurt.exceptions.EmailAlreadyExistException;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class RegistroRestController {

    private final UsuarioService usuarioService;

    public RegistroRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario/login")
    public ResponseEntity<String> loginRemoto(@RequestBody @Valid CredencialDto credencial){
        log.info("Usuario: "+ credencial.getUsername());
        log.info("Password "+ credencial.getPassword());
        if (usuarioService.validUsernameAndPassword(credencial)){
            return ResponseEntity.ok("Login Successfull!!");
        }else{
            return new ResponseEntity<>("Username or Password not valid!!! ",HttpStatus.UNAUTHORIZED);

        }
    }

    @PostMapping("/usuario/registro")
    public ResponseEntity<String> registro(@RequestBody @Valid RegistroRestDto registroDto){
        log.info("Nombre: "+ registroDto.getNombreCompleto());
        log.info("Email: "+ registroDto.getEmail());
        log.info("Password: "+ registroDto.getPassword());
        log.info("Telefono: "+ registroDto.getTelefono());
        try {
            Usuario usuario = usuarioService.createUsuarioByRegistroRestDto(registroDto);
        } catch (EmailAlreadyExistException e) {
            return new ResponseEntity<>("El e-mail ya se encuentra registrado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Usuario creado correctamente",HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarException(MethodArgumentNotValidException e){
        StringBuilder aux = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> aux.append(fieldError.getField())
                        .append(" ").append(fieldError.getDefaultMessage()));
        return new ResponseEntity<>(aux.toString(), HttpStatus.BAD_REQUEST);
    }
}
