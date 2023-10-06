package uy.com.curso.pizzahurt.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.curso.pizzahurt.dtos.CredencialDto;
import uy.com.curso.pizzahurt.dtos.MedioPagoDto;
import uy.com.curso.pizzahurt.dtos.RegistroDto;
import uy.com.curso.pizzahurt.dtos.RegistroRestDto;
import uy.com.curso.pizzahurt.exceptions.*;
import uy.com.curso.pizzahurt.helpers.AppHelper;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;

import java.util.Optional;

@Slf4j
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
    public Usuario createUsuarioByRegistroDto(RegistroDto registroDto) throws EmailAlreadyExistException {

        if (usuarioRepository.existsUsuarioByEmail(registroDto.getEmail())){
            log.error("Error: El e-mail ya se encuentra registrado...");
            throw new EmailAlreadyExistException("El e-mail ya se encuentra registrado..");
        }
        Usuario usuario = new Usuario();
        AppHelper.fillUsuarioFromRegistroDto(registroDto,usuario);
        usuario.setNombreCompleto(registroDto.getNombreCompleto());
        usuario.setEmail(registroDto.getEmail());
        usuario.setTelefono(registroDto.getTelefono());

        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public Usuario createUsuarioByRegistroRestDto(RegistroRestDto registro) throws EmailAlreadyExistException {
        if (usuarioRepository.existsUsuarioByEmail(registro.getEmail())){
            log.error("Error: El e-mail ya se encuentra registrado...");
            throw new EmailAlreadyExistException("El e-mail ya se encuentra registrado..");
        }
        Usuario usuario = new Usuario();
        AppHelper.fillUsuarioFromRegistroRestDto(registro,usuario);
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

    public boolean validUsernameAndPassword(CredencialDto credencialDto) {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(credencialDto.getUsername());
        if (usuario != null){
            return encoder.matches(credencialDto.getPassword(),usuario.getPassword());
        } else{
            return false;
        }
    }

    public boolean comparePasswordWithUserPassword(String password, Usuario usuario) {
        System.out.println("El valor de password es: "+ password);
        return encoder.matches(password, usuario.getPassword());
    }

    public Usuario find(long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("No se encontró el pedido"));
    }

    public Usuario findByEmail( String email){
        System.out.println("Valor de email: "+email);
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public Boolean existsUsuarioByEmail( String email){
        return usuarioRepository.existsUsuarioByEmail(email);
    }

    @Transactional
    public void addMedioPagoByEmail(String email,MedioPagoDto medioPagoDto) throws UsuarioNotFoundException, MedioPagoAlreadyExistException {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if ( usuario != null){
            if (usuario.getNroTarjeta() == null || usuario.getNroTarjeta().isEmpty()) {
                AppHelper.fillUsuarioFromMedioPago(medioPagoDto,usuario);
                usuarioRepository.save(usuario);
            } else{
                log.error("Ya existe un medio de pago asociado al usuario");
                throw new MedioPagoAlreadyExistException("Ya existe un medio de pago asociado al usuario");
            }
        } else{
            log.error("No se encontró usuario registrado el email");
            throw new UsuarioNotFoundException("No se encontró usuario registrado para el email");
        }
    }

    public MedioPagoDto getMedioPagoByEmail(String email) throws UsuarioNotFoundException, NotFoundMetodoPagoException {
        MedioPagoDto medioPagoDto= new MedioPagoDto();
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if(usuario != null){
            if (usuario.getNroTarjeta() != null) {
                AppHelper.fillMedioPagoDtoFromUsuario(usuario,medioPagoDto);
                return medioPagoDto;
            } else{
                log.error("El usuario no tiene un Medio de Pago asociado");
                throw new NotFoundMetodoPagoException("El usuario no tiene un Medio de Pago asociado");
            }
        } else{
            log.error("No se encontró usuario registrado el email");
            throw new UsuarioNotFoundException("No se encontró usuario registrado para el email");
        }
    }
}
