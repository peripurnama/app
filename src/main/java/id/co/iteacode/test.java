package id.co.iteacode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

//	public static void main(String[] args) throws ParseException {
//		Date startDate = format.parse("06/12/2019");
//		Date endDate = format.parse("31/12/2019");
//
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("asdfgh"));
//		System.out.println("Total: " + calculateDate(startDate, endDate));
//	}
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder encryp = new BCryptPasswordEncoder();
//		String pwd = encryp.encode("admin123");
//		System.out.println("Password -> " + pwd);
//	}

	private static Long calculateDate(Date startDate, Date endDate) {
		if (endDate != null) {
			System.out.println("endDate != null");
			long diff = endDate.getTime() - startDate.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(startDate);

			long minuteForWeekend = 0;

//			System.out.println("Cal Start Date: " + format.format(calStartDate.getTime()));

			System.out.println("diffDays: " + diffDays);
			for (int i = 1; i <= (diffDays + 1); i++) {
				calStartDate.add(Calendar.DATE, 1);
				if ((calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
						|| calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					System.out.println();
					System.out.println("Cal Start Date: " + format.format(calStartDate.getTime()));
					minuteForWeekend +=1;
				}
				
			}
			
			long calculate = (diffDays + 1) - minuteForWeekend;
			return calculate;
		} else {
			System.out.println("else");
			long diff = startDate.getTime() - startDate.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			return diffDays + 1;
		}
	}
}
