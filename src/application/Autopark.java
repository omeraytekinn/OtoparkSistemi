package application;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Autopark Operations
 * 
 * @author oNiketya
 *
 */
public class Autopark {
	private double hourlyFee,
				   incomeDaily;
	private SubscribedVehicle[] subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	private int numOfSubsVehicle,
				currentVehicle,
				capacity;
	
	/** Creates autopark object
	 * @param hourlyFee 
	 * @param capacity
	 */
	public Autopark(double hourlyFee, int capacity) {
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;
		subscribedVehicles = new SubscribedVehicle[10];
		parkRecords = new ArrayList<ParkRecord>();
		numOfSubsVehicle = 0;
		currentVehicle = 0;
	}

	/** Searches vehicle which has given plate number in subscribed vehicles
	 * @param plate
	 * @return vehicle thas subscribed autopark
	 */
	public SubscribedVehicle searchVehicle(String plate) {
		for(int i=0; i<numOfSubsVehicle; i++)
			if(subscribedVehicles[i].getPlate().equals(plate))
				return subscribedVehicles[i];
		return null;
	}

	/** Increases subscribes vehicle array's size
	 */
	private void enlargeVehicleArray() {
		SubscribedVehicle tmp[] = new SubscribedVehicle[subscribedVehicles.length * 2];
		for (int i = 0; i < subscribedVehicles.length; i++) {
			tmp[i] = subscribedVehicles[i];
		}
		subscribedVehicles = tmp;
	}
	
	
	/** Adds new vehicle to subscription array
	 * @param newVehicle
	 * @return Is successfully added
	 */
	public boolean addVehicle(SubscribedVehicle newVehicle) {
		if(searchVehicle(newVehicle.getPlate()) == null) {
			if(numOfSubsVehicle == subscribedVehicles.length )
				enlargeVehicleArray();
			subscribedVehicles[numOfSubsVehicle] = newVehicle;
			numOfSubsVehicle++;
			return true;
		}
		return false;
	}
	
	/** Searches vehicle in the autopark (in park record array)
	 * @param plate
	 * @return Is vehicle in autopark
	 */
	public boolean isParked(String plate) {
		for(ParkRecord record:parkRecords)
			if(record.getVehicle().getPlate().equals(plate) && !record.isExit())
				return true;
		return false;
	}
	
	
	/** Entering vehicle to the autopark recordings
	 * @param plate
	 * @param enter
	 * @param isOfficial
	 * @return Is operation successful
	 */
	public boolean vehicleEnters(String plate, Time enterTime, boolean isOfficial) {
		if(!isParked(plate) && currentVehicle < capacity) {
			if(searchVehicle(plate) != null && searchVehicle(plate).getSubscription().isValid())
				parkRecords.add(new ParkRecord(enterTime, searchVehicle(plate)));
			else if(isOfficial)
				parkRecords.add(new ParkRecord(enterTime, new OfficialVehicle(plate)));
			else 
				parkRecords.add(new ParkRecord(enterTime, new RegularVehicle(plate)));
			
			currentVehicle++;
			return true;
		}
		return false;
	}
	
	
	/** Giving exit to the car in park recordings
	 * @param plate
	 * @param exit
	 * @return Is operation successful
	 */
	public boolean vehicleExits(String plate, Time exit) {
		for(ParkRecord record:parkRecords) {
			if(record.getVehicle().getPlate().equals(plate) && !record.isExit()){
				record.setExitTime(exit);
				if(record.getVehicle().isSpecial() && record.getVehicle().getSubscription() == null && record.getParkDuration() >= 60)
						incomeDaily +=  record.getParkDuration() / 60 * hourlyFee;
				capacity--;
				return true;
			}		
		}
		return false;
	}

	@Override
	public String toString() {
		String ret = "\t\t\t\t----  Autopark  ----\n\n";
		int i=0;

		ret += "[Currently Parked Vehicles]\nPlate \t Enter Time \t Vehicle \n";
		for(ParkRecord record:parkRecords) {
			if(!record.isExit()) {
				ret += record.toString() + "\n";
				i++;
			}
		}
		ret += "\nTotal Parked Vehicles: " + i + "\n";
		
		i=0;
		ret += "\n[Park Records]\nPlate \t Enter Time \t Exit Time \t Vehicle \t\t Fee\n";
		for(ParkRecord record:parkRecords) {
			if(record.isExit()) {
				double fee = 0.0;
				if(record.getVehicle().isSpecial() && record.getVehicle().getSubscription() == null && record.getParkDuration() >= 60)
					fee = record.getParkDuration() / 60 * hourlyFee;
				ret += record.toString() + "\t" + fee + "\n";
				i++;
			}
		}
		ret += "\nTotal Records: " + i + "\n\n";
		
		ret += "[Subscribed Vehicles]\nPlate \t Begin Date \t End Date\n";
		for(i=0; i<numOfSubsVehicle; i++)
			ret += subscribedVehicles[i].getPlate() + "\t" + subscribedVehicles[i].getSubscription().getBegin() + " - " + subscribedVehicles[i].getSubscription().getEnd() + "\n";
		ret += "\nTotal Subcribed Vehicles: " +  numOfSubsVehicle;
		
		return ret;
	}
	
	public double getHourlyFee() {
		return hourlyFee;
	}
}
