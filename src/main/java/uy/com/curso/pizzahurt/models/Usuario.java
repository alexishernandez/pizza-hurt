package uy.com.curso.pizzahurt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uy.com.curso.pizzahurt.models.common.AbstractEntity;
import uy.com.curso.pizzahurt.validators.DomicilioConstraint;
import uy.com.curso.pizzahurt.validators.EmailUniqueConstraint;
import uy.com.curso.pizzahurt.validators.TarjetaConstraint;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DomicilioConstraint
@TarjetaConstraint
public class Usuario extends AbstractEntity implements UserDetails {

    @NotNull
    @Size(min=3, max=20, message="El nombre debe tener como mínimo {min} y máximo {max} caracteres")
    private String nombreCompleto;

    @Email
    @Column(unique = true)
    private String email;

    private String telefono;

    private String password;

    private Boolean activo;

    //domicilio del usuario
    private String ciudad;

    private String barrio;

    private String calle;

    private  String nroPuerta;

    private String apto;

    private Integer codigoPostal;

    private String observaciones;

    //Tarjeta del usuario
    private String emisor;

    private String nombreTarjeta;

    @CreditCardNumber(message="El nro de tarjeta no es válido")
    private String nroTarjeta;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Fecha inválida, el formato correcto es: MM/YY")
    private String fechaVencimiento;

    @Pattern(regexp="^[0-9]{3}$", message="CódigoCVV invalido")
    private String codigoCVV;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}