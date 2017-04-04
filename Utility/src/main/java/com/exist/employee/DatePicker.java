
package com.exist.employee;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DatePicker {

	 public static Date parseDate(String date) {
     try {
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
     } catch (Exception e) {
         return null;
     }

  }
}