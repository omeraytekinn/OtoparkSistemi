package application;

public class Test {
	
	public static void main(String[] args) {
		Autopark a = new Autopark(100,50);
		if(a.vehicleEnters("34aa34", new Time(12,20), false))
			System.err.println("Ba�ar�l�1");
		if(a.vehicleEnters("34aa34", new Time(12,20), false))
			System.err.println("Ba�ar�l�2");
		if(a.vehicleEnters("34aa34", new Time(12,20), false))
			System.err.println("Ba�ar�l�3");
		System.out.println(a);
	}
	
}
