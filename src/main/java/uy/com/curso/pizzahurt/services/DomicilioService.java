package uy.com.curso.pizzahurt.services;

import org.springframework.stereotype.Service;
import uy.com.curso.pizzahurt.dtos.DomicilioDto;
import uy.com.curso.pizzahurt.models.Usuario;

@Service
public class DomicilioService {

    public String validateDomicilio(Usuario domicilio) {
        String message="";
        boolean isEmptyDomicilioBase=(domicilio.getCiudad() == null || domicilio.getCiudad().isEmpty()) &&
                (domicilio.getBarrio() == null || domicilio.getBarrio().isEmpty()) &&
                (domicilio.getCalle()  == null || domicilio.getCalle().isEmpty()) &&
                (domicilio.getNroPuerta()== null ||domicilio.getNroPuerta().isEmpty()) &&
                (domicilio.getCodigoPostal() == null || domicilio.getCodigoPostal().isEmpty());
        boolean isFullDomicilioBase= (domicilio.getCiudad() != null && !domicilio.getCiudad().isEmpty()) &&
                (domicilio.getBarrio() != null && !domicilio.getBarrio().isEmpty()) &&
                (domicilio.getCalle()  != null && !domicilio.getCalle().isEmpty()) &&
                (domicilio.getNroPuerta() != null && !domicilio.getNroPuerta().isEmpty()) &&
                (domicilio.getCodigoPostal() != null && !domicilio.getCodigoPostal().isEmpty());

        boolean isEmptyApto = (domicilio.getApto() == null || domicilio.getApto().isEmpty());
        boolean isEmptyObservaciones = (domicilio.getObservaciones() == null || domicilio.getObservaciones().isEmpty());

        if (isFullDomicilioBase || (isEmptyDomicilioBase && isEmptyApto && isEmptyObservaciones)){
            message = "";
        } else
            message = "Faltan datos a el domicilio o no es correcto ";
        return message;

    }
}
