package uy.com.curso.pizzahurt.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uy.com.curso.pizzahurt.dtos.UsuarioPasswordDto;
import uy.com.curso.pizzahurt.exceptions.UsuarioNotFoundException;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@RequestMapping("protected/usuario")
@Controller
@SessionAttributes("carrito")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/modificar")
    public String modificarUsuario(Model model, @AuthenticationPrincipal Usuario usuario) {
        usuario = usuarioService.findByEmail(usuario.getEmail());
        model.addAttribute("usuario",usuario);
        return "modificarUsuario";
    }

    @PostMapping("/modificar")
    public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model) throws UsuarioNotFoundException {
        log.info("Guardando usuario: " + usuario.toString());

        if (result.hasErrors()) {
            log.error("Se encontraron errores al validar: {}", result.getAllErrors());
            return "modificarUsuario";
        }
        usuarioService.updateUsuario(usuario);
        model.addAttribute("mensaje","Actualización exitosa...");
        return "modificarUsuario";
    }

    @GetMapping("/cambiarPassword")
    public String cambiarPassword(Model model, @AuthenticationPrincipal Usuario usuario) {
        UsuarioPasswordDto usuarioPasswordDto = new UsuarioPasswordDto();
        usuarioPasswordDto.setEmail(usuario.getEmail());
        model.addAttribute("usuarioPasswordDto", usuarioPasswordDto);
        return "cambiarPassword";
    }

    @PostMapping("/cambiarPassword")
    public String guardarPassword(@ModelAttribute("usuarioPasswordDto") @Valid UsuarioPasswordDto usuarioPasswordDto,
                 BindingResult result,Model model,@AuthenticationPrincipal Usuario usuario) {

        if (result.hasErrors()) {
            log.error("Se han encontraron errores al validar: {}", result.getAllErrors());
            return "cambiarPassword";
        }
        Usuario usuarioAux = usuarioService.findByEmail(usuario.getEmail());
        if (usuarioService.comparePasswordWithUserPassword(usuarioPasswordDto.getPasswordActual(),usuarioAux)){
            usuarioService.cambiarPasswordUsuario(usuarioPasswordDto.getPasswordNueva(),usuarioAux);
            model.addAttribute("success","La contraseña se cambió exitosamente");
            return "cambiarPassword";

        } else{
            log.error("Se han encontraron errores en la contraseña actual...");
            result.rejectValue("passwordActual", "","Contraseña incorrecta");
            return "cambiarPassword";}
    }


    @ExceptionHandler({UsuarioNotFoundException.class})
    public ModelAndView UsuarioNoEncontrado(HttpServletRequest request, Exception exception){
        log.error("Request: "+request.getRequestURL() +" raised "+ exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView ;
    }

    @InitBinder /* Convierte cadenas vacías en nulas cuando se envía un formulario */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}