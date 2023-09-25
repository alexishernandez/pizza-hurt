package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

import java.util.Optional;

@Slf4j
@RequestMapping("/usuario")
@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/show")
    public String showUsuario(Model model, @RequestParam(name="id") Long id) {
        Optional<Usuario> usuario = usuarioService.find(id);
        if (usuario.isPresent()){
            model.addAttribute("usuario",usuario.get());
            return "editUsuario";
        }else{
            return "editUsuario";
            }
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@Valid Usuario usuario, Errors errores, Model model) {
        log.info("Guardando usuario: " + usuario.toString());
        if (errores.hasErrors()) {
            log.error("Se encontraror errores al validar: {}", errores.getAllErrors());
            return "editUsuario";
        }
        usuarioService.updateUsuario(usuario);
        model.addAttribute("mensaje","Actualización exitosa...");
        return "editUsuario";
    }

    @GetMapping("/registrarUsuario")
    public String showUsuario(Model model) {
        Usuario usuario= new Usuario();
        model.addAttribute("usuario",usuario);
        return "registroUsuario";
    }

    @PostMapping("/registrarUsuario")
    public String agregarUsuario(@Valid Usuario usuario, Errors errores,Model model) {
        model.addAttribute("usuario",usuario);
        return "registroUsuario";
    }





}
