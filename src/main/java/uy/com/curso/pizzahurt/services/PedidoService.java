package uy.com.curso.pizzahurt.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.com.curso.pizzahurt.dtos.PedidoDto;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.PedidoRepository;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PizzaService pizzaService;

    public PedidoService(PedidoRepository pedidoRepository, PizzaService pizzaService) {
        this.pedidoRepository = pedidoRepository;
        this.pizzaService = pizzaService;
    }

    public Optional<Pedido> find(long id){
        return pedidoRepository.findById(id);
    }

    public List<PedidoDto> findAllPedidoDtoByUsuario(Usuario usuario){
        List<PedidoDto> pedidoDtoList = new LinkedList<PedidoDto>();
        List<Pedido>  pedidos = pedidoRepository.findAllByUsuario(usuario);
        for (Pedido pedido: pedidos){
            PedidoDto pedidoDto= new PedidoDto();
            AppHelper.fillPedidoDtofromPedido(pedidoDto,pedido);
            pedidoDtoList.add(pedidoDto);
        }
        return pedidoDtoList;
    }

    @Transactional
    public void crearPedido(Pedido pedido){
    	pedido.setFechaPedido(new Date());
        System.out.println("********************************");
        pedido.getPizzas().forEach(System.out::println);
        System.out.println("********************************");
        pedidoRepository.save(pedido);
    }
    
}
