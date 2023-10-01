package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Usuario;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


    public List<Pedido> findAllByUsuario(Usuario usuario);

}
