package id.co.iteacode.validator.pengajuan_cuti;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestAddDto;


public class ValidFileValidator implements ConstraintValidator<ValidFile, Object> {

	public static final String PNG_MIME_TYPE = "image/png";
	public static final String JPG_MIME_TYPE = "image/jpeg";
	public static final long TEN_MB_IN_BYTES = 30485760;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value instanceof PengajuanCutiRequestAddDto) {
			PengajuanCutiRequestAddDto dto = (PengajuanCutiRequestAddDto) value;
			MultipartFile file = dto.getFile();
			
			if (!file.isEmpty()) {
				if (!PNG_MIME_TYPE.equalsIgnoreCase(file.getContentType())) {

					if (!JPG_MIME_TYPE.equalsIgnoreCase(file.getContentType())) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate("Format file tidak valid").addPropertyNode("file")
								.addConstraintViolation();
						return false;
					}

				}
			}

			else if (file.getSize() > TEN_MB_IN_BYTES) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Ukuran file tidak boleh lebih dari 3 MB")
						.addPropertyNode("file").addConstraintViolation();
				return false;
			}
		} 

		return true;
	}

}
