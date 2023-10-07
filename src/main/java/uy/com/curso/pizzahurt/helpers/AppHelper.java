package uy.com.curso.pizzahurt.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.Data;
import uy.com.curso.pizzahurt.dtos.MedioPagoDto;
import uy.com.curso.pizzahurt.dtos.PedidoDto;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.dtos.RegistroRestDto;
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

    public static void fillUsuarioFromRegistroRestDto(RegistroRestDto registro, Usuario usuario){
        usuario.setNombreCompleto(registro.getNombreCompleto());
        usuario.setActivo(true);
        usuario.setTelefono(registro.getTelefono());
        usuario.setEmail(registro.getEmail());
        usuario.setPassword(encoder.encode(registro.getPassword()));
    }

    public static void fillPedidoDtofromPedido(PedidoDto pedidoDto, Pedido pedido){
        pedidoDto.setId(pedido.getId());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String strDate = dateFormat.format(pedido.getFechaPedido());
        pedidoDto.setFecha(strDate);
        pedidoDto.setReceptor(pedido.getNombreReceptor());
        String direccion = pedido.getCalle()+" "+pedido.getNroPuerta();
        if (pedido.getApto() !=null){
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

    public static void fillUsuarioFromMedioPago(MedioPagoDto medioPagoDto, Usuario usuario){
        usuario.setEmisor(medioPagoDto.getEmisor());
        usuario.setNombreTarjeta(medioPagoDto.getNombreTarjeta());
        usuario.setNroTarjeta(medioPagoDto.getNroTarjeta());
        usuario.setFechaVencimiento(medioPagoDto.getFechaVencimiento());
        usuario.setCodigoCVV(medioPagoDto.getCodigoCVV());
    }

    public static void fillPedidoMetodoPagofromUsuario(Pedido pedido,Usuario usuario){
        pedido.setEmisor(usuario.getEmisor());
        pedido.setNombreTarjeta(usuario.getNombreTarjeta());
        pedido.setNroTarjeta(usuario.getNroTarjeta());
        pedido.setFechaVencimiento(usuario.getFechaVencimiento());
        pedido.setCodigoCVV(usuario.getCodigoCVV());
    }

    public static void fillMedioPagoDtoFromUsuario(Usuario usuario, MedioPagoDto medioPagoDto) {
        medioPagoDto.setEmisor(usuario.getEmisor());
        medioPagoDto.setNombreTarjeta(usuario.getNombreTarjeta());
        medioPagoDto.setNroTarjeta(usuario.getNroTarjeta());
        medioPagoDto.setFechaVencimiento(usuario.getFechaVencimiento());
        medioPagoDto.setCodigoCVV(usuario.getCodigoCVV());
    }
}