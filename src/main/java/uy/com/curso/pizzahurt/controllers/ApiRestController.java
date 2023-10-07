package uy.com.curso.pizzahurt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uy.com.curso.pizzahurt.dtos.CredencialDto;
import uy.com.curso.pizzahurt.dtos.MessagesRestDto;
import uy.com.curso.pizzahurt.dtos.MedioPagoDto;
import uy.com.curso.pizzahurt.dtos.RegistroRestDto;
import uy.com.curso.pizzahurt.exceptions.EmailAlreadyExistException;
import uy.com.curso.pizzahurt.exceptions.MedioPagoAlreadyExistException;
import uy.com.curso.pizzahurt.exceptions.NotFoundMetodoPagoException;
import uy.com.curso.pizzahurt.exceptions.UsuarioNotFoundException;
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
            return returnMensajesAuxiliares("login_successful: Login correcto",HttpStatus.OK);
        }else{
            return returnMensajesAuxiliares("login_error: Usuario o Contraseña incorrectos",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/usuario/registro")
    public ResponseEntity<String> registro(@RequestBody @Valid RegistroRestDto registroDto){
        try {
            Usuario usuario = usuarioService.createUsuarioByRegistroRestDto(registroDto);
            return returnMensajesAuxiliares("registro_successful: Usuario creado correctamente",HttpStatus.CREATED);
        } catch (EmailAlreadyExistException e) {
            return returnMensajesAuxiliares("registro_error: El e-mail ya se encuentra registrado",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mediopago/agregar")
    public ResponseEntity<String> agregarMedioPago(@RequestParam(name="email") @Email String email, @RequestBody @Valid MedioPagoDto medioPagoDto){
        try {
            usuarioService.addMedioPagoByEmail(email,medioPagoDto);
            return returnMensajesAuxiliares("mediopago_successful: Medio de Pago ingresado correctamente",HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            return returnMensajesAuxiliares("mediopago_error: "+e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (MedioPagoAlreadyExistException e) {
            return returnMensajesAuxiliares("mediopago_error: "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/mediopago/ver")
    public ResponseEntity<String> consultarMedioPago(@RequestParam(name="email") @Email String email){
        try {
            MedioPagoDto medioPagoDto = usuarioService.getMedioPagoByEmail(email);
            return new ResponseEntity<String>(medioPagoDto.toJson(), HttpStatus.OK);
        } catch (NotFoundMetodoPagoException e) {
            return returnMensajesAuxiliares("mediopago_error: "+e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (UsuarioNotFoundException e) {
            return returnMensajesAuxiliares("mediopago_error: "+e.getMessage(),HttpStatus.CONFLICT);
        } catch (JsonProcessingException e) {
            log.error("Error al convertir el Método de Pago");
            return returnMensajesAuxiliares("mediopago_error: Ha ocurrido un Error Inesperado",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<String> returnMensajesAuxiliares(String messageString, HttpStatus httpStatus){
        MessagesRestDto messagesRestDto = new MessagesRestDto();
        messagesRestDto.getMessages().add(messageString);
        try{
            return new ResponseEntity<>(messagesRestDto.toJson(),httpStatus);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarException(MethodArgumentNotValidException e){
        MessagesRestDto messagesRestDto = new MessagesRestDto();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    StringBuilder aux = new StringBuilder();
                    aux.append("campo_").append(fieldError.getField())
                            .append("_error: ").append(fieldError.getDefaultMessage());
                    messagesRestDto.getMessages().add(aux.toString());
                });
        try {
            String erroresString = messagesRestDto.toJson();
            return new ResponseEntity<>(erroresString, HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
