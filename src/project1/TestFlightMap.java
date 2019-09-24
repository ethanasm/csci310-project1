package project1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestFlightMap {

	private FlightMap testFm;
	private City origin;
	
	@Before
	public void setup() {
		testFm = new FlightMap();
		testFm.setOrigin('A');
		
		for (int i = 0; i < 6; i++) {
			City c = new City((char)('A' + i));
			testFm.addCity(c);
			if (i == 0)
				origin = c;
			
		}
		
		
		
		ArrayList<String> routes = new ArrayList<String>();
		routes.add("A C 200");
		routes.add("A E 230");
		routes.add("A F 500");
		routes.add("B E 600");
		routes.add("B F 150");
		routes.add("C D 300");
		routes.add("C E 400");
		routes.add("E F 800");
		testFm.setRoutes(routes);
	}
	
	@Test
	public void testFlightMapGetOrigin() {
		assertEquals('A', testFm.getOrigin());
	}
	
	@Test
	public void testFlightMapSetOrigin() {
		testFm.setOrigin('B');
		assertEquals('B', testFm.getOrigin());
	}
	
	@Test
	public void testFlightMapAddCity() {
		City cityG = new City('G');
		testFm.addCity(cityG);
		assertEquals(cityG, testFm.getCity('G'));
	}
	
	@Test
	public void testFlightMapGetCity() {
		assertEquals(origin, testFm.getCity('A'));
	}
	
	@Test
	public void testFlightMapGetCities() {
		assertEquals(6, testFm.getCities().size());
	}
	
	@Test
	public void testFlightMapSetRoutes() {
		City cityA = testFm.getCity('A');
		City cityB = testFm.getCity('B');
		City cityD = testFm.getCity('D');
		
		assertEquals(0, cityD.getRoutes().size());
		assertEquals(3, cityA.getRoutes().size());
		assertEquals(150, cityB.getRoute('F').getCost());
	}
	
	@Test
	public void testFlightMapComputeRoutes() {
		testFm.computeRoutesFromCity(testFm.getOrigin());
		
		assertEquals(500, testFm.getCity('D').getCost());
		assertEquals(Integer.MAX_VALUE, testFm.getCity('B').getCost());
	}
	
	@Test
	public void testFlightMapGetShortestPath() {
		testFm.computeRoutesFromCity(testFm.getOrigin());
		ArrayList<City> pathD = testFm.getShortestPathTo(testFm.getCity('D'));
		ArrayList<City> pathB = testFm.getShortestPathTo(testFm.getCity('B'));
		
		assertEquals(3, pathD.size());
		assertEquals(null, pathB);
	}
	
	@Test
	public void testFlightMapGetAllShortestPaths() {
		testFm.computeRoutesFromCity(testFm.getOrigin());
		assertEquals(4, testFm.getAllShortestPaths().size());
	}
}
