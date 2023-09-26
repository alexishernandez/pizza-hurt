package uy.com.curso.pizzahurt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PizzaController {
	
	 @GetMapping("/") 
	 public String index() {
		 return "inicio";
	 }

}
