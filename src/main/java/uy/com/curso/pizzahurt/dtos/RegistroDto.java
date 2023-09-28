package uy.com.curso.pizzahurt.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.models.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {

    @NotNull
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombreCompleto;

    @Email
    @Column(unique = true)
    private String email;

    private String telefono;

    private String password;


    public void loadFromUsuario(RegistroDto registroDto,Usuario usuario){
        registroDto.setNombreCompleto(usuario.getNombreCompleto());
        registroDto.setEmail(usuario.getEmail());
        registroDto.setTelefono(usuario.getTelefono());
        registroDto.setPassword(usuario.getPassword());
    }
}
