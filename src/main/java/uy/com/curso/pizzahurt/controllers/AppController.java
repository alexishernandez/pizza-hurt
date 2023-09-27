package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@Controller
public class AppController {

    private final UsuarioService usuarioService;

    public AppController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }




    @GetMapping("/")
    public String home(Model model) {
        Usuario usuario= new Usuario();
        model.addAttribute("usuario",usuario);
        return "home";
    }

    @GetMapping("/home")
    public String home1(Model model) {
        Usuario usuario= new Usuario();
        model.addAttribute("usuario",usuario);
        return "home";
    }

}
