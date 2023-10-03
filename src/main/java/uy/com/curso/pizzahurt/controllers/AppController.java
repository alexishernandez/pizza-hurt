package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import uy.com.curso.pizzahurt.dtos.CarritoDto;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@Controller
@RequestMapping("/protected")
@SessionAttributes("carrito")
public class AppController {

    @ModelAttribute("carrito")
    public CarritoDto carrito() {
        return new CarritoDto();
    }

    private final UsuarioService usuarioService;

    public AppController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home1(Model model,  @AuthenticationPrincipal Usuario usuario  ) {
        System.out.println("Entre en home1");
        return "home";
    }

}
