package uy.com.curso.pizzahurt.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.repositories.PedidoRepository;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> find(long id){
        return pedidoRepository.findById(id);
    }
}
