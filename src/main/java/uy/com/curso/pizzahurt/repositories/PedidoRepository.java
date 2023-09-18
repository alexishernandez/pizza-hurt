package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.repository.CrudRepository;
import uy.com.curso.pizzahurt.entities.Pedido;

interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
