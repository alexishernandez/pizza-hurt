package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.com.curso.pizzahurt.models.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {


}

