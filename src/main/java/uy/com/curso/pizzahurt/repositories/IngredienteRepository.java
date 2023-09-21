package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.curso.pizzahurt.models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {

}
