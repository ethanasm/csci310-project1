/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1
 * 	Description: Test methods for getter and setter functions in Route class.
 */
package project1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestRoute {
	
	private City c1;
	private City c2;
	private City c3;
	private Route testRoute;

	@Before
	public void setup() {
		c1 = new City('A');
		c2 = new City('B');
		c3 = new City('C');
		testRoute = new Route(c1, c2, 500);
	}
	
	@Test
	public void testRouteGetDest() {
		assertEquals(c2, testRoute.getDest());
	}
	
	@Test
	public void testRouteSetDest() {
		testRoute.setDest(c3);
		assertEquals(c3, testRoute.getDest());
	}
	
	@Test
	public void testRouteGetCost() {
		assertEquals(500, testRoute.getCost());
	}
	
	@Test
	public void testRouteSetCost() {
		testRoute.setCost(1000);
		assertEquals(1000, testRoute.getCost());
	}
	
	@Test
	public void testRoutesGetSrc() {
		assertEquals(c1, testRoute.getSrc());
	}
	
	@Test
	public void testRoutesSetSrc() {
		testRoute.setSrc(c3);
		assertEquals(c3, testRoute.getSrc());
	}
	
}
