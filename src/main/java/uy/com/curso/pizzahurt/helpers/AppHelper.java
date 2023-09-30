package uy.com.curso.pizzahurt.helpers;

import org.springframework.security.crypto.password.PasswordEncoder;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.models.Usuario;

public class AppHelper {

    private final PasswordEncoder passwordEncoder;

    public AppHelper(PasswordEncoder passwordEncoder) {
        super();
        this.passwordEncoder = passwordEncoder;
     }

    public void loadUsuarioFromRegistroDto(RegistroDto registroDto, Usuario usuario){
        registroDto.setNombreCompleto(usuario.getNombreCompleto());
        registroDto.setEmail(usuario.getEmail());
        registroDto.setTelefono(usuario.getTelefono());
        registroDto.setPassword(usuario.getPassword());
    }
}
