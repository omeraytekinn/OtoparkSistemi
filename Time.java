package application;

public class Time {
	private int hour,
				minute;

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
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

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
}
