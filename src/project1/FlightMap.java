package project1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class FlightMap {
	
	private char origin;
	private ArrayList<City> cities;
	
	
	public FlightMap(List<String> routes, char origin) {
		cities = new ArrayList<City>();
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
				
			src.addRoute(new Route(src,dest,cost));
		}
		this.origin = origin;
	}
	
	public FlightMap() {}
	
	public void setOrigin(char c) {
		this.origin = c;
	}
	
	public char getOrigin() {
		return this.origin;
	}
	
	public void addCity(City city) {
		cities.add(city);
	}
	
	public City getCity(char name) {
		for (City c : cities) {
			if (name == c.getName())
				return c;
		}
		return null;
	}
	
	public void computeRoutesFromOrigin() {
		City city = null;
		for (City c : cities) {
			if (c.getName() == origin)
				city = c;
		}
		city.setCost(0);
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
	}
	
	public List<List<City>> getAllShortestPaths() {
		List<List<City>> allPaths = new ArrayList<List<City>>();
		for (City c : cities) {
			if (c.getName() != origin) {
				List<City> path = getShortestPathTo(c);
				if (path != null)
					allPaths.add(path);
			}
		}
		return allPaths;
	}
	
	public List<City> getShortestPathTo(City dest) {
		List<City> path = new ArrayList<>();
		
		for (City before = dest; before != null; before = before.getPred()) {
			path.add(before);
		}
		
		Collections.reverse(path);
		return path;
	}
	
}