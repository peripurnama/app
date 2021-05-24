package id.co.iteacode.validator.pengajuan_cuti;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidTitleValidator.class)
@Documented
public @interface ValidTitle {
	String message() default "Invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
