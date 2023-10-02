package uy.com.curso.pizzahurt.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uy.com.curso.pizzahurt.dtos.CarritoDto;
import uy.com.curso.pizzahurt.dtos.PedidoDto;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.IngredienteService;
import uy.com.curso.pizzahurt.services.PedidoService;
import uy.com.curso.pizzahurt.services.PizzaService;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Slf4j
@RequestMapping("/protected/pedido")
@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @GetMapping("/crear")
    public String crearPedido(Model model, @ModelAttribute("carrito") CarritoDto carrito,
                              @AuthenticationPrincipal Usuario usuario) {
    	Pedido pedido = new Pedido();
        Optional<Usuario> aux = usuarioService.find(usuario.getId());
        if (aux.isPresent()) {
            AppHelper.fillPedidoDireccionfromUsuario(pedido, usuario);
            AppHelper.fillPedidoMetodoPagofromUsuario(pedido, usuario);
            List<Pizza> pizzasPedido = pedido.getPizzas();
            Iterator<Pizza> it = carrito.iterator();
            while (it.hasNext()) {
                System.out.println(it);
                pizzasPedido.add(it.next());
            }

            model.addAttribute("pedido", pedido);
            return ("editPedido");
        }
        //TODO ver que hacer con el error
        return "editPedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid Pedido pedido, BindingResult result, @AuthenticationPrincipal Usuario usuario, Model model) {
        if (result.hasErrors()){
            return "editPedido";
        }else{
            pedido.setUsuario(usuario);
            pedidoService.crearPedido(pedido);
            return "editPedido";
        }
    }


    @GetMapping("/show")
    public String showPedido(Model model, @RequestParam(name="id") Long id) {
        Optional<Pedido> pedido = pedidoService.find(id);
        if (pedido.isPresent()){
            model.addAttribute("pedido",pedido.get());
            return "editPedido";
        }else{
            return "editPedido";
            }
    }

    @GetMapping("/list")
    public String listPedido(Model model, @AuthenticationPrincipal Usuario usuario) {
        List<PedidoDto> pedidosDto = pedidoService.findAllPedidoDtoByUsuario(usuario);
        model.addAttribute("pedidosDto",pedidosDto);
        return ("listPedidos");
    }
}
