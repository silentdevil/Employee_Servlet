
package com.exist.employee;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DatePicker {

	public static Date parseDate(String date, String id) throws Exception {
		
		while(!RegexUtils.isValidDate(date) && id.equals("EMPTY_NOT_ALLOWED")) {
			System.out.println("Not valid date format");
			date = InputManager.enterString("BirthDate [YYYY-MM-DD]", "EMPTY_NOT_ALLOWED");
		}

        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
}