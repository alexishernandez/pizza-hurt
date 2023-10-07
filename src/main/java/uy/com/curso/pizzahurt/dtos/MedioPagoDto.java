package uy.com.curso.pizzahurt.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import uy.com.curso.pizzahurt.validators.VtoCreditCardConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedioPagoDto {

    @NotNull(message = "El campo emisor debe estar presente")
    @NotBlank(message = "El campo emisor no puede estar vacío")
    private String emisor;

    @NotNull(message = "El campo nombreTarjeta debe estar presente")
    @NotBlank(message = "El campo nombreTarjeta no puede estar vacío")
    private String nombreTarjeta;

    @NotNull(message = "El campo nroTarjeta debe estar presente")
    @CreditCardNumber(message = "El campo nroTarjeta no es válido")
    private String nroTarjeta;

    @NotNull(message = "El campo fechaVencimiento debe estar presente")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Fecha inválida, el formato correcto es: MM/YY")
    @VtoCreditCardConstraint
    private String fechaVencimiento;

    @NotNull(message = "El campo codigoCVV debe estar presente")
    @Pattern(regexp = "^[0-9]{3}$", message = "CódigoCVV invalido")
    private String codigoCVV;


    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


}
