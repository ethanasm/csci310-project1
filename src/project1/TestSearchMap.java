/*	Author: Ethan Smith
 * 	Project: CSCI310 Project 1
 * 	Description: Test methods for SearchMap class.
 */
package project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class TestSearchMap {

	private static String testInputName = "testinput.txt";
	private static String testOutputName = "testoutput.txt";
	private ArrayList<String> outputContent;
	private FlightMap testFm;
		
	@Before
	public void setup() {
		File testinput = new File(testInputName);
		
		PrintWriter writeInput = null;
		try {
			writeInput = new PrintWriter(testinput);
			writeInput.write("P\n" + 
					"P W 200\n" + 
					"P R 300\n" + 
					"R X 200\n" + 
					"Q X 375\n" + 
					"W S 250\n" + 
					"S T 300\n" + 
					"T W 350\n" + 
					"W Y 500\n" + 
					"Y Z 450\n" + 
					"Y R 600");
		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe: " + fnfe.getMessage());
		} finally {
			writeInput.flush();
			if (writeInput != null)
				writeInput.close();
		}
		outputContent = new ArrayList<String>();
		outputContent.add("Destination        Flight route from P        Total Cost");
		outputContent.add("W                  P, W                       $200");
		outputContent.add("R                  P, R                       $300");
		outputContent.add("X                  P, R, X                    $500");
		outputContent.add("S                  P, W, S                    $450");
		outputContent.add("T                  P, W, S, T                 $750");
		outputContent.add("Y                  P, W, Y                    $700");
		outputContent.add("Z                  P, W, Y, Z                 $1150");
		testFm = SearchMap.readFile(testInputName);
	}
	
	@AfterClass
	public static void deleteTestFiles() {
		File input = new File(testInputName);
		File output = new File(testOutputName);
		input.delete();
		output.delete();
	}
	
	
	@Test
	public void testSearchMapReadFile() {
		ArrayList<City> cities = testFm.getCities();
		assertEquals(9, cities.size());
		int totalRoutes = 0;
		for (City c : cities)
			totalRoutes += c.getRoutes().size();
		assertEquals(10, totalRoutes);
	}
	
	
	@Test
	public void testSearchMapWrite() {
		testFm.computeRoutesFromCity(testFm.getOrigin());
		SearchMap.writeToFile(testFm.getAllShortestPaths(), testOutputName, testFm);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(testOutputName));
			for (String s : outputContent) {
				String nextLine = br.readLine();
				assertTrue(nextLine.contains(s));
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe: " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			}
		}
	}
}
