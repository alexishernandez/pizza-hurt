package uy.com.curso.pizzahurt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.repositories.IngredienteRepository;
import uy.com.curso.pizzahurt.services.IngredienteService;



@Slf4j
@RequestMapping("/pizza")
@Controller
@RequiredArgsConstructor
public class PizzaController {
	
	public final IngredienteService ingredienteService;
	
	 @GetMapping("/crear") 
	 public String crearPizza(Model model) {
		 
		 
		 model.addAttribute("pizza", new Pizza());
		 model.addAttribute("allMasas", ingredienteService.getAllMasas() );
		 model.addAttribute("allSalsas", ingredienteService.getAllSalsas() );
		 model.addAttribute("allQuesos", ingredienteService.getAllQuesos() );
		 model.addAttribute("allToppings", ingredienteService.getAllToppings() );
		 
		 return "editPizza";
	 }

}
