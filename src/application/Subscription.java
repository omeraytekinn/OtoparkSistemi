package application;

/**
 * Vehicle Subscription Operations
 * 
 * @author oNiketya
 *
 */
public class Subscription {
	private Date begin,
				 end;
	
	private SubscribedVehicle vehicle;
	
	
	/**
	 * Creates and instantiates subscription object. Creates new SubscribedVehicle object
	 * @param begin
	 * @param end
	 * @param plate
	 */
	public Subscription(Date begin, Date end, String plate) {
		this.begin = begin;
		this.end = end;
		vehicle = new SubscribedVehicle(plate, this);
	}
	
	/**
	 * Controls is subscription outdated
	 * 
	 * @return subscription validness
	 */
	public boolean isValid() {
		if(Date.getToday().isAfterThan(end))
			return false;
		return true;
	}
	
	public SubscribedVehicle getVehicle() {
		return vehicle;
	}
	
	public Date getBegin() {
		return begin;
	}
	
	public Date getEnd() {
		return end;
	}	
	
	@Override
	public String toString() {
		return getBegin() + " - " + getEnd();
	}
}
