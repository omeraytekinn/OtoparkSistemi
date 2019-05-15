package application;

public class Subscription {
	private Date begin,
				 end;
	
	private SubscribedVehicle vehicle;
	
	public Subscription(Date begin, Date end, String plate) {
		this.begin = begin;
		this.end = end;
		vehicle = new SubscribedVehicle(plate, this);
	}
	
	public boolean isValid() {
		if(Date.getToday().isAfterThan(end))
			return false;
		return true;
	}
	
	public SubscribedVehicle getVehicle() {
		return vehicle;
	}
	
}
