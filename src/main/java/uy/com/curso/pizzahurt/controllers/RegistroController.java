package uy.com.curso.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.exceptions.EmailAlreadyExistException;
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
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error",defaultValue = "false") boolean loginError ) {
        return "login";
    }

    @GetMapping("/registrarUsuario")
    public String showUsuario(Model model) {
        RegistroDto registroDto= new RegistroDto();
        model.addAttribute("registroDto",registroDto);
        return "registroUsuario";
    }

    @PostMapping("/registrarUsuario")
    public String agregarUsuario(@Valid RegistroDto registroDto, BindingResult result, Model model) {
        model.addAttribute("registroDto",registroDto);
        if (result.hasErrors()) {
            log.error("Se encontraron errores al validar: {}", result.getAllErrors());
            return "registroUsuario";
        }
        try {
            usuarioService.createUsuarioByRegistroDto(registroDto);
        } catch (EmailAlreadyExistException e) {
            result.reject("mensaje_error","El e-mail ya se encuentra registrado...");
//            model.addAttribute();
            return "registroUsuario";
        }
        model.addAttribute("mensaje","Usuario creado correctamente...");
        return "registroUsuario";
    }

    @InitBinder /* Convierte cadenas vacías en nulas cuando se envía un formulario */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}