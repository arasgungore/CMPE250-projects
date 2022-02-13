
import java.io.File;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;


/**
 * project3main class.
 * @author Aras Gungore
 *
 */
public class project3main {
	
	/**
	 * Main method.
	 * @param args Command line arguments where args[0] is the input filename/directory and args[1] is the output filename/directory.
	 * @throws Exception Exception thrown if the directories given in args or paths don't exist.
	 */
	public static void main(String args[]) throws Exception {
		
		// Decimal separator is dot instead of comma since locale is set to US English.
		Locale.setDefault(new Locale("en", "US"));
		
		// Read input.
		
		// Create a new Scanner object to read data from the input file with directory "args[0]".
		Scanner in = new Scanner(new File(args[0]));
		
		final int T = in.nextInt();
		final int V = in.nextInt();
		
		final int mecnun = Integer.parseInt(in.next().substring(1));
		final int leyla = Integer.parseInt(in.next().substring(1));
		
		// Read the adjacency list of cities in the Mecnun and Leyla's side of the country.
		DirectedGraph c_cities = new DirectedGraph(leyla);
		in.nextLine();
		for(int i = 1; i < leyla; ++i) {
			String line = in.nextLine();
			Scanner line_scanner = new Scanner(line);
			final int tail = Integer.parseInt(line_scanner.next().substring(1));
			while(line_scanner.hasNext()) {
				final int head = Integer.parseInt(line_scanner.next().substring(1));
				final int weight = Integer.valueOf(line_scanner.nextInt());
				c_cities.addArc(tail, head, weight);
			}
			line_scanner.close();
		}
		
		
		Pair<Stack<Integer>, Integer> path_time_pair = c_cities.getShortestPath(mecnun, leyla);
		Stack<Integer> path = path_time_pair.key;
		final int time_cost = path_time_pair.value;
		
		
		
		// Write output.
		
		// Create a new PrintStream object to write data to the output file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		if(path.peek() == -1) {
			in.close();		// Closes the Scanner object.
			out.print("-1\n-1");
		}
		else {
			out.print("c" + path.pop());
			while(!path.isEmpty())
				out.print(" c" + path.pop());
			out.print("\n");
			if(time_cost > T) {
				in.close();
				out.print("-1");
			}
			else {
				// Read the adjacency list of cities in the other side of the country.
				DirectedGraph d_cities = new DirectedGraph(V - leyla + 1);
				{
					String line = in.nextLine();
					Scanner line_scanner = new Scanner(line);
					line_scanner.next();
					final int tail = d_cities.getV();
					while(line_scanner.hasNext()) {
						final int head = Integer.parseInt(line_scanner.next().substring(1));
						final int weight = Integer.valueOf(line_scanner.nextInt());
						d_cities.addArc(tail, head, weight);
						d_cities.addArc(head, tail, weight);
					}
					line_scanner.close();
				}
				for(int i = 1; i < d_cities.getV(); ++i) {
					String line = in.nextLine();
					Scanner line_scanner = new Scanner(line);
					final int tail = Integer.parseInt(line_scanner.next().substring(1));
					while(line_scanner.hasNext()) {
						final int head = Integer.parseInt(line_scanner.next().substring(1));
						final int weight = Integer.valueOf(line_scanner.nextInt());
						d_cities.addArc(tail, head, weight);
						d_cities.addArc(head, tail, weight);
					}
					line_scanner.close();
				}
				
				in.close();
				
				
				final int total_weight = 2 * d_cities.getMSTweight();
				out.print(total_weight);
			}
		}
		
		out.close();	// Closes the PrintStream object.
		
	}
}