package application;

/**
 * Subscribed Vehicle Informations
 * 
 * @author oNiketya
 *
 */
public class SubscribedVehicle implements Vehicle {
	private String plate;
	private Subscription subscription;
	
	public SubscribedVehicle(String plate, Subscription subscription) {
		this.plate = plate;
		this.subscription = subscription;
	}

	@Override
	public String getPlate() { return plate; }

	@Override
	public Subscription getSubscription() { return subscription; }

	@Override
	public boolean isSpecial() { return true; }
	
	@Override
	public String toString() {
		return "Subscribed Vehicle";
	}
}
