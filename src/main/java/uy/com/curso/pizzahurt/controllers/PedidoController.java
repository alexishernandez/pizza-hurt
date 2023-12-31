package uy.com.curso.pizzahurt.controllers;

import java.util.Iterator;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.com.curso.pizzahurt.dtos.CarritoDto;
import uy.com.curso.pizzahurt.dtos.PedidoDto;
import uy.com.curso.pizzahurt.exceptions.PedidoNotFoundException;
import uy.com.curso.pizzahurt.exceptions.UsuarioNotFoundException;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.PedidoService;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
@RequestMapping("/protected/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @GetMapping("/crear")
    public String crearPedido(Model model, @ModelAttribute("carrito") CarritoDto carrito,
                              @AuthenticationPrincipal Usuario usuario) throws UsuarioNotFoundException {
        if (usuario.getId()==null){
            return "redirect:/login";
        }
    	Pedido pedido = new Pedido();
        Usuario aux = usuarioService.find(usuario.getId());
        AppHelper.fillPedidoDireccionfromUsuario(pedido, aux);
        AppHelper.fillPedidoMetodoPagofromUsuario(pedido, aux);
        List<Pizza> pizzasPedido = pedido.getPizzas();
        Iterator<Pizza> it = carrito.iterator();
        while (it.hasNext()) {
            System.out.println(it);
            pizzasPedido.add(it.next());
        }
        model.addAttribute("pedido", pedido);
        return ("editPedido");
    }

    @GetMapping("/quitarPizza")
    public String quitarPizzaCarrito(@ModelAttribute("carrito") CarritoDto carrito,@RequestParam(name = "id") Long id){
        log.info("entro en quitarPizzaCarrito: "+id);
        carrito.removeIf(pizza -> id.equals(pizza.getId()));
        return "redirect:/protected/home";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid Pedido pedido, BindingResult result,
            @AuthenticationPrincipal Usuario usuario,@ModelAttribute("carrito") CarritoDto carrito, Model model,
            RedirectAttributes attributes) {
        pedido.setUsuario(usuario);
        //Quito un identificador temporal
        carrito.forEach(pizza -> pizza.setId(null));
        pedido.getPizzas().addAll(carrito);
        if (usuario.getId()==null){
            return "redirect:/login";
        }
        if (result.hasErrors()){
            return "editPedido";
        }
        if(carrito.isEmpty()){
            result.reject("mensaje_error","No se han agregado pizzas al carrito");
            return "editPedido";
        }
        pedidoService.crearPedido(pedido);
        carrito.clear();
        model.addAttribute("success","Actualización exitosa...");
        attributes.addFlashAttribute("success", "Pedido guardado exitosamente.");
        return "redirect:/protected/pedido/list";
    }


    @GetMapping("/show")
    public String showPedido(Model model, @RequestParam(name="id") Long id, @AuthenticationPrincipal Usuario usuario) throws PedidoNotFoundException {

        Pedido pedido =pedidoService.find(id);
        if (pedido.getUsuario().getId().equals(usuario.getId())) {
            model.addAttribute("pedido", pedido);
            return "showPedido";
        } else
            throw new PedidoNotFoundException("Pedido no encontrado o no se encuentra asociado a su usuario");
    }

    @GetMapping("/list")
    public String listPedido(Model model, @AuthenticationPrincipal Usuario usuario) throws UsuarioNotFoundException {
        if (usuario.getId() ==null){
            return "redirect:/login";
        }
        List<PedidoDto> pedidosDto = pedidoService.findAllPedidoDtoByUsuario(usuarioService.find(usuario.getId()));
        model.addAttribute("pedidosDto",pedidosDto);
        return ("listPedidos");
    }

    @ExceptionHandler({PedidoNotFoundException.class, UsuarioNotFoundException.class})
    public ModelAndView pedidoNoEncontrado(HttpServletRequest request, Exception exception){
        log.error("Request: "+request.getRequestURL() +" raised "+ exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
    return modelAndView ;
    }

    @ModelAttribute("carrito")
    public CarritoDto carrito() {
        return new CarritoDto();
    }

    @InitBinder /* Convierte cadenas vacías en nulas cuando se envía un formulario */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
