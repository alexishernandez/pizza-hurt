package uy.com.curso.pizzahurt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uy.com.curso.pizzahurt.entities.Ingrediente;
import uy.com.curso.pizzahurt.entities.TipoIngrediente;
import uy.com.curso.pizzahurt.repositories.IngredienteRepository;

@SpringBootApplication
public class PizzaHurtApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaHurtApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(IngredienteRepository ingredienteRepository) {
        return (args -> {
            // Tipo de Masas
            ingredienteRepository.save(new Ingrediente("Masa Común", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Extra Fina", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Tradicional", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Sin Gluten", TipoIngrediente.MASA));

            // Tipo de Salsas
            ingredienteRepository.save(new Ingrediente("Salsa de Tomate Común", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa Alfredo", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa Pesto", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa BBQ", TipoIngrediente.SALSA));

            // Tipo de Quesos
            ingredienteRepository.save(new Ingrediente("Muzzarella", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Dambo", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Cuatro Quesos", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Chedar", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Azul", TipoIngrediente.QUESO));

            // Tipo de Topins
            ingredienteRepository.save(new Ingrediente("Panceta", TipoIngrediente.TOPINS));
            ingredienteRepository.save(new Ingrediente("Aceituna", TipoIngrediente.TOPINS));
            ingredienteRepository.save(new Ingrediente("Anchoas", TipoIngrediente.TOPINS));
            ingredienteRepository.save(new Ingrediente("Ananá", TipoIngrediente.TOPINS));
            ingredienteRepository.save(new Ingrediente("Palmitos", TipoIngrediente.TOPINS));

        });
    }
}
