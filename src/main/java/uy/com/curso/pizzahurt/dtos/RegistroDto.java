package uy.com.curso.pizzahurt.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.models.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {

    @NotNull
    @NotBlank
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombreCompleto;

    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    private String telefono;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String passwordConfirmar;

    @AssertTrue(message = "La contraseña no coincide con su confirmación")
    public boolean isPasswordValidar(){
        if (password == null || password.isBlank() || passwordConfirmar == null || passwordConfirmar.isBlank()){
            return true;
        } else{
            return password.equals(passwordConfirmar);
        }
    }

}
