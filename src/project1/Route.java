package project1;

public class Route {
	private City src;
	private City dest;
	private int cost;
	
	Route(City src, City dest, int cost) {
		this.setSrc(src);
		this.dest = dest;
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

	public City getSrc() {
		return src;
	}

	public void setSrc(City src) {
		this.src = src;
	}
}
