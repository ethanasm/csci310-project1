package project1;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class FlightMap {
	
	List<Pair<Character,List<Character>>> adj;
	char origin;
	
	static class Route {
		char src, dest;
		int cost;
		
		Route(char src, char dest, int cost) {
			this.src = src;
			this.dest = dest;
			this.cost = cost;
		}
	};
	
	public void setOrigin(char origin) {
		this.origin = origin;
	}
	
	public char getOrigin() {
		return this.origin;
	}
	
	public FlightMap(List<Route> routes, char origin) {
		adj = new ArrayList<>();
		
		for (Route current : routes) {
			Pair<Character,List<Character>> newp = new Pair<Character,List<Character>>(Character.valueOf(current.src), new ArrayList<>());
			
		}
		
	}
}
