package uy.com.curso.pizzahurt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

    @Transactional
    public Usuario createUsuario(Usuario usuario){
        usuario.setActivo(true);
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public Usuario createUsuarioByRegistroDto(RegistroDto registroDto){
        Usuario usuario = new Usuario();
        AppHelper.fillUsuarioFromRegistroDto(registroDto,usuario);
        usuario.setNombreCompleto(registroDto.getNombreCompleto());
        usuario.setEmail(registroDto.getEmail());
        usuario.setTelefono(registroDto.getTelefono());

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

    @Transactional
    public void cambiarPasswordUsuario(String nuevaPassword, Usuario usuario){
        usuario.setPassword(encoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }

    //Consultas

    public boolean comparePasswordWithUserPassword(String password, Usuario usuario) {
        System.out.println("El valor de password es: "+ password);
        return encoder.matches(password, usuario.getPassword());
    }

    public Optional<Usuario> find(long id){
        return usuarioRepository.findById(id);
    }

    public Usuario findByEmail( String email){
        System.out.println("Valor de email: "+email);
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public Boolean existsUsuarioByEmail( String email){
        return usuarioRepository.existsUsuarioByEmail(email);
    }


}
