package application;

/**
 * Autopark Recording Opertations
 * 
 * @author oNiketya
 *
 */
public class ParkRecord {
	private Time enterTime,
				 exitTime;
	private Vehicle vehicle;
	
	/** 
	 * Creates a park record which has vehicle and enter time operations
	 * 
	 * @param enterTime
	 * @param vehicle
	 */
	public ParkRecord(Time enterTime, Vehicle vehicle) {
		this.enterTime = enterTime;
		this.vehicle = vehicle;
	}
	
	/**
	 * Calculates difference between enter and exit time
	 * 
	 * @return vehicle park duration
	 */
	public int getParkDuration() {
		return exitTime.getDifference(enterTime);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setExitTime(Time exitTime) {
		this.exitTime = exitTime;
	}
	
	/**
	 * Gives is vehicle in autopark information 
	 * 
	 * @return is vehicle exit
	 */
	public boolean isExit() {
		if(exitTime == null)
			return false;
		else
			return true;
	}
	
	@Override
	public String toString() {
		if(exitTime == null)
			return vehicle.getPlate() + "\t\t" + enterTime + "\t" + "  "+ vehicle;
		else
			return vehicle.getPlate() + "\t\t" + enterTime + "\t\t" + exitTime +"  "+ vehicle;
	}
}
