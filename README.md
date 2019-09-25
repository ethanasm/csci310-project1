# CSCI310-Project1

Author: Ethan Smith
This class reads from an input file containing directed, weighted edges between cities, as well as an origin city. It creates a FlightMap which stores the graph of cities and routes. After using the compute shortest path function in FlightMap and retrieving the paths, it writes them to an output file.

Build using "ant dist"
Executed from dist folder by running "java -cp Project1.jar project1.SearchMap ../[inputFilename] ../[outputFilename]"
