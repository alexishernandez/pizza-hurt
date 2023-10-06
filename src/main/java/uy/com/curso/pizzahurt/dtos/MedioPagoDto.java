package uy.com.curso.pizzahurt.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedioPagoDto {

    private String emisor;

    private String nombreTarjeta;

    @CreditCardNumber(message = "El nro de tarjeta no es válido")
    private String nroTarjeta;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Fecha inválida, el formato correcto es: MM/YY")
    private String fechaVencimiento;

    @Pattern(regexp = "^[0-9]{3}$", message = "CódigoCVV invalido")
    private String codigoCVV;


    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


}
