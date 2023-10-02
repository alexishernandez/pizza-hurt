package uy.com.curso.pizzahurt.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.Data;
import uy.com.curso.pizzahurt.dtos.PedidoDto;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.models.Pedido;
import uy.com.curso.pizzahurt.models.Pizza;
import uy.com.curso.pizzahurt.models.Usuario;

@Data
@Component
public  class AppHelper {


    private static PasswordEncoder encoder;

    @Autowired
    public AppHelper(PasswordEncoder encoder) {
        AppHelper.encoder = encoder;
    }

    public static void fillUsuarioFromRegistroDto(RegistroDto registroDto, Usuario usuario){
        usuario.setNombreCompleto(registroDto.getNombreCompleto());
        usuario.setActivo(true);
        usuario.setTelefono(registroDto.getTelefono());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(encoder.encode(registroDto.getPassword()));
    }

    public static void fillPedidoDtofromPedido(PedidoDto pedidoDto, Pedido pedido){
        pedidoDto.setId(pedido.getId());
        pedidoDto.setFecha(pedido.getFechaPedido().toString());
        pedidoDto.setReceptor(pedido.getNombreReceptor());
        String direccion = pedido.getCalle()+" "+pedido.getNroPuerta();
        if (!pedido.getApto().isEmpty()){
            direccion =direccion+"/"+pedido.getApto();
        }
        direccion = direccion + "(" + pedido.getCiudad() + "-" + pedido.getBarrio() + ")";
        pedidoDto.setDireccion(direccion);
        
        // Concateno los nombres de las pizzas para presentarlos
        List<String> listaNombresPizza = new ArrayList<String>();
        for ( Pizza pizza: pedido.getPizzas()) {
        	listaNombresPizza.add(pizza.getNombre());
        	
        }
        String nombrePizzas = String.join("-", listaNombresPizza);
        pedidoDto.setPizzas(nombrePizzas);
    }

    public static void fillPedidoDireccionfromUsuario(Pedido pedido,Usuario usuario){
      pedido.setCiudad(usuario.getCiudad());
      pedido.setBarrio(usuario.getBarrio());
      pedido.setCalle(usuario.getCalle());
      pedido.setCodigoPostal(usuario.getCodigoPostal());
      pedido.setNroPuerta(usuario.getNroPuerta());
      pedido.setApto(usuario.getApto());
      pedido.setObservaciones(usuario.getObservaciones());
    }

    public static void fillPedidoMetodoPagofromUsuario(Pedido pedido,Usuario usuario){
        pedido.setEmisor(usuario.getEmisor());
        pedido.setNroTarjeta(usuario.getNroTarjeta());
        pedido.setFechaVencimiento(usuario.getFechaVencimiento());
        pedido.setCodigoCVV(usuario.getCodigoCVV());
    }
}
