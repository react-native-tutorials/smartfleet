package com.smartlife.smartfleet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date getSystemDate() {
		return new Date(System.currentTimeMillis());
	}

	public static Date getDate(String date, String pattern) {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat();
			formatDate.applyPattern(pattern);
			return formatDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}

}
