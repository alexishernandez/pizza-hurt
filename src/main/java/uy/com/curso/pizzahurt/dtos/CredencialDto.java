package uy.com.curso.pizzahurt.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.curso.pizzahurt.validators.DomicilioConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredencialDto {

    @NotNull
    @NotBlank
    @Email
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
