package uy.com.curso.pizzahurt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.experimental.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uy.com.curso.pizzahurt.dtos.CredencialDto;
import uy.com.curso.pizzahurt.dtos.MedioPagoDto;
import uy.com.curso.pizzahurt.dtos.RegistroRestDto;
import uy.com.curso.pizzahurt.exceptions.EmailAlreadyExistException;
import uy.com.curso.pizzahurt.exceptions.MedioPagoAlreadyExistException;
import uy.com.curso.pizzahurt.exceptions.NotFoundMetodoPagoException;
import uy.com.curso.pizzahurt.exceptions.UsuarioNotFoundException;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiRestController {

    private final UsuarioService usuarioService;

    public ApiRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario/login")
    public ResponseEntity<String> loginRemoto(@RequestBody @Valid CredencialDto credencial){
        if (usuarioService.validUsernameAndPassword(credencial)){
            return ResponseEntity.ok("Login correcto!!");
        }else{
            return new ResponseEntity<>("Usuario o Contraseña incorrectos",HttpStatus.UNAUTHORIZED);

        }
    }

    @PostMapping("/usuario/registro")
    public ResponseEntity<String> registro(@RequestBody @Valid RegistroRestDto registroDto){
        try {
            Usuario usuario = usuarioService.createUsuarioByRegistroRestDto(registroDto);
            return new ResponseEntity<>("Usuario creado correctamente",HttpStatus.CREATED);
        } catch (EmailAlreadyExistException e) {
            return new ResponseEntity<>("El e-mail ya se encuentra registrado",HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/mediopago/agregar")
    public ResponseEntity<String> agregarMedioPago(@RequestParam(name="email") @Email String email, @RequestBody @Valid MedioPagoDto medioPagoDto){
        try {
            usuarioService.addMedioPagoByEmail(email,medioPagoDto);
            return new ResponseEntity<>("Medio de Pago ingresado correctamente!!", HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (MedioPagoAlreadyExistException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/mediopago/ver")
    public ResponseEntity<String> agregarMedioPago(@RequestParam(name="email") @Email String email){
        try {
            MedioPagoDto medioPagoDto = usuarioService.getMedioPagoByEmail(email);
            return new ResponseEntity<String>(medioPagoDto.toJson(), HttpStatus.OK);
        } catch (NotFoundMetodoPagoException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            log.error("Error al convertir el Método de Pago");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


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
