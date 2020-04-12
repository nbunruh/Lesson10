package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	public boolean isNumberValid(String num) {
		Pattern pattern = Pattern.compile("\\d{3}/\\d{7}");
		Matcher matcher = pattern.matcher(num);
		return matcher.find();
	}
	
}
