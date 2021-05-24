package id.co.iteacode.validator.pengajuan_cuti;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidStartDateValidator.class)
@Documented
public @interface ValidStartDate {

	String message() default "Invalid";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}