package uy.com.curso.pizzahurt.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPasswordDto {

    private String email;

    @NotBlank
    @NotNull
    private String passwordActual;

    @NotBlank
    @NotNull
    private String passwordNueva;

    @NotBlank
    @NotNull
    private String passwordNuevaConfirmar;

    @AssertTrue(message = "La nueva contraseña no coincide con su confirmación")
    public boolean isPasswordValidar(){
        if (passwordNueva == null || passwordNueva.isBlank() ||
            passwordNuevaConfirmar == null || passwordNuevaConfirmar.isBlank()){
            return true;
        } else{
            return passwordNueva.equals(passwordNuevaConfirmar);
        }
    }
}