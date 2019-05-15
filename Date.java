package application;

import java.util.Calendar;

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
		if(other.year < year)
			return true;
		else if(other.month < year)
			return true;
		else if(other.day < day)
			return true;
		return false;		
	}
	
	public boolean isBeforeThan(Date other) {
		if(other.year > year)
			return true;
		else if(other.month > year)
			return true;
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
		return new Date(Calendar.DAY_OF_MONTH,Calendar.MONTH,Calendar.YEAR);
	}
}
