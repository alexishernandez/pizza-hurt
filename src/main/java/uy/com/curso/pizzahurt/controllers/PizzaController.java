package uy.com.curso.pizzahurt.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uy.com.curso.pizzahurt.dtos.CarritoDto;
import uy.com.curso.pizzahurt.models.Ingrediente;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.services.IngredienteService;
import uy.com.curso.pizzahurt.services.PizzaService;


@Slf4j
@RequestMapping("/protected/pizza")
@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class PizzaController {
	
	public final IngredienteService ingredienteService;
	public final PizzaService pizzaService;
	

	// - - - Model Attributes - - - 
		
	@ModelAttribute("carrito")
	public CarritoDto carrito() {
	    return new CarritoDto();
	}
	
	
	@ModelAttribute("allMasas")
	public List<Ingrediente> allMasas() {
	    return ingredienteService.getAllMasas() ;
	}
	
	@ModelAttribute("allSalsas")
	public List<Ingrediente> allSalsas() {
	    return ingredienteService.getAllSalsas() ;
	}

	@ModelAttribute("allQuesos")
	public List<Ingrediente> allQuesos() {
	    return ingredienteService.getAllQuesos() ;
	}

	@ModelAttribute("allToppings")
	public List<Ingrediente> allToppings() {
	    return ingredienteService.getAllToppings() ;
	}

	 @GetMapping("/crear") 
	 public String crearPizza(Model model) {
		 model.addAttribute("pizza", new Pizza());
		 return "editPizza";
	 }
	 
	 @PostMapping("/crear")
	 public String guardarPizza(@Valid Pizza pizza,Errors errores, @ModelAttribute("carrito") CarritoDto carrito, Model model) {

        if (errores.hasErrors()) {
            log.error("Se encontraron errores al validar la pizza: {}", errores.getAllErrors());
            return "editPizza";
        } else  {
        	carrito.add(pizza);
			model.addAttribute("success","Pizza creada exitosamente.");
	        return "editPizza";

        }
		 
	 }
	
	 
/*	 
	 @PostMapping("/borrar") 
	 public String borrarPizza(@Valid Pizza pizza, @ModelAttribute("carrito") CarritoDto carrito, 
			 				    Errors errores, Model model) {

		 return 
	 }
*/	 
	 
	 


}
