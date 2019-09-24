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
	
	private static FlightMap readFile(String filename) {
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
	
	private static void writeToFile(List<List<City>> routes, String filename, FlightMap fm) {
		PrintWriter pw = null;
		try {
			File file = new File(filename);
			pw = new PrintWriter(file);
			pw.printf("%-18s %-26s %-12s%n", "Destination", "Flight route from " + fm.getOrigin() , "Total Cost");
			for (List<City> l : routes) {
				if (l.get(l.size() - 1).getCost() == Integer.MAX_VALUE)
					continue;	
				String s = "";
				for (int i = 0; i < l.size(); i++) {
					s += l.get(i).getName();
					if (i + 1 != l.size())
						s += ", ";
				}
				pw.printf("%-18s %-26s %-12s%n", l.get(l.size() - 1).getName(), s, "$" + l.get(l.size() - 1).getCost());
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + filename + " could not be found.");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			throw new IllegalArgumentException("Two command line arguments required");
		}
		
		FlightMap fm = readFile(args[0]);
		fm.computeRoutesFromCity(fm.getOrigin());
		List<List<City>> routes = fm.getAllShortestPaths();
		writeToFile(routes, args[1], fm);
	}
}
