
import java.io.File;
import java.io.PrintStream;

import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * project2main class.
 * @author Aras Gungore
 *
 */
public class project2main {
	
	/**
	 * Common tolerance value for all type double comparisons.
	 */
	private static double tol = 1e-10;
	
	/**
	 * Static getter method for the static field "tol".
	 * @return Common tolerance value for all type double comparisons.
	 */
	public static double getTol() {
		return tol;
	}
	
	
	
	/**
	 * Static IO_Pair class.
	 * @author Aras Gungore
	 *
	 */
	public static class Pair<K, V> {
		
		/**
		 * Key of the pair.
		 */
		public K key;
		/**
		 * Value of the pair.
		 */
		public V value;
		
		/**
		 * Pair constructor with two parameters; namely key and value.
		 * @param key Key of the pair.
		 * @param value Value of the pair.
		 */
		public Pair(final K key, final V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
	
	/**
	 * Returns whether the two double numbers are considered equal or not.
	 * @param a First number of type double.
	 * @param b Second number of type double.
	 * @param tol Tolerance value for type double comparisons.
	 * @return TRUE if the numbers are equal, FALSE otherwise.
	 */
	public static boolean essentiallyEqual(final double a, final double b, final double tol) {
		return Math.abs(a - b) < tol;
	}

	/**
	 * Returns whether the first double number is considered greater than the second number or not.
	 * @param a First number of type double.
	 * @param b Second number of type double.
	 * @param tol Tolerance value for type double comparisons.
	 * @return TRUE if the first number is greater than the second, FALSE otherwise.
	 */
	public static boolean definitelyGreaterThan(final double a, final double b, final double tol) {
		return a - b > tol;
	}
	
	/**
	 * Returns whether the first double number is considered less than the second number or not.
	 * @param a First number of type double.
	 * @param b Second number of type double.
	 * @param tol Tolerance value for type double comparisons.
	 * @return TRUE if the first number is less than the second, FALSE otherwise.
	 */
	public static boolean definitelyLessThan(final double a, final double b, final double tol) {
		return definitelyGreaterThan(b, a, tol);
	}
	
	
	
	/**
	 * TrainingComparator class.
	 * @author Aras Gungore
	 *
	 */
	public static class TrainingComparator implements Comparator<Player> {
		
		/**
		 * Overrides to compare method to sort Players according to first-come-first-served fashion, then lowest ID.
		 * @param p1 First Player.
		 * @param p2 Second Player.
		 * @return 1 if p2 Player is of top priority, -1 if p1 Player is of top priority, 0 if they have the same priority.
		 */
		@Override
		public int compare(final Player p1, final Player p2) {
			if(definitelyLessThan(p1.training_queue_entry_time, p2.training_queue_entry_time, tol))
				return -1;
			if(definitelyGreaterThan(p1.training_queue_entry_time, p2.training_queue_entry_time, tol))
				return 1;
			if(p1.getID() < p2.getID())
				return -1;
			if(p1.getID() > p2.getID())
				return 1;
			return 0;
		}
	}
	
	/**
	 * PhysioComparator class.
	 * @author Aras Gungore
	 *
	 */
	public static class PhysioComparator implements Comparator<Player> {
		
		/**
		 * Overrides to compare method to sort Players according to highest training time, then first-come-first-served, then lowest ID.
		 * @param p1 First Player.
		 * @param p2 Second Player.
		 * @return 1 if p2 Player is of top priority, -1 if p1 Player is of top priority, 0 if they have the same priority.
		 */
		@Override
		public int compare(final Player p1, final Player p2) {
			if(definitelyGreaterThan(p1.training_duration, p2.training_duration, tol))
				return -1;
			if(definitelyLessThan(p1.training_duration, p2.training_duration, tol))
				return 1;
			if(definitelyLessThan(p1.physio_queue_entry_time, p2.physio_queue_entry_time, tol))
				return -1;
			if(definitelyGreaterThan(p1.physio_queue_entry_time, p2.physio_queue_entry_time, tol))
				return 1;
			if(p1.getID() < p2.getID())
				return -1;
			if(p1.getID() > p2.getID())
				return 1;
			return 0;
		}
	}
	
	/**
	 * MassageComparator class.
	 * @author Aras Gungore
	 *
	 */
	public static class MassageComparator implements Comparator<Player> {
		
		/**
		 * Overrides to compare method to sort Players according to highest skill level, then first-come-first-served, then lowest ID.
		 * @param p1 First Player.
		 * @param p2 Second Player.
		 * @return 1 if p2 Player is of top priority, -1 if p1 Player is of top priority, 0 if they have the same priority.
		 */
		@Override
		public int compare(final Player p1, final Player p2) {
			if(p1.getLevel() > p2.getLevel())
				return -1;
			if(p1.getLevel() < p2.getLevel())
				return 1;
			if(definitelyLessThan(p1.massage_queue_entry_time, p2.massage_queue_entry_time, tol))
				return -1;
			if(definitelyGreaterThan(p1.massage_queue_entry_time, p2.massage_queue_entry_time, tol))
				return 1;
			if(p1.getID() < p2.getID())
				return -1;
			if(p1.getID() > p2.getID())
				return 1;
			return 0;
		}
	}
	
	
	
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
		
		// Get players.
		final int N = in.nextInt();
		Player[] players = new Player[N];
		for(int i = 0; i < N; ++i) {
			final int ID = in.nextInt();
			final int level = in.nextInt();
			players[i] = new Player(ID, level);
		}
		
		// Get training and massage queue entries and put them to the timeline.
		final int A = in.nextInt();
		PriorityQueue<Event> timeline = new PriorityQueue<Event>(A);
		for(int i = 0; i < A; ++i) {
			final Event.EventType type = in.next().charAt(0) == 't' ? Event.EventType.TrainingQueueEntry : Event.EventType.MassageQueueEntry;
			final int ID = in.nextInt();
			final double time = in.nextDouble();
			final double duration = in.nextDouble();
			timeline.add(new Event(type, ID, time, duration));
		}
		
		// Get physiotherapists.
		final int S_p = in.nextInt();
		Physiotherapist[] physiotherapists = new Physiotherapist[S_p];
		for(int i = 0; i < S_p; ++i) {
			final double time = in.nextDouble();
			physiotherapists[i] = new Physiotherapist(time);
		}
		
		int S_c = in.nextInt();
		int S_m = in.nextInt();
		
		
		in.close();		// Closes the Scanner object.
		
		
		
		PriorityQueue<Player> training_queue = new PriorityQueue<Player>(new TrainingComparator());
		PriorityQueue<Player> physio_queue = new PriorityQueue<Player>(new PhysioComparator());
		PriorityQueue<Player> massage_queue = new PriorityQueue<Player>(new MassageComparator());
		
		int total_canceled_trainings_and_massages = 0, total_invalid_massages = 0;
		int max_training_queue_len = 0, max_physio_queue_len = 0, max_massage_queue_len = 0;
		int training_queue_entry_count = 0, massage_queue_entry_count = 0;
		double training_queue_waiting_duration = 0.0d, physio_queue_waiting_duration = 0.0d, massage_queue_waiting_duration = 0.0d;
		double total_training_duration = 0.0d, total_physio_duration = 0.0d, total_massage_duration = 0.0d;
		double total_turnaround_time = 0.0d, last_event_time = 0.0d;
		Pair<Integer, Double> max_physio_queue_waiting_player = new Pair<Integer, Double>(0, 0.0d);
		Pair<Integer, Double> min_massage_queue_waiting_player = new Pair<Integer, Double>(-1, -1.0d);
		
		while(!timeline.isEmpty()) {
			final Event event = timeline.poll();
			final double event_time = event.getTime();
			Player player = players[event.getID()];
			
			// Handles the event.
			switch(event.getType()) {
				case TrainingQueueEntry:		// Player enters the training queue.
					if(player.is_available) {
						player.training_queue_entry_time = event_time;
						player.training_duration = event.getDuration();
						player.is_available = false;
						training_queue.add(player);
						++training_queue_entry_count;
					}
					else
						++total_canceled_trainings_and_massages;
					break;
				case MassageQueueEntry:			// Player enters the massage queue.
					if(player.getMassageCount() < 3) {
						if(player.is_available) {
							player.massage_queue_entry_time = event_time;
							player.massage_duration = event.getDuration();
							player.is_available = false;
							player.takeMassage();
							massage_queue.add(player);
							++massage_queue_entry_count;
						}
						else
							++total_canceled_trainings_and_massages;
					}
					else
						++total_invalid_massages;
					break;
				case TrainingLeave:				// Player leaves the training session.
					++S_c;
					player.physio_queue_entry_time = event_time;
					physio_queue.add(player);
					break;
				case MassageLeave:				// Player leaves the massage session.
					++S_m;
					player.is_available = true;
					break;
				case PhysioLeave:				// Player leaves the physiotherapy session.
					// if(definitelyGreaterThan(event_time, player.training_queue_entry_time, tol))
						total_turnaround_time += event_time - player.training_queue_entry_time;
					player.is_available = true;
					physiotherapists[player.physiotherapist_ID].is_available = true;
			}
			
			
			// Players leave the training queue and visit the coaches.
			while(!training_queue.isEmpty() && S_c > 0) {
				--S_c;
				Player p = training_queue.poll();
				total_training_duration += p.training_duration;
				// if(definitelyGreaterThan(event_time, p.training_queue_entry_time, tol))
					training_queue_waiting_duration += event_time - p.training_queue_entry_time;
				timeline.add(new Event(Event.EventType.TrainingLeave, p.getID(), event_time + p.training_duration, 0.0d));
			}
			
			// Players leave the physiotherapy queue and visit the physiotherapists.
			for(int i = 0; !physio_queue.isEmpty() && i < S_p; ++i) {
				Physiotherapist physio = physiotherapists[i];
				if(physio.is_available) {
					Player p = physio_queue.poll();
					total_physio_duration += physio.getServiceTime();
					// if(definitelyGreaterThan(event_time, p.physio_queue_entry_time, tol)) {
						final double waiting_duration = event_time - p.physio_queue_entry_time;
						physio_queue_waiting_duration += waiting_duration;
						p.increasePhysioWaitingDuration(waiting_duration);
					// }
					if(definitelyGreaterThan(p.getTotalPhysioWaitingDuration(), max_physio_queue_waiting_player.value, tol)) {
						max_physio_queue_waiting_player.key = p.getID();
						max_physio_queue_waiting_player.value = p.getTotalPhysioWaitingDuration();
					}
					else if(essentiallyEqual(p.getTotalPhysioWaitingDuration(), max_physio_queue_waiting_player.value, tol)
							&& p.getID() < max_physio_queue_waiting_player.key)
						max_physio_queue_waiting_player.key = p.getID();
					physio.is_available = false;
					p.physiotherapist_ID = i;
					timeline.add(new Event(Event.EventType.PhysioLeave, p.getID(), event_time + physio.getServiceTime(), 0.0d));
				}
			}
			
			// Players leave the massage queue and visit the masseurs.
			while(!massage_queue.isEmpty() && S_m > 0) {
				--S_m;
				Player p = massage_queue.poll();
				total_massage_duration += p.massage_duration;
				// if(definitelyGreaterThan(event_time, p.massage_queue_entry_time, tol)) {
					final double waiting_duration = event_time - p.massage_queue_entry_time;
					massage_queue_waiting_duration += waiting_duration;
					p.increaseMassageWaitingDuration(waiting_duration);
				// }
				if(p.getMassageCount() == 3) {
					if(min_massage_queue_waiting_player.key == -1) {
						min_massage_queue_waiting_player.key = p.getID();
						min_massage_queue_waiting_player.value = p.getTotalMassageWaitingDuration();
					}
					else if(definitelyLessThan(p.getTotalMassageWaitingDuration(), min_massage_queue_waiting_player.value, tol)) {
						min_massage_queue_waiting_player.key = p.getID();
						min_massage_queue_waiting_player.value = p.getTotalMassageWaitingDuration();
					}
					else if(essentiallyEqual(p.getTotalMassageWaitingDuration(), min_massage_queue_waiting_player.value, tol)
							&& p.getID() < min_massage_queue_waiting_player.key)
						min_massage_queue_waiting_player.key = p.getID();
				}
				timeline.add(new Event(Event.EventType.MassageLeave, p.getID(), event_time + p.massage_duration, 0.0d));
			}
			
			// Check for last event time and maximum queue lengths.
			final Event next_event = timeline.peek();
			final boolean is_last_event = next_event == null;
			if(is_last_event || definitelyLessThan(event_time, next_event.getTime(), tol)) {
				if(is_last_event)
					last_event_time = event_time;
				max_training_queue_len = Math.max(training_queue.size(), max_training_queue_len);
				max_physio_queue_len = Math.max(physio_queue.size(), max_physio_queue_len);
				max_massage_queue_len = Math.max(massage_queue.size(), max_massage_queue_len);
			}
		}
		
		
		
		// Write output.
		
		// Create a new PrintStream object to write data to the output file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		// Double statistics are rounded to exactly 3 decimal points.
		
		// 1. Maximum length of the training queue.
		out.println(max_training_queue_len);
		// 2. Maximum length of the physiotherapy queue.
		out.println(max_physio_queue_len);
		// 3. Maximum length of the massage queue.
		out.println(max_massage_queue_len);
		// 4. Average waiting time in the training queue.
		out.println(String.format("%.3f", training_queue_entry_count == 0 ? 0.0d : training_queue_waiting_duration / (double)training_queue_entry_count));
		// 5. Average waiting time in the physiotherapy queue.
		out.println(String.format("%.3f", training_queue_entry_count == 0 ? 0.0d : physio_queue_waiting_duration / (double)training_queue_entry_count));
		// 6. Average waiting time in the massage queue.
		out.println(String.format("%.3f", massage_queue_entry_count == 0 ? 0.0d : massage_queue_waiting_duration / (double)massage_queue_entry_count));
		// 7. Average training time.
		out.println(String.format("%.3f", training_queue_entry_count == 0 ? 0.0d : total_training_duration / (double)training_queue_entry_count));
		// 8. Average physiotherapy time.
		out.println(String.format("%.3f", training_queue_entry_count == 0 ? 0.0d : total_physio_duration / (double)training_queue_entry_count));
		// 9. Average massage time.
		out.println(String.format("%.3f", massage_queue_entry_count == 0 ? 0.0d : total_massage_duration / (double)massage_queue_entry_count));
		// 10. Average turnaround time.
		out.println(String.format("%.3f", training_queue_entry_count == 0 ? 0.0d : total_turnaround_time / (double)training_queue_entry_count));
		// 11. ID of the player who spent the most time in the physiotherapy queue and the waiting time of that player in seconds.
		out.println(max_physio_queue_waiting_player.key + " " + String.format("%.3f", max_physio_queue_waiting_player.value));
		// 12. ID of the player who spent the least time in the massage queue and the waiting time of
		// that player in seconds, among the ones who took three massage services.
		out.println(min_massage_queue_waiting_player.key + " " + (essentiallyEqual(-1.0d, min_massage_queue_waiting_player.value, tol) ? "-1" : String.format("%.3f", min_massage_queue_waiting_player.value)));
		// 13. Total number of invalid attempts to get a message service.
		out.println(total_invalid_massages);
		// 14. Total number of canceled attempts including both training and massage attempts.
		out.println(total_canceled_trainings_and_massages);
		// 15. Total seconds passed during the whole simulation.
		out.println(String.format("%.3f", last_event_time));
		
		out.close();	// Closes the PrintStream object.
		
	}
}