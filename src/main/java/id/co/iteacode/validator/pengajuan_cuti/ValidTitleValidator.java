package id.co.iteacode.validator.pengajuan_cuti;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import id.co.iteacode.util.RegexUtil;


public class ValidTitleValidator implements ConstraintValidator<ValidTitle, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(RegexUtil.isBlankString(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Judul tidak boleh kosong")
                .addConstraintViolation();
            return false;
        }
        
        if(value.length() < 5) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Judul tidak boleh kurang dari 5 karakter")
                .addConstraintViolation();
            return false;
        }
        
        if(value.length() > 100) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Judul tidak boleh lebih dari 100 karakter")
                .addConstraintViolation();
            return false;
        }
        
        return true;
	}

}
