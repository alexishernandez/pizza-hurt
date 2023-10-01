package uy.com.curso.pizzahurt.helpers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
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
}
