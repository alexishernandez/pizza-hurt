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
    @Pattern(regexp="^(?=.*\\d)(?=.*[A-Z]).{6,9}$",
            message = "Mínimo 6 y máximo 9 caracteres, Al menos 1 número, " +
            "Al menos 1 alfabeto en mayúsculas, " +
            "No se permiten caracteres especiales")
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