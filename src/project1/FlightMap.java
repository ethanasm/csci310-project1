/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1	
 * 	Description: This class represents a graph of cities and their flight routes with
 * 	costs. It stores the list of cities (which individually contain their own routes; see
 * 	City class) and the origin to calculate costs from. The computeRoutesFromCity
 * 	function uses Dijkstra's algorithm to compute the shortest cost path to each city
 *  connected to the origin. getShortestPath and getAllShortestPaths can then be used
 *  to retrieve these paths.
 */

package project1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class FlightMap {
	
	private char origin;
	private ArrayList<City> cities;
	
	public FlightMap() {
		cities = new ArrayList<City>();
	}

	
	// sets the origin city name
	public void setOrigin(char c) {
		this.origin = c;
	}
	
	
	// returns the origin city name
	public char getOrigin() {
		return this.origin;
	}
	
	
	// adds a city to the graph
	public void addCity(City city) {
		cities.add(city);
	}
	
	
	// returns a city with a given name
	public City getCity(char name) {
		for (City c : cities) {
			if (name == c.getName())
				return c;
		}
		return null;
	}
	
	
	// returns all cities in the graph
	public ArrayList<City> getCities() {
		return cities;
	}
	
	
	/*	Uses the formatted strings retrieved from SearchMap
	 * 	readFile function to add routes to the graph
	 */
	public void setRoutes(List<String> routes) {
		for (String s : routes) {
			String[] route = s.split(" ");
			char srcName = route[0].charAt(0);
			char destName = route[1].charAt(0);
			int cost = Integer.parseInt(route[2]);
			
			City src = getCity(srcName);
			if (src == null) {
				src = new City(srcName);
				addCity(src);
			}
				
			City dest = getCity(destName);
			if (dest == null) {
				dest = new City(destName);
				addCity(dest);
			}
				
			src.addRoute(new Route(dest,cost));
		}
	}
	
	
	/*	Computes the shortest cost paths to all cities connected
	 * 	to the city passed by parameter. If city requested doesn't
	 * 	exist, returns false. Implements Dijkstra's algorithm.
	 */
	public boolean computeRoutesFromCity(char cityName) {
		City city = null;
		for (City c : cities) {
			if (c.getName() == cityName)
				city = c;
		}
		
		if (city == null)
			return false;
		
		city.setCost(0); // cost from start is 0
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(city);
		city.setVisited(true);
		while (!pq.isEmpty()) {
			City next = pq.poll();
			for (Route r : next.getRoutes()) {
				City neighbor = r.getDest();
				if (!neighbor.isVisited()) {
					int newCost = next.getCost() + r.getCost();
					if (newCost < neighbor.getCost()) {
						pq.remove(neighbor);
						neighbor.setCost(newCost);
						neighbor.setPred(next);
						pq.add(neighbor);
					}
				}
			}
			next.setVisited(true);
		}
		return true;
	}

	
	/*	Returns the cities in the shortest cost path computed in the
	 * 	computeRoutesFromCity function. If path doesn't exist, returns null.
	 */
	public ArrayList<City> getShortestPathTo(City dest) {
		ArrayList<City> path = new ArrayList<>();
		
		// start at dest, and get all the predecessors back to the computed origin
		for (City before = dest; before != null; before = before.getPred()) {
			path.add(before);
		}
		
		if (path.size() == 1)
			return null;
		
		// reverse the path so it starts with origin
		Collections.reverse(path);
		return path;
	}
	
	
	/*	Returns all the shortest cost paths of cities connected to the origin
	 * 	where cost of routes were calculated from.
	 */
	public ArrayList<ArrayList<City>> getAllShortestPaths() {
		ArrayList<ArrayList<City>> allPaths = new ArrayList<ArrayList<City>>();
		for (City c : cities) {
			if (c.getName() != origin) {
				ArrayList<City> path = getShortestPathTo(c);
				if (path != null)
					allPaths.add(path);
			}
		}
		return allPaths;
	}
}