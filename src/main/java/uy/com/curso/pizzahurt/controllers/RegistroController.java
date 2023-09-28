package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registrarUsuario")
    public String showUsuario(Model model) {
        RegistroDto registro= new RegistroDto();
        model.addAttribute("registro",registro);
        return "registroUsuario";
    }

    @PostMapping("/registrarUsuario")
    public String agregarUsuario(@Valid RegistroDto registro, Errors errores, Model model) {
        model.addAttribute("registro",registro);
        if (errores.hasErrors()) {
            log.error("Se encontraron errores al validar: {}", errores.getAllErrors());
            return "registroUsuario";
        }
        if (usuarioService.existsUsuarioByEmail(registro.getEmail())){
            log.error("Error: El e-mail ya se encuentra registrado...");
            model.addAttribute("mensaje_error","Error: El e-mail ya se encuentra registrado...");
            return "registroUsuario";
        }
        usuarioService.createUsuarioByRegistroDto(registro);
        model.addAttribute("mensaje","Usuario creado correctamente...");
        return "registroUsuario";
    }
}