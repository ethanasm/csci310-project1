/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1	
 * 	Description: This class reads from an input file containing directed, weighted edges
 * 	between cities, as well as an origin city. It creates a FlightMap which stores the
 * 	graph of cities and routes. After using the compute shortest path function in
 * 	FlightMap and retrieving the paths, it writes them to an output file.
 */

package project1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class SearchMap {
	
	
	/*	Reads from a file containing information about edges in the FlightMap graph
	 * 	and uses the setRoutes function in FlightMap to store them. Returns this
	 * 	FlightMap object.
	 */
	public static FlightMap readFile(String filename) {
		List<String> routes = new ArrayList<String>();
		char origin = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			
			String line = br.readLine();
			origin = line.charAt(0);
			
			line = br.readLine();
			while (line != null) {
				routes.add(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + filename + " could not be found.");
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			} 
			
		}
		FlightMap fm = new FlightMap();
		fm.setOrigin(origin);
		fm.setRoutes(routes);
		return fm;
	}
	
	
	/*	Writes a formatted list of all the shortest cost paths computed from the origin to
	 * 	an output file.
	 */
	public static void writeToFile(ArrayList<ArrayList<City>> routes, String filename, FlightMap fm) {
		PrintWriter pw = null;
		try {
			File file = new File(filename);
			pw = new PrintWriter(file);
			pw.printf("%-18s %-26s %-12s%n", "Destination", "Flight route from " + fm.getOrigin() , "Total Cost");
			
			for (List<City> l : routes) {
				// if city not reached by graph algorithm, don't print
				if (l.get(l.size() - 1).getCost() == Integer.MAX_VALUE)
					continue;
				
				// else get the path from origin 
				String s = "";
				for (int i = 0; i < l.size(); i++) {
					s += l.get(i).getName();
					if (i + 1 != l.size())
						s += ", ";
				}
				
				// print the formatted city name, path, and cost of path
				pw.printf("%-18s %-26s %-12s%n", l.get(l.size() - 1).getName(), s, "$" + l.get(l.size() - 1).getCost());
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + filename + " could not be found.");
		} finally {
			pw.flush();
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	
	/* Uses the filenames supplied at command line to read from a list of routes and
	 * output the shortest cost paths from the origin to an output file.
	 */
	public static void main(String[] args) {
		
		if (args.length != 2) {
			throw new IllegalArgumentException("Two command line arguments required");
		}
		
		FlightMap fm = readFile(args[0]);
		fm.computeRoutesFromCity(fm.getOrigin());
		ArrayList<ArrayList<City>> routes = fm.getAllShortestPaths();
		writeToFile(routes, args[1], fm);
	}
}
