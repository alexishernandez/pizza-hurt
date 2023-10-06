package uy.com.curso.pizzahurt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VtoCreditCardValidator implements ConstraintValidator<VtoCreditCardConstraint, String> {
    @Override
    public void initialize(VtoCreditCardConstraint vtoCreditCarConstraint) {
        ConstraintValidator.super.initialize(vtoCreditCarConstraint);
    }

    @Override
    public boolean isValid(String vto, ConstraintValidatorContext context) {
        if (vto ==null || vto.isBlank())
            return true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        Date expiry = null;
        try {
            expiry = simpleDateFormat.parse(vto);
        } catch (ParseException e) {
           return false;
        }
        return expiry.after(new Date());
    }
}
