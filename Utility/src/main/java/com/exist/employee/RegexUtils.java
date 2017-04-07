package com.exist.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {


	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidLandline(String landline) {
		Pattern pattern = Pattern.compile("\\d{3}-\\d{4}");

		Matcher matcher = pattern.matcher(landline);
		return matcher.matches();
	}

	public static boolean isValidMobile(String mobile) {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{3}-\\d{4}");

		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static boolean isValidDate(String date) {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}




}