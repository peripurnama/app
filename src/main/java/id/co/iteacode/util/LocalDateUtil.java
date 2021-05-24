package id.co.iteacode.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class LocalDateUtil {


	public LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
	    return new java.sql.Timestamp(
	      dateToConvert.getTime()).toLocalDateTime();
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	 
	public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public String formatDate(LocalDate localDate) {
		if (localDate != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return localDate.format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatDate(Date date) {
		if (date != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return convertToLocalDateViaInstant(date).format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatDate2(Date date) {
		if (date != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
			LocalDateTime localDateTime = date.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDateTime();
			return localDateTime.format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatTime(Date date) {
		if (date != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			return convertToLocalDateTimeViaMilisecond(date).format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatDate(LocalDate localDate, String pattern) {
		if (localDate != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
			return localDate.format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatDate(Date date, String pattern) {
		if (date != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
			return convertToLocalDateViaInstant(date).format(dateTimeFormatter);
		}
		return "";
	}
	
	public String formatTime(Date date, String pattern) {
		if (date != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
			return convertToLocalDateViaInstant(date).format(dateTimeFormatter);
		}
		return "";
	}
	
	public Date asDate(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate localDate = LocalDate.parse(date,formatter);
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public String asLocalDateTime(Date date, String format) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter);
	}
	
	public Date localTimeToDate(String strTime) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				   .parseCaseInsensitive().appendPattern("hh:mm a").toFormatter(Locale.US);
		
		LocalTime lt = LocalTime.parse(strTime,formatter);
		
		Instant instant = lt.atDate(LocalDate.now()).
		        atZone(ZoneId.systemDefault()).toInstant();
		
		Date time = Date.from(instant);
		
		return time;
	}

}
