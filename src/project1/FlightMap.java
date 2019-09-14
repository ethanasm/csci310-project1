package project1;
import java.util.ArrayList;
import java.util.List;


public class FlightMap {
	
	private char origin;
	private ArrayList<ArrayList<Route>> adj;;
	
	private class Route {
		char dest;
		int cost;
		
		Route(char dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
	
	
	public FlightMap(List<String> routes, char origin) {
		adj = new ArrayList<ArrayList<Route>>();
		for (int i = 0; i < 52; i++) {
			adj.add(null);
		}
		for (int i = 0; i < routes.size(); i++) {
			String[] route = routes.get(i).split(" ");
			char src = route[0].charAt(0);
			char dest = route[1].charAt(0);
			int cost = Integer.parseInt(route[2]);
			int index = charToInteger(src);
			if (adj.get(index) == null) {
				adj.set(index, new ArrayList<>());
			}
			adj.get(index).add(new Route(dest, cost));
		}
		this.origin = origin;
	}
	
	public void setOrigin(char c) {
		this.origin = c;
	}
	
	public char getOrigin() {
		return this.origin;
	}
	
	public ArrayList<String> getRoutesFromOrigin() {
		boolean[] visited = new boolean[52];
		return DFS(charToInteger(this.origin), new ArrayList<String>(), 0, Character.toString(origin), visited);
	}
	
	private ArrayList<String> DFS(int v, ArrayList<String> routes, int cost, String route, boolean[] visited) {
		
		visited[v] = true;
		if (this.adj.get(v) == null) {
			return routes;
		}
		
		for (Route r : this.adj.get(v)) {
			int w = charToInteger(r.dest);
			if (!visited[w]) {
				String prev = route;
				route += ("," + r.dest);
				cost += r.cost;
				routes.add(r.dest + " " + route + " $" + cost);
				DFS(w,routes,cost,route,visited);
				cost -= r.cost;
				route = prev;
			}
		}
		return routes;
	}
	
	private int charToInteger(char c) {
		boolean isUpper = Character.isUpperCase(c);
		return isUpper ? c - 'A' + 26 : c - 'a';
	}
}
