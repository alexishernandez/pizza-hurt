package uy.com.curso.pizzahurt.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.services.PedidoService;

@RequestMapping("/pedido")
@Controller
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @GetMapping("/crear")
    public String crearPedido(Model model) {
    	
    	model.addAttribute("pedido", new Pedido());
    	
    	return ("editPedido");
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


}
