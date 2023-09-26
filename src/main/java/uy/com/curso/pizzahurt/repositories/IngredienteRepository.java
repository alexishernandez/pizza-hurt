package uy.com.curso.pizzahurt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.com.curso.pizzahurt.enums.TipoIngrediente;
import uy.com.curso.pizzahurt.models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {
	
	List<Ingrediente> findByTipoIngrediente(TipoIngrediente tipoIngrediente);

}
