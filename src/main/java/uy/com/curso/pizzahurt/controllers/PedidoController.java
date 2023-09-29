package uy.com.curso.pizzahurt.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uy.com.curso.pizzahurt.dtos.CarritoDto;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.services.IngredienteService;
import uy.com.curso.pizzahurt.services.PedidoService;
import uy.com.curso.pizzahurt.services.PizzaService;

@Slf4j
@RequestMapping("/protected/pedido")
@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class PedidoController {

    private final PedidoService pedidoService;

    
    @GetMapping("/crear")
    public String crearPedido(Model model, @ModelAttribute("carrito") CarritoDto carrito) {
    	
    	Pedido pedido = new Pedido();
    	List<Pizza> pizzasPedido = pedido.getPizzas();

    	Iterator<Pizza> it = carrito.iterator();
    	while (it.hasNext()) {
    		System.out.println(it);
    		pizzasPedido.add(it.next());	
    		
    		
    	}
    	
    	model.addAttribute("pedido", pedido );

    	
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
