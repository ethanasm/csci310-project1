package project1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

import project1.FlightMap.Route;

public class SearchMap {
	
	
	private static FlightMap parseFile(String filename) {
		List<Route> routes = new ArrayList<Route>();
		char origin = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line = br.readLine();
			origin = line.charAt(0);
			
			line = br.readLine();
			while (line != null) {
				String[] routeInfo = line.split(" ");
				routes.add(new Route(routeInfo[0].charAt(0),routeInfo[1].charAt(0),Integer.parseInt(routeInfo[2])));
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + filename + " could not be found.");
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
		return new FlightMap(routes,origin);
	}
	
	public static void main(String[] args) throws IllegalArgumentException {

		if (args.length != 1) {
			System.out.print("Incorrect number of command line arguments");
			throw new IllegalArgumentException();
		}
		
		FlightMap fm = parseFile(args[0]);
		
		
	}
}
