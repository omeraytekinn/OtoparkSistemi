package application;

public class ParkRecord {
	private Time enterTime,
				 exitTime;
	private Vehicle vehicle;
	
	public ParkRecord(Time enterTime, Vehicle vehicle) {
		this.enterTime = enterTime;
		this.vehicle = vehicle;
	}
	
	public int getParkDuration() {
		return exitTime.getDifference(enterTime);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setExitTime(Time exitTime) {
		this.exitTime = exitTime;
	}

	public boolean isExit() {
		if(exitTime == null)
			return false;
		else
			return true;
	}
	
	@Override
	public String toString() {
		return vehicle.getPlate() + "\t-> " + enterTime.getHour() + ":" + enterTime.getMinute() + " - "
											+ exitTime.getHour() + ":" + exitTime.getMinute() + "\t"
											+ enterTime.getDifference(exitTime) + " minute";
	}
}
