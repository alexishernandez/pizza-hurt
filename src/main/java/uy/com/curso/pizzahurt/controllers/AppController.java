package uy.com.curso.pizzahurt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uy.com.curso.pizzahurt.models.Usuario;

@Controller
public class AppController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registrarUsuario")
    public String showUsuario(Model model) {
        Usuario usuario= new Usuario();
        model.addAttribute("usuario",usuario);
        return "registroUsuario";
    }

}
