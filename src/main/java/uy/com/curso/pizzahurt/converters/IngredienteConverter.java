package uy.com.curso.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.com.curso.pizzahurt.models.Ingrediente;
import uy.com.curso.pizzahurt.services.IngredienteService;

@Component
public class IngredienteConverter implements Converter<Long, Ingrediente> {

    private IngredienteService ingredienteService;

    public IngredienteConverter(IngredienteService ingredienteService) {
        super();
        this.ingredienteService = ingredienteService;
    }

    @Override
    public Ingrediente convert(Long id) {
        return ingredienteService.find(id).orElseThrow();
    }

    

    
}
