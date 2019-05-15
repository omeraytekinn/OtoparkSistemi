package application;

import java.util.ArrayList;
import java.util.Calendar;

public class Autopark {
	private double hourlyFee,
				   incomeDaily;
	private SubscribedVehicle[] subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	private int numOfSubsVehicle,
				currentVehicle,
				capacity;
	
	public Autopark(double hourlyFee, int capacity) {
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;
		subscribedVehicles = new SubscribedVehicle[10];
		parkRecords = new ArrayList<ParkRecord>();
		numOfSubsVehicle = 0;
		currentVehicle = 0;
	}

	public SubscribedVehicle searchVehicle(String plate) {
		for(int i=0; i<numOfSubsVehicle; i++)
			if(subscribedVehicles[i].getPlate().equals(plate))
				return subscribedVehicles[i];
		return null;
	}

	private void enlargeVehicleArray() {
		subscribedVehicles = new SubscribedVehicle[subscribedVehicles.length * 2];
	}
	
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
	
	public boolean isParked(String plate) {
		for(ParkRecord record:parkRecords)
			if(record.getVehicle().getPlate() == plate && !record.isExit())
				return true;
		return false;
	}
	
	public boolean vehicleEnters(String plate, Time enter, boolean isOfficial) {
		if(!isParked(plate) && currentVehicle < capacity) {
			if(searchVehicle(plate).getSubscription().isValid()) 
				parkRecords.add(new ParkRecord(enter, searchVehicle(plate)));
			else if(isOfficial)
				parkRecords.add(new ParkRecord(enter, new OfficialVehicle(plate)));
			else
				parkRecords.add(new ParkRecord(enter, new RegularVehicle(plate)));
			currentVehicle++;
			return true;
		}
		return false;
	}
	
	public boolean vehicleExits(String plate, Time exit) {
		for(ParkRecord record:parkRecords) {
			if(record.getVehicle().getPlate() == plate && !record.isExit()){
				record.setExitTime(exit);
				if(record.getVehicle().isSpecial() && record.getVehicle().getSubscription() == null)
					if(record.getParkDuration() >= 60) 
						incomeDaily +=  record.getParkDuration() / 60 * hourlyFee;
				capacity--;
				return true;
			}		
		}
		return false;
	}
	
	public String toString() {
		
	}
	
}
