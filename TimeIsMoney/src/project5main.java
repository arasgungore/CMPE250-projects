
import java.io.File;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * project5main class.
 * @author Aras Gungore
 *
 */
public class project5main {
	
	/**
	 * A dynamically programmed function that returns the maximum possible profit from given ArrayList of Offers.
	 * The ArrayList of Offers must be sorted according to ascending end time beforehand.
	 * Time complexity: O(N * log(N))
	 * @param offers An ArrayList which holds the Offers.
	 * @param n Total number of Offers.
	 * @return Maximum profit attainable from given ArrayList of Offers.
	 */
	public static int getMaxProfit1(final ArrayList<Offer> offers, final int n) {
		int[] maxProfitTable = new int[n];		// Filled with zeros by Java default.
		maxProfitTable[0] = offers.get(0).getProfit();
		
		for(int i = 1; i < n; ++i) {
			// Find profit including the current offer.
	        int includedProfit = offers.get(i).getProfit();
	        final int startTime = offers.get(i).getStartTime();
		    for(int j = i - 1; j >= 0; --j)
		        if(offers.get(j).getEndTime() <= startTime) {
		        	includedProfit += maxProfitTable[j];
		        	break;
		        }
	        
	        // Store the maximum of the included and excluded profits.
	        maxProfitTable[i] = Math.max(includedProfit, maxProfitTable[i - 1]);
		}
		
		return maxProfitTable[n - 1];
	}
	
	/**
	 * A recursive function that returns the maximum possible profit from given ArrayList of Offers.
	 * The ArrayList of Offers must be sorted according to ascending end time beforehand.
	 * Time complexity: O(N * 2^N)
	 * @param offers An ArrayList which holds the Offers.
	 * @param n Given ArrayList size.
	 * @return Maximum profit attainable from given ArrayList of Offers.
	 */
	public static int getMaxProfit2(final ArrayList<Offer> offers, final int n) {
		if(n == 1)		// Base case.
			return offers.get(0).getProfit();
		
	    // Find profit when the current offer is included.
	    int includedProfit = offers.get(n - 1).getProfit();
	    final int startTime = offers.get(n - 1).getStartTime();
	    for(int j = n - 1; j >= 0; --j)
	        if(offers.get(j).getEndTime() <= startTime) {
	        	includedProfit += getMaxProfit2(offers, j + 1);
	        	break;
	        }
	    
	    // Find profit when the current offer is excluded.
	    final int excludedProfit = getMaxProfit2(offers, n - 1);
	    
	    return Math.max(includedProfit, excludedProfit);
	}
	
	
	
	/**
	 * Main method.
	 * @param args Command line arguments where args[0] is the input filename/directory and args[1] is the output filename/directory.
	 * @throws Exception Exception thrown if the directories given in args or paths don't exist.
	 */
	public static void main(String args[]) throws Exception {
		
		// Read input.
		
		// Create a new Scanner object to read data from the input file with directory "args[0]".
		Scanner in = new Scanner(new File(args[0]));
		
		
		final char[] prod_types = in.nextLine().replaceAll(" ", "").toCharArray();
		final int totalOffer = prod_types.length;
		
		
		int[] process_times_A = new int[totalOffer];
		int[] process_times_B = new int[totalOffer];
		int[] factory_gains = new int[totalOffer];
		int[] arrival_times = new int[totalOffer];
		
		for(int i = 0; i < totalOffer; ++i)
			process_times_A[i] = in.nextInt();
		for(int i = 0; i < totalOffer; ++i)
			process_times_B[i] = in.nextInt();
		for(int i = 0; i < totalOffer; ++i)
			factory_gains[i] = in.nextInt();
		for(int i = 0; i < totalOffer; ++i)
			arrival_times[i] = in.nextInt();
		
		
		in.close();		// Closes the Scanner object.
		
		
		
		// List of objects containing start time, end time, and profit of each order. It is sorted based on end time.
		ArrayList<Offer> offers = new ArrayList<Offer>(totalOffer);
		
		for(int i = 0; i < totalOffer; ++i) {
			final int startTime = arrival_times[i];
			final int processTime = prod_types[i] == 's' ? process_times_A[i] : process_times_B[i];
			final int endTime = startTime + processTime;
			final int profit = factory_gains[i];
			offers.add(new Offer(startTime, endTime, profit));
		}
		
		Collections.sort(offers);
		
		
		// Algorithm 1: Find Maximum Profit using Dynamic Programming
		// Time complexity: O(N * log(N))
		
		final int maxProfit = getMaxProfit1(offers, totalOffer);
		
		
		// Algorithm 2: Find Maximum Profit using Recursion
		// Time complexity: O(N * 2^N)
		
		// final int maxProfit = getMaxProfit2(offers, totalOffer);
		
		
		
		// Write output.
		
		// Create a new PrintStream object to write data to the output file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		out.print(maxProfit);
		
		out.close();	// Closes the PrintStream object.
		
	}
}