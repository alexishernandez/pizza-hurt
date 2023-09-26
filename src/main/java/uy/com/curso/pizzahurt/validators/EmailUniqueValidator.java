package uy.com.curso.pizzahurt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import uy.com.curso.pizzahurt.models.Usuario;
import uy.com.curso.pizzahurt.repositories.UsuarioRepository;
import uy.com.curso.pizzahurt.services.UsuarioService;

public class EmailUniqueValidator implements ConstraintValidator<EmailUniqueConstraint, String> {

    private final UsuarioRepository usuarioRepository;

    public EmailUniqueValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void initialize(EmailUniqueConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && usuarioRepository.existsUsuarioByEmail(value);
    }

}
