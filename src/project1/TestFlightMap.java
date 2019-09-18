package project1;

import org.junit.Before;
import org.junit.Test;

public class TestFlightMap {

	private FlightMap tester;
	
	@Before
	public void setup() {
		tester = new FlightMap();
		tester.setOrigin('A');
		
		for (int i = 0; i < 26; i++) {
			City c = new City((char)('A' + i));
			tester.addCity(c);
		}
	}
	
	@Test
	public void testComputeRoutes3city() {
		for (int i = 0; i < 3; i++) {
			
		}
	}
	
	@Test
	public void testComputeRoutes8city() {
		
	}
	
	@Test
	public void testComputeRoutes26city() {
		
	}
	
	@Test
	public void testComputeRoutesDisconnected() {
		
	}
	
	@Test
	public void testComputeRoutesCircular() {
		
	}
	
}
