package uy.com.curso.pizzahurt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.PizzaRepository;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;


    public Pizza createPizza(Pizza pizza){
    	return pizzaRepository.save(pizza);
    }

    @Transactional
    public void updatePizza(Pizza pizza){
        pizzaRepository.save(pizza);
    }

   




}
