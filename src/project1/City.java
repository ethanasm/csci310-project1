/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1	
 * 	Description: This class represents nodes of individual cities within a larger graph.
 * 	Data includes the least cost sum of edge weights from origin, whether it has been
 *  visited, and the last visited city (all computed in FlightMap's computeShortestPath
 *  function which implements Dijkstra. Additionally, the city name and all adjacent routes.
 *  Implements comparable to be used in a priority queue for Dijkstra implementation.
 */
package project1;

import java.util.ArrayList;
import java.util.List;

import project1.Route;

public class City implements Comparable<City> {
	private char name;
	
	private int cost; // least cost to this city from origin
	private boolean visited;
	private City pred;
	private List<Route> adj;
	
	City(char c) {
		this.setName(c);
		this.setPred(null);
		this.setCost(Integer.MAX_VALUE);
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
