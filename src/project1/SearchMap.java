package project1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import project1.FlightMap.Route;

@SuppressWarnings("serial")
class FileFormatException extends Exception {
	public FileFormatException() {}
}

public class SearchMap {
	
	public static void main(int argv, String[] args) throws IllegalArgumentException {
		
		if (argv != 3) {
			System.out.print("Incorrect number of command line arguments");
			throw new IllegalArgumentException();
		}
		
		FileReader fr;
		BufferedReader br;
		String line;
		char origin;
		
		try {
			fr = new FileReader(args[1]);
			br = new BufferedReader(fr);
			line = br.readLine();
			
			
			if (line == null || !Character.isLetter(line.charAt(0))) {
				br.close();
				throw new FileFormatException();
			}
			origin = line.charAt(0);
			line = br.readLine();
			while (line != null) {
				String[] routeInfo = line.split(" ");
				if (routeInfo.length != 3) {
					br.close();
					throw new FileFormatException();
				}
				
				line = br.readLine();
			}
			
			do {
				line = br.readLine();
				String[] split = line.split(" ");
			} while (line != null);
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file " + args[2] + " could not be found.");
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} catch (Exception e) {
			System.out.println("e: " + e.getMessage());
		}
		
		
		
		
		
	}
}
