/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1
 * 	Description: Test methods for getter and setter functions in Route class.
 */
package project1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestRoute {
	;
	private City c1;
	private City c2;
	private Route testRoute;

	@Before
	public void setup() {
		c1 = new City('A');
		c2 = new City('B');
		testRoute = new Route(c2, 500);
	}
	
	@Test
	public void testRouteGetDest() {
		assertEquals(c2, testRoute.getDest());
	}
	
	@Test
	public void testRouteSetDest() {
		testRoute.setDest(c1);
		assertEquals(c1, testRoute.getDest());
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
}
