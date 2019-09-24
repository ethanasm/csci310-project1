/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1
 * 	Description: Test methods for getter and setter functions in City class.
 */
package project1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestCity {
	
	private City c1;
	private City c2;

	@Before
	public void setup() {
		c1 = new City('A');
		c2 = new City('B');
		c1.setCost(500);
		c2.setCost(100);
		c1.setVisited(false);
		c2.setVisited(true);
		c1.setPred(c2);
		c2.addRoute(new Route(c1,300));
	}
	
	@Test
	public void testCityCompareTo() {
		assertEquals(1, c1.compareTo(c2));
	}
	
	@Test
	public void testCityGetName() {
		assertEquals('A', c1.getName());
	}
	
	@Test
	public void testCitySetName() {
		c1.setName('Z');
		assertEquals('Z', c1.getName());
	}
	
	@Test
	public void testCityGetCost() {
		assertEquals(500, c1.getCost());
	}
	
	@Test
	public void testCitySetCost() {
		c1.setCost(100);
		assertEquals(100, c1.getCost());
	}
	
	@Test
	public void testCityisVisited() {
		assertEquals(true, c2.isVisited());
	}
	
	@Test
	public void testCitySetVisited() {
		c2.setVisited(false);
		assertEquals(false, c2.isVisited());
	}
	
	@Test
	public void testCityGetPred() {
		assertEquals(c2, c1.getPred());
	}
	
	@Test
	public void testCitySetPred() {
		c2.setPred(c1);
		assertEquals(c1, c2.getPred());
	}
	
	@Test
	public void testCityAddRoute() {
		c1.addRoute(new Route(c2,200));
		assertEquals(1, c1.getRoutes().size());
	}
	
	@Test
	public void testCityGetRoutes() {
		assertEquals(1, c2.getRoutes().size());
	}
	
	@Test
	public void testCityGetRoute() {
		assertEquals(c1, c2.getRoute('A').getDest());
		assertEquals(null, c2.getRoute('B'));
	}
}
