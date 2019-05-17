package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
	private int day,
				month,
				year;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public boolean isAfterThan(Date other) {
		if(year > other.year)
			return true;
		else if(year < other.year)
			return false;
		else if(month > other.month)
			return true;
		else if(month < other.month)
			return false;
		else if(other.day < day)
			return true;
		return false;		
	}
	
	public boolean isBeforeThan(Date other) {
		if(year > other.year)
			return true;
		else if(year > other.year)
			return false;
		else if(month > other.month)
			return true;
		else if(month > other.month)
			return false;
		else if(other.day > day)
			return true;
		return false;
	}
	
	public boolean isEqualsWith(Date other) {
		if(other.year == year && other.month == month && other.day == day)
			return true;
		return false;
	}
	
	public static Date getToday() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new java.util.Date());
		return new Date(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
	}
	
	public String toString() {
		return day + "/" + month + "/" + year;
	}
}
