package com.cooksys.spring.hibernate;

import java.util.Calendar;
import java.util.Date;

public class Utils {
	public static Short getYear(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return (short) calendar.get(Calendar.YEAR);
	}
	public static Date getDateFromYear(Short y) {
		Calendar calendar = Calendar.getInstance();
		calendar.set((int)y, 1, 1, 0, 0, 0);
		return calendar.getTime();
		
	}
}
