package id.co.iteacode.validator.pengajuan_cuti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import id.co.iteacode.util.RegexUtil;

public class ValidStartDateValidator implements ConstraintValidator<ValidStartDate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (RegexUtil.isBlankString(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Tanggal awal cuti tidak boleh kosong")
					.addConstraintViolation();
			return false;
		}

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;
		try {
			startDate = format.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar add1Day = Calendar.getInstance();
		add1Day.setTime(new Date());
		add1Day.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("Add 1 Day: " + format.format(add1Day.getTime()));
		System.out.println("Star Date: " + format.format(startDate));
		System.out.println("!startDate.after(add1Day.getTime()): " + !startDate.after(add1Day.getTime()));
		if (!startDate.after(add1Day.getTime())) {
			System.out.println("!startDate.after(add1Day.getTime())");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Tanggal awal cuti tidak boleh kurang h+1 dari hari ini")
					.addConstraintViolation();
			return false;
		}

		return true;
	}

}
