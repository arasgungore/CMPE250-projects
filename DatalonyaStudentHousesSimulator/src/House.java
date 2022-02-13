
/**
 * House class.
 * @author Aras Gungore
 *
 */
public class House implements Comparable<House> {
	
	/**
	 * ID of the house.
	 */
	private int ID;
	/**
	 * Duration as the number of semesters that the house is full.
	 */
	private int duration;
	/**
	 * Rating of the house which shows the good or bad condition of the house.
	 */
	private double rating;
	
	
	
	/**
	 * A constructor with four parameters; namely ID, name, duration, rating.
	 * @param ID ID of the student.
	 * @param duration Duration as the number of semesters that the house is full.
	 * @param rating Rating of the house.
	 */
	public House(final int ID, final int duration, final double rating) {
		this.ID = ID;
		this.duration = duration;
		this.rating = rating;
	}

	/**
	 * Decreases the duration counter by 1.
	 */
	public void semesterPasses() {
		if(duration > 0)
			--duration;
	}
	
	/**
	 * Returns whether the house is vacant or not.
	 * @return TRUE if duration is 0, FALSE otherwise.
	 */
	public boolean isVacant() {
		return duration == 0;
	}
	
	/**
	 * Occupies the house by a student and sets the duration until the student graduates.
	 * @param s Student which will be staying in the house from now on.
	 */
	public void occupiedByStudent(final Student s) {
		duration = s.getDuration();
	}
	
	/**
	 * Overrides to compareTo method to sort Houses according to ascending ID order.
	 * @param h Other House.
	 * @return 1 if this House is of top priority, -1 if the other House is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(final House h) {
		if(ID < h.ID)
			return -1;
		if(ID > h.ID)
			return 1;
		return 0;
	}
	
	
	
	/**
	 * Getter method for the field "duration".
	 * @return Duration as the number of semesters that the house is full.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Getter method for the field "rating".
	 * @return Rating of the house.
	 */
	public double getRating() {
		return rating;
	}
}
