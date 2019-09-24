/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1	
 * 	Description: This class represents directed edges between individual cities within
 *  a larger graph. Data includes the source and destination cities, and the cost (or
 *  edge weight) to get from the source to destination. 
 */

package project1;

public class Route {
	private City dest;
	private int cost;
	
	Route(City dest, int cost) {
		this.setDest(dest);
		this.setCost(cost);
	}

	public City getDest() {
		return dest;
	}

	public void setDest(City dest) {
		this.dest = dest;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
