package uy.com.curso.pizzahurt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario){
        usuario.setActivo(true);
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public void updateUsuario(Usuario usuario){
        Usuario aux = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if(aux != null) usuario.setId(aux.getId());
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> find(long id){
        return usuarioRepository.findById(id);
    }

    public Usuario findByEmail( String email){
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public Boolean existsUsuarioByEmail( String email){
        return usuarioRepository.existsUsuarioByEmail(email);
    }




}
