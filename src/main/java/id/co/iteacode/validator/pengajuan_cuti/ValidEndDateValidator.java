package id.co.iteacode.validator.pengajuan_cuti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestAddDto;
import id.co.iteacode.util.RegexUtil;

public class ValidEndDateValidator implements ConstraintValidator<ValidEndDate, PengajuanCutiRequestAddDto> {

	@Override
	public boolean isValid(PengajuanCutiRequestAddDto value, ConstraintValidatorContext context) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		String formatStartDate = value.getFormatStartDate();
		String formatEndDate = value.getFormatEndDate();
		
		Date startDate = null;
		Date endDate = null;
		
		if (RegexUtil.isBlankString(formatEndDate)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Tanggal akhir cuti tidak boleh kosong")
					.addPropertyNode("formatEndDate")
					.addConstraintViolation();
			return false;
		}
		
		try {
			startDate = format.parse(formatStartDate);
			endDate = format.parse(formatEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar add1Day = Calendar.getInstance();
		add1Day.setTime(startDate);
		add1Day.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println("Add 1 Day: " + format.format(add1Day.getTime()));
		System.out.println("Star Date: " + format.format(startDate));
		System.out.println("!startDate.after(add1Day.getTime()): " + !startDate.after(add1Day.getTime()));
		if (!endDate.after(add1Day.getTime())) {
			System.out.println("!startDate.after(add1Day.getTime())");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Tanggal akhir cuti tidak boleh kurang tanggal awal cuti")
					.addPropertyNode("formatEndDate")
					.addConstraintViolation();
			return false;
		}

		return true;
	}

}
