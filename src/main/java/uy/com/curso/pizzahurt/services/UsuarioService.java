package uy.com.curso.pizzahurt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

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

    @Transactional
    public Usuario cambiarEstadoUsuario(Usuario usuario){
        Usuario aux = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if(aux != null) {
            Boolean nuevoEstado= !aux.getActivo();
            aux.setActivo(nuevoEstado);
            usuario.setActivo(nuevoEstado);
            usuarioRepository.save(aux);
        }
        return usuario;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findUsuarioByEmail(username);
        if (user != null) {
            return user;
        }
        else {
            throw new UsernameNotFoundException("El usuario:" + username + " no existe");
        }
    }
}
