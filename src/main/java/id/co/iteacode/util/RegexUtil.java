package id.co.iteacode.util;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RegexUtil {

	public static boolean isNumeric(String string) {
		return string.matches("^[-+]?\\d+(\\.\\d+)?$");
	}

	public String removeScriptTags(String message) {
		String scriptRegex = "<(/)?[ ]*script[^>]*>";
		Pattern pattern2 = Pattern.compile(scriptRegex);

		if (message != null) {
			Matcher matcher2 = pattern2.matcher(message);
			StringBuffer str = new StringBuffer(message.length());
			while (matcher2.find()) {
				matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
			}
			matcher2.appendTail(str);
			message = str.toString();
		}
		return message;
	}

	public String noHTML(String text) {
		return text.replaceAll("\\<.*?\\>", "");
	}

	public static boolean validateEmail(String email) {
		String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean validateUrl(String url) {
		String EMAIL_REGEX = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
		Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	public boolean isValidUsername(String username) {
		boolean valid = (username != null) && username.matches("[A-Za-z0-9_]+");
		return valid;
	}

	public static boolean validateLetters(String txt) {
		String regx = "^[a-zA-Z ]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();
	}

	public static boolean validateLettersAndNumber(String txt) {
		String regx = "^[A-Za-z0-9 ]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();
	}

	public boolean validateNumber(String txt) {
		String regx = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();
	}

	public boolean validateNumberDecimal(String txt) {
		String regx = "[0-9]+([,.][0-9]{1,2})?";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();
	}

	public boolean validatePassword(String passwordhere, String confirmhere, List<String> errorList) {

		Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
		Pattern lowerCasePatten = Pattern.compile("[a-z ]");
		Pattern digitCasePatten = Pattern.compile("[0-9 ]");
		errorList.clear();

		boolean flag = true;

		if (!passwordhere.equals(confirmhere)) {
			errorList.add("password and confirm password does not match !!\n");
			flag = false;
		}
		if (passwordhere.length() < 6) {
			errorList.add("password lenght must have alleast 6 character !!\n");
			flag = false;
		}
		if (!specailCharPatten.matcher(passwordhere).find()) {
			errorList.add("password must have atleast one special character !!\n");
			flag = false;
		}
		if (!UpperCasePatten.matcher(passwordhere).find()) {
			errorList.add("password must have atleast one uppercase character !!\n");
			flag = false;
		}
		if (!lowerCasePatten.matcher(passwordhere).find()) {
			errorList.add("password must have atleast one lowercase character !!\n");
			flag = false;
		}
		if (!digitCasePatten.matcher(passwordhere).find()) {
			errorList.add("password must have atleast one digit character !!\n");
			flag = false;
		}

		return flag;

	}

	public boolean isDate(String data) {
		String regex = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
				+ "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$"

				+ "|^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))/02/29)$"
				+ "|^(((19|2[0-9])[0-9]{2})/02/(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})/(0[13578]|10|12)/(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})/(0[469]|11)/(0[1-9]|[12][0-9]|30))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

	public boolean isDate2(String data) {
		String regex = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
				+ "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

	public boolean isDate3(String data) {
		String regex = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))/02/29)$"
				+ "|^(((19|2[0-9])[0-9]{2})/02/(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})/(0[13578]|10|12)/(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})/(0[469]|11)/(0[1-9]|[12][0-9]|30))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

	public static boolean validatePhone(String phone) {
		Pattern p = Pattern.compile("^\\+62\\d{11}|^\\+62\\d{13}|\\d{13}|\\d{12}|\\d{11}|\\d{10}|"
				+ "\\(\\d{3}\\)\\d{4}-?\\d{4}|\\(\\d{4}\\)\\d{5}-?\\d{5}|\\d{4}-?\\d{4}-?\\d{4}|"
				+ "|\\d{4}-?\\d{3}-?\\d{4}|\\d{4}-?\\d{2}-?\\d{4}|^\\+62\\d{3}-?\\d{4}-?\\d{4}");
		return p.matcher(phone).matches();
	}

	public boolean isNumericV2(String strNum) {
		return strNum.matches("-?\\d+(\\.\\d+)?");
	}

	public String generateToken() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		System.out.println(generatedString);
		return generatedString.toUpperCase();
	}

	// function to generate a random string of length n
	static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (alphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString().toUpperCase();
	}

	public static boolean isBlankString(String string) {
	    return string == null || string.trim().isEmpty();
	}
}
