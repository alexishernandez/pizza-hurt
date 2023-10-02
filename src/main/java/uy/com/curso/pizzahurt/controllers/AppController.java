package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@Controller
@RequestMapping("/protected")
public class AppController {

    private final UsuarioService usuarioService;

    public AppController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home1(Model model) {
        System.out.println("Entre en home1");
        return "home";
    }

}
