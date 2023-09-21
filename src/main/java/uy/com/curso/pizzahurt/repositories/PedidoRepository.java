package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.curso.pizzahurt.models.Pedido;

interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
