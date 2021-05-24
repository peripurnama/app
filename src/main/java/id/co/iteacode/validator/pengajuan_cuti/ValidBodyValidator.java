package id.co.iteacode.validator.pengajuan_cuti;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import id.co.iteacode.util.RegexUtil;


public class ValidBodyValidator implements ConstraintValidator<ValidBody, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(RegexUtil.isBlankString(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Keterangan tidak boleh kosong")
                .addConstraintViolation();
            return false;
        }
        
        if(value.length() < 5) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Keterangan tidak boleh kurang dari 5 karakter")
                .addConstraintViolation();
            return false;
        }
        
        if(value.length() > 255) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Keterangan tidak boleh lebih dari 255 karakter")
                .addConstraintViolation();
            return false;
        }
        
        return true;
	}

}
