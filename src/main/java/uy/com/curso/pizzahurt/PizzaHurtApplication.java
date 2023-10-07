package uy.com.curso.pizzahurt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uy.com.curso.pizzahurt.models.Ingrediente;
import uy.com.curso.pizzahurt.enums.TipoIngrediente;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.IngredienteRepository;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;

@SpringBootApplication
public class PizzaHurtApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaHurtApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner dataLoaderIngrediente(IngredienteRepository ingredienteRepository) {
        return (args -> {
            // Tipo de Masas
            ingredienteRepository.save(new Ingrediente("Masa Común", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Extra Fina", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Tradicional", TipoIngrediente.MASA));
            ingredienteRepository.save(new Ingrediente("Sin Gluten", TipoIngrediente.MASA));

            // Tipo de Salsas
            ingredienteRepository.save(new Ingrediente("Salsa de Tomate", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa Alfredo", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa Pesto", TipoIngrediente.SALSA));
            ingredienteRepository.save(new Ingrediente("Salsa BBQ", TipoIngrediente.SALSA));

            // Tipo de Quesos
            ingredienteRepository.save(new Ingrediente("Muzzarella", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Dambo", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Cuatro Quesos", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Chedar", TipoIngrediente.QUESO));
            ingredienteRepository.save(new Ingrediente("Queso Azul", TipoIngrediente.QUESO));

            // Tipo de Topins
            ingredienteRepository.save(new Ingrediente("Panceta", TipoIngrediente.TOPPINGS));
            ingredienteRepository.save(new Ingrediente("Aceituna", TipoIngrediente.TOPPINGS));
            ingredienteRepository.save(new Ingrediente("Anchoas", TipoIngrediente.TOPPINGS));
            ingredienteRepository.save(new Ingrediente("Ananá", TipoIngrediente.TOPPINGS));
            ingredienteRepository.save(new Ingrediente("Palmitos", TipoIngrediente.TOPPINGS));

        });
    }

    @Bean
    CommandLineRunner dataLoaderUsuario(UsuarioRepository usuarioRepository) {
        return (args -> {

            usuarioRepository.save(new Usuario("Jhon Doe","jhondoe@prueba.com","222-2222",
                    passwordEncoder().encode("Password1"),true,"Montevideo","Centro","Soriano","1287","101",
                    11600,"Casi en la esquina", "Visa","Jhon Doe",
                    "4336487641383753","05/24","212"));

            usuarioRepository.save(new Usuario("Jane Doe","janedoe@prueba.com","222-2222",
                    passwordEncoder().encode("Password2"),true,"Montevideo","Centro","18 de Julio","1287","101",
                    11600,"Casi en la esquina", "Visa","Jane Doe",
                    "4469046274639110","05/24","212"));

        });
    }
}