package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Autopark;
import application.Date;
import application.Subscription;
import application.Time;

class AutoparkTest {
	Autopark a1 = new Autopark(10, 10);
	@Test
	void testAutopark() {
		assertEquals(10, a1.getHourlyFee());
	}

	@Test
	void testSearchVehicle() {
		assertNull(a1.searchVehicle("1"));
		Subscription s1 = new Subscription(new Date(12, 10, 2010), new Date(13, 12, 2012), "1");
		a1.addVehicle(s1.getVehicle());
		assertSame(s1.getVehicle(), a1.searchVehicle("1"));
	}

	@Test
	void testAddVehicle() {
		Subscription s1 = new Subscription(new Date(12, 10, 2010), new Date(13, 12, 2012), "1");
		boolean res = a1.addVehicle(s1.getVehicle());
		assertTrue(res);
		Subscription s2 = new Subscription(new Date(12, 10, 2010), new Date(13, 12, 2012), "1");
		res = a1.addVehicle(s2.getVehicle());
		assertFalse(res);
		Subscription s3 = new Subscription(new Date(12, 10, 2010), new Date(13, 9, 2010), "1");
		res = a1.addVehicle(s3.getVehicle());
		assertFalse(res);
	}
	
	@Test
	void testIsParked() {
		boolean res = a1.isParked("1");
		assertFalse(res);
		a1.vehicleEnters("1", new Time(12, 23), false);
		res = a1.isParked("1");
		assertTrue(res);
	}

	@Test
	void testVehicleEnters() {
		boolean res = a1.vehicleEnters("1", new Time(12, 23), false);
		assertTrue(res);
		res = a1.vehicleEnters("1", new Time(12, 23), false);
		assertFalse(res);
		
	}

	@Test
	void testVehicleExits() {
		boolean res = a1.vehicleExits("1", new Time(12, 23));
		assertFalse(res);
		a1.vehicleEnters("1", new Time(12, 23), false);
		res = a1.vehicleExits("1", new Time(12, 24));
		assertTrue(res);
	}


}
