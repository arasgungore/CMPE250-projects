
/**
 * Event class.
 * @author Aras Gungore
 *
 */
public class Event implements Comparable<Event> {
	
	/**
	 * EventType enumeration.
	 * @author Aras Gungore
	 *
	 */
	public enum EventType {
		/**
		 * The event of a player entering the training queue.
		 */
		TrainingQueueEntry, 
		/**
		 * The event of a player entering the massage queue.
		 */
		MassageQueueEntry,
		/**
		 * The event of a player leaving the training queue and then immediately entering the physiotherapy queue.
		 */
		TrainingLeave,
		/**
		 * The event of a player leaving the massage queue.
		 */
		MassageLeave,
		/**
		 * The event of a player leaving the physiotherapy queue.
		 */
		PhysioLeave
	}
	
	
	
	/**
	 * Type of the event.
	 */
	private final EventType type;
	/**
	 * ID of the player associated with the event.
	 */
	private final int ID;
	/**
	 * Start time of the event.
	 */
	private final double time;
	/**
	 * Duration of the event.
	 */
	private final double duration;
	
	
	
	/**
	 * Event constructor with four parameters; namely type, ID, time, duration.
	 * @param type Type of the event.
	 * @param ID ID of the player associated with the event.
	 * @param time Start time of the event.
	 * @param duration Duration of the event.
	 */
	Event(final EventType type, final int ID, final double time, final double duration) {
		this.type = type;
		this.ID = ID;
		this.time = time;
		this.duration = duration;
	}
	
	/**
	 * Overrides to compareTo method to sort Events according to ascending time order.
	 * @param e Other Event.
	 * @return 1 if this Event is of top priority, -1 if the other Event is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(final Event e) {
		if(project2main.definitelyLessThan(time, e.time, project2main.getTol()))
			return -1;
		if(project2main.definitelyGreaterThan(time, e.time, project2main.getTol()))
			return 1;
		return 0;
	}
	
	
	
	/**
	 * Getter method for the field "type".
	 * @return Type of the event.
	 */
	public EventType getType() {
		return type;
	}
	
	/**
	 * Getter method for the field "ID".
	 * @return ID of the player associated with the event.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Getter method for the field "time".
	 * @return Start time of the event.
	 */
	public double getTime() {
		return time;
	}
	
	/**
	 * Getter method for the field "duration".
	 * @return Duration of the event.
	 */
	public double getDuration() {
		return duration;
	}
}
