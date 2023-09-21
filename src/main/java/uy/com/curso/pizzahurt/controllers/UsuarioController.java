package uy.com.curso.pizzahurt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

import java.util.Optional;

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


}
