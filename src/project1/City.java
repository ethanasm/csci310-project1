package project1;

import java.util.ArrayList;
import java.util.List;

import project1.Route;

public class City implements Comparable<City> {
	private char name;
	private int cost = Integer.MAX_VALUE;
	private boolean visited;
	private City pred;
	private List<Route> adj;
	
	City(char c) {
		this.name = c;
		this.pred = null;
		this.adj = new ArrayList<>();
	}
	
	public int compareTo(City city2) {
		return Integer.compare(this.cost, city2.cost);
	}
	
	public char getName() {
		return name;
	}
	
	public void setName(char n) {
		name = n;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int c) {
		cost = c;
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public City getPred() {
		return pred;
	}

	public void setPred(City pred) {
		this.pred = pred;
	}

	public void addRoute(Route r) {
		adj.add(r);
	}
	
	public List<Route> getRoutes() {
		return adj;
	}
	
}
