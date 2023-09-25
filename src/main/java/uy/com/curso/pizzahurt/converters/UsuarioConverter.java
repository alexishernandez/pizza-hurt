package uy.com.curso.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.services.UsuarioService;

@Component
public class UsuarioConverter implements Converter<String, Usuario> {

    private UsuarioService usuarioService;

    public UsuarioConverter(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario convert(String email) {
        return usuarioService.findByEmail(email);
    }
}
