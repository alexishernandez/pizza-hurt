package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.repository.CrudRepository;
import uy.com.curso.pizzahurt.entities.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente,Long> {


}
