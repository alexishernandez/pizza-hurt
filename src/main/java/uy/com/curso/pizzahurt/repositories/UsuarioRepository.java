package uy.com.curso.pizzahurt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.curso.pizzahurt.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findUsuarioByEmail(String email);

    public Boolean existsUsuarioByEmail(String email);

}

