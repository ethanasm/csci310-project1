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
	
	
	private static FlightMap parseFile(String filename) {
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
		return new FlightMap(routes, origin);
	}
	
	private static void writeToFile(ArrayList<String> routes, String filename, FlightMap fm) {
		PrintWriter pw = null;
		try {
			File file = new File(filename);
			pw = new PrintWriter(file);
			pw.printf("%-15s %-24s %-15s%n", "Destination", "Flight route from " + fm.getOrigin() , "Total Cost");
			for (String s : routes) {
				String[] sp = s.split(" ");
				pw.printf("%-15s %-24s %-15s%n", sp[0], sp[1], sp[2]);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + filename + " could not be found.");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
	}
	
	public static void main(String[] args) throws IllegalArgumentException {

		if (args.length != 2) {
			System.out.print("Incorrect number of command line arguments");
			throw new IllegalArgumentException();
		}
		
		FlightMap fm = parseFile(args[0]);
		ArrayList<String> routes = fm.getRoutesFromOrigin();
		writeToFile(routes, args[1], fm);
	}
}
