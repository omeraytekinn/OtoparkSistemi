package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Time Operations
 * 
 * @author oNiketya
 *
 */
public class Time {
	private int hour,
				minute;

	/** Creates and instantiates time object
	 * @param hour
	 * @param minute
	 */
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	
	/**
	 * Calculates difference between another time
	 * 
	 * @param other
	 * @return time difference in minutes
	 */
	public int getDifference(Time other) {
		int diffHour,
			diffMinute;
		diffHour = other.getHour() - hour;
		diffMinute = other.getMinute() - minute;
		if( diffHour < 0 ) {
			diffHour *= -1;
			diffMinute *= -1;
		}
		return diffHour*60 + diffMinute;
	}
	
	/** Getting current time in Time object format
	 * @return current time
	 */
	public static Time getCurTime() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new java.util.Date());
		return new Time(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	
	public String toString() {
		return hour + ":" + minute;
	}
}
