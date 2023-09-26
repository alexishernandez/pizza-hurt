package uy.com.curso.pizzahurt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.services.IngredienteService;
import uy.com.curso.pizzahurt.services.PizzaService;


@Slf4j
@RequestMapping("/pizza")
@Controller
@RequiredArgsConstructor
public class PizzaController {
	
	public final IngredienteService ingredienteService;
	public final PizzaService pizzaService;
	
	 @GetMapping("/crear") 
	 public String crearPizza(Model model) {
		 
		 
		 model.addAttribute("pizza", new Pizza());
		 model.addAttribute("allMasas", ingredienteService.getAllMasas() );
		 model.addAttribute("allSalsas", ingredienteService.getAllSalsas() );
		 model.addAttribute("allQuesos", ingredienteService.getAllQuesos() );
		 model.addAttribute("allToppings", ingredienteService.getAllToppings() );
		 
		 return "editPizza";
	 }
	 
	 @PostMapping("/crear") 
	 public String grabarPizza(@Valid Pizza pizza, Errors errores, Model model) {

        if (errores.hasErrors()) {
            log.error("Se encontraron errores al validar la pizza: {}", errores.getAllErrors());
            return "modificarPizza";
        } else  {
        	pizzaService.updatePizza(pizza);        	

	        model.addAttribute("mensaje","Actualización exitosa...");
	        return "modificarPizza";

        }

		 

		 
	 }
	 


}
