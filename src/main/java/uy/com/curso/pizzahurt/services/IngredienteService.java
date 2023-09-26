package uy.com.curso.pizzahurt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.com.curso.pizzahurt.enums.TipoIngrediente;
import uy.com.curso.pizzahurt.models.Ingrediente;
import uy.com.curso.pizzahurt.repositories.IngredienteRepository;


@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public Optional<Ingrediente> find(long id){
        return ingredienteRepository.findById(id);
    }
    
    public List<Ingrediente> getAllMasas() {
    	return ingredienteRepository.findByTipoIngrediente(TipoIngrediente.MASA);
    }
    
    public List<Ingrediente> getAllSalsas() {
    	return ingredienteRepository.findByTipoIngrediente(TipoIngrediente.SALSA);
    }

    
    public List<Ingrediente> getAllQuesos() {
    	return ingredienteRepository.findByTipoIngrediente(TipoIngrediente.QUESO);
    }
    
    public List<Ingrediente> getAllToppings() {
    	return ingredienteRepository.findByTipoIngrediente(TipoIngrediente.TOPPINGS);
    }
    
    public Ingrediente findByNombre(String nombre) {
    	return ingredienteRepository.findByNombre(nombre);
    }
        


    
}
