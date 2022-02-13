
import java.io.File;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;


/**
 * project1main class.
 * @author Aras Gungore
 *
 */
public class project1main {
	
	/**
	 * Allocates students to empty houses.
	 * @param students ArrayList which holds all currently homeless students that haven't graduated yet.
	 * @param occupied_houses ArrayList which holds all occupied houses.
	 * @param vacant_houses ArrayList which holds all vacant houses.
	 */
	public static void allocate_students(ArrayList<Student> students, ArrayList<House> occupied_houses, ArrayList<House> vacant_houses) {
		for(Iterator<Student> s_it = students.iterator(); s_it.hasNext(); ) {
			Student s = s_it.next();
			for(Iterator<House> h_it = vacant_houses.iterator(); h_it.hasNext(); ) {
				House h = h_it.next();
				if(h.getRating() >= s.getRating()) {
					h.occupiedByStudent(s);
					occupied_houses.add(h);
					s_it.remove();
					h_it.remove();
					break;
				}
			}
		}
	}
	
	/**
	 * Transfers perpetually homeless graduated students to the homeless ArrayList.
	 * @param students ArrayList which holds all currently homeless students that haven't graduated yet.
	 * @param homeless ArrayList which holds all perpetually homeless graduated students.
	 */
	public static void add_to_homeless(ArrayList<Student> students, ArrayList<Student> homeless) {
		for(Iterator<Student> s_it = students.iterator(); s_it.hasNext(); ) {
			Student s = s_it.next();
			s.semesterPasses();
			if(s.isGraduating()) {
				homeless.add(s);
				s_it.remove();
			}
		}
		// Collections.sort(students);
	}
	
	/**
	 * Transfers the houses which are vacated at the end of the semester to the empty_houses ArrayList.
	 * @param occupied_houses ArrayList which holds all occupied houses.
	 * @param vacant_houses ArrayList which holds all vacant houses.
	 */
	public static void add_to_vacant_houses(ArrayList<House> occupied_houses, ArrayList<House> vacant_houses) {
		for(Iterator<House> h_it = occupied_houses.iterator(); h_it.hasNext(); ) {
			House h = h_it.next();
			h.semesterPasses();
			if(h.isVacant()) {
				vacant_houses.add(h);
				h_it.remove();
			}
		}
		Collections.sort(vacant_houses);
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
		in.useLocale(Locale.ENGLISH);
		
		ArrayList<House> occupied_houses = new ArrayList<House>();
		ArrayList<House> vacant_houses = new ArrayList<House>();
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Student> homeless = new ArrayList<Student>();
		int max_duration = 0;
				
		while(in.hasNext()) {
			final char indicator = in.next().charAt(0);
			if(indicator == 'h') {
				final int h_ID = in.nextInt();
				final int h_duration = in.nextInt();
				final double h_rating = in.nextDouble();
				House h = new House(h_ID, h_duration, h_rating);
				if(h.isVacant())							// Add the vacant houses to the vacant_houses ArrayList.
					vacant_houses.add(h);
				else										// Add the occupied houses to the occupied_houses ArrayList.
					occupied_houses.add(h);
			}
			else if(indicator == 's') {
				final int s_ID = in.nextInt();
				final String s_name = in.next();
				final int s_duration = in.nextInt();
				final double s_rating = in.nextDouble();
				Student s = new Student(s_ID, s_name, s_duration, s_rating);
				if(s.isGraduating())						// Add the graduating students to the homeless ArrayList.
					homeless.add(s);
				else										// Add the other students to the students ArrayList.
					students.add(s);
				max_duration = Math.max(max_duration, s_duration);
			}
		}
		
		in.close();		// Closes the Scanner object.
		
		
		
		Collections.sort(students);			// Sort by ID.
		Collections.sort(vacant_houses);
		
		for(int semester = 1; semester < max_duration; ++semester) {
			allocate_students(students, occupied_houses, vacant_houses);
			add_to_homeless(students, homeless);
			add_to_vacant_houses(occupied_houses, vacant_houses);
		}
		
		// Last semester.
		allocate_students(students, occupied_houses, vacant_houses);
		// There are no more semesters left, so might as well add the rest of the students to the homeless.
		homeless.addAll(students);
		
		Collections.sort(homeless);		// Sort by ID.
		
		
		
		// Write output.
		
		// Create a new PrintStream object to write data to the output file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		for(Student s : homeless)
			out.println(s.getName());
		
		out.close();	// Closes the PrintStream object.
	}
}