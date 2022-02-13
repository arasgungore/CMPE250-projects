
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


/**
 * project4main class.
 * @author Aras Gungore
 *
 */
public class project4main {
	
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
		
		final int G_train_no = in.nextInt();
		int[] G_trains = new int[G_train_no];
		for(int i = 0; i < G_train_no; ++i)
			G_trains[i] = in.nextInt();
		
		final int R_train_no = in.nextInt();
		int[] R_trains = new int[R_train_no];
		for(int i = 0; i < R_train_no; ++i)
			R_trains[i] = in.nextInt();
		
		final int G_reindeer_no = in.nextInt();
		int[] G_reindeers = new int[G_reindeer_no];
		for(int i = 0; i < G_reindeer_no; ++i)
			G_reindeers[i] = in.nextInt();
		
		final int R_reindeer_no = in.nextInt();
		int[] R_reindeers = new int[R_reindeer_no];
		for(int i = 0; i < R_reindeer_no; ++i)
			R_reindeers[i] = in.nextInt();
		
		final int no_of_bags = in.nextInt();
		String[] types_of_bags = new String[no_of_bags];
		int[] nos_of_gifts_in_bags = new int[no_of_bags];
		int total_no_of_gifts = 0;
		for(int i = 0; i < no_of_bags; ++i) {
			if(in.hasNext("[a-e]+"))
				types_of_bags[i] = in.next();
			else
				types_of_bags[i] = "";
			nos_of_gifts_in_bags[i] = in.nextInt();
			total_no_of_gifts += nos_of_gifts_in_bags[i];
		}
		
		in.close();		// Closes the Scanner object.
		
		
		
		final int source = 0;
		
		final int G_train_start = 1 + no_of_bags;
		final int G_reindeer_start = G_train_start + G_train_no;
		
		final int R_train_start = G_reindeer_start + G_reindeer_no;
		final int R_reindeer_start = R_train_start + R_train_no;
		
		final int sink = R_reindeer_start + R_reindeer_no;
		
		
		MaxFlowDinic graph = new MaxFlowDinic(sink + 1);
		
		// Iterate through bags and construct the graph.
		for(int i = 0; i < no_of_bags; ++i) {
			final int curr_v = i + 1;
			
			// Add arc from source to bags.
			graph.addArc(source, curr_v, nos_of_gifts_in_bags[i]);
			final String types = types_of_bags[i];
			final int type_len = types.length();
			
			if(type_len > 0)
				switch(types.charAt(0)) {
					case 'a':
						if(type_len > 1)
							switch(types.charAt(1)) {
								case 'b':
									if(type_len > 2)
										switch(types.charAt(2)) {
											case 'd':	// 'abd'
												for(int j = 0; j < G_train_no; ++j)
													graph.addArc(curr_v, G_train_start + j, 1);
												break;
											case 'e':	// 'abe'
												for(int j = 0; j < G_reindeer_no; ++j)
													graph.addArc(curr_v, G_reindeer_start + j, 1);
										}
									else {				// 'ab'
										for(int j = 0; j < G_train_no; ++j)
											graph.addArc(curr_v, G_train_start + j, 1);
										for(int j = 0; j < G_reindeer_no; ++j)
											graph.addArc(curr_v, G_reindeer_start + j, 1);
									}
									break;
								case 'c':
									if(type_len > 2)
										switch(types.charAt(2)) {
											case 'd':	// 'acd'
												for(int j = 0; j < R_train_no; ++j)
													graph.addArc(curr_v, R_train_start + j, 1);
												break;
											case 'e':	// 'ace'
												for(int j = 0; j < R_reindeer_no; ++j)
													graph.addArc(curr_v, R_reindeer_start + j, 1);
										}
									else {				// 'ac'
										for(int j = 0; j < R_train_no; ++j)
											graph.addArc(curr_v, R_train_start + j, 1);
										for(int j = 0; j < R_reindeer_no; ++j)
											graph.addArc(curr_v, R_reindeer_start + j, 1);
									}
									break;
								case 'd':				// 'ad'
									for(int j = 0; j < G_train_no; ++j)
										graph.addArc(curr_v, G_train_start + j, 1);
									for(int j = 0; j < R_train_no; ++j)
										graph.addArc(curr_v, R_train_start + j, 1);
									break;
								case 'e':				// 'ae'
									for(int j = 0; j < G_reindeer_no; ++j)
										graph.addArc(curr_v, G_reindeer_start + j, 1);
									for(int j = 0; j < R_reindeer_no; ++j)
										graph.addArc(curr_v, R_reindeer_start + j, 1);
							}
						else {							// 'a'
							for(int j = 0; j < G_train_no; ++j)
								graph.addArc(curr_v, G_train_start + j, 1);
							for(int j = 0; j < R_train_no; ++j)
								graph.addArc(curr_v, R_train_start + j, 1);
							for(int j = 0; j < G_reindeer_no; ++j)
								graph.addArc(curr_v, G_reindeer_start + j, 1);
							for(int j = 0; j < R_reindeer_no; ++j)
								graph.addArc(curr_v, R_reindeer_start + j, 1);
						}
						break;
					case 'b':
						if(type_len > 1)
							switch(types.charAt(1)) {
								case 'd':				// 'bd'
									for(int j = 0; j < G_train_no; ++j)
										graph.addArc(curr_v, G_train_start + j, G_trains[j]);
									break;
								case 'e':				// 'be'
									for(int j = 0; j < G_reindeer_no; ++j)
										graph.addArc(curr_v, G_reindeer_start + j, G_reindeers[j]);
							}
						else {							// 'b'
							for(int j = 0; j < G_train_no; ++j)
								graph.addArc(curr_v, G_train_start + j, G_trains[j]);
							for(int j = 0; j < G_reindeer_no; ++j)
								graph.addArc(curr_v, G_reindeer_start + j, G_reindeers[j]);
						}
						break;
					case 'c':
						if(type_len > 1)
							switch(types.charAt(1)) {
								case 'd':				// 'cd'
									for(int j = 0; j < R_train_no; ++j)
										graph.addArc(curr_v, R_train_start + j, R_trains[j]);
									break;
								case 'e':				// 'ce'
									for(int j = 0; j < R_reindeer_no; ++j)
										graph.addArc(curr_v, R_reindeer_start + j, R_reindeers[j]);
							}
						else {							// 'c'
							for(int j = 0; j < R_train_no; ++j)
								graph.addArc(curr_v, R_train_start + j, R_trains[j]);
							for(int j = 0; j < R_reindeer_no; ++j)
								graph.addArc(curr_v, R_reindeer_start + j, R_reindeers[j]);
						}
						break;
					case 'd':							// 'd'
						for(int j = 0; j < G_train_no; ++j)
							graph.addArc(curr_v, G_train_start + j, G_trains[j]);
						for(int j = 0; j < R_train_no; ++j)
							graph.addArc(curr_v, R_train_start + j, R_trains[j]);
						break;
					case 'e':							// 'e'
						for(int j = 0; j < G_reindeer_no; ++j)
							graph.addArc(curr_v, G_reindeer_start + j, G_reindeers[j]);
						for(int j = 0; j < R_reindeer_no; ++j)
							graph.addArc(curr_v, R_reindeer_start + j, R_reindeers[j]);
				}
			else {										// '' (No restrictions)
				for(int j = 0; j < G_train_no; ++j)
					graph.addArc(curr_v, G_train_start + j, Math.min(nos_of_gifts_in_bags[i], G_trains[j]));
				for(int j = 0; j < R_train_no; ++j)
					graph.addArc(curr_v, R_train_start + j, Math.min(nos_of_gifts_in_bags[i], R_trains[j]));
				for(int j = 0; j < G_reindeer_no; ++j)
					graph.addArc(curr_v, G_reindeer_start + j, Math.min(nos_of_gifts_in_bags[i], G_reindeers[j]));
				for(int j = 0; j < R_reindeer_no; ++j)
					graph.addArc(curr_v, R_reindeer_start + j, Math.min(nos_of_gifts_in_bags[i], R_reindeers[j]));
			}
			
		}
		
		// Add arcs from vehicles to the sink.
		for(int i = 0; i < G_train_no; ++i)
			graph.addArc(G_train_start + i, sink, G_trains[i]);
		for(int i = 0; i < R_train_no; ++i)
			graph.addArc(R_train_start + i, sink, R_trains[i]);
		for(int i = 0; i < G_reindeer_no; ++i)
			graph.addArc(G_reindeer_start + i, sink, G_reindeers[i]);
		for(int i = 0; i < R_reindeer_no; ++i)
			graph.addArc(R_reindeer_start + i, sink, R_reindeers[i]);
		
		
		
		// Write output.
		
		// Create a new PrintStream object to write data to the output file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		out.print(total_no_of_gifts - graph.getMaximumFlow(source, sink));
		
		out.close();	// Closes the PrintStream object.
		
	}
}