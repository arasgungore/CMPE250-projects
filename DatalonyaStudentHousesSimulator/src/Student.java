
/**
 * Student class.
 * @author Aras Gungore
 *
 */
public class Student implements Comparable<Student> {
	
	/**
	 * ID of the student.
	 */
	private int ID;
	/**
	 * Name of the student.
	 */
	private String name;
	/**
	 * Number of semesters that the student will study at the University of Datalonya.
	 */
	private int duration;
	/**
	 * Minimum rating criterion of the student to accept a house.
	 */
	private double rating;
	
	

	/**
	 * A constructor with three parameters; namely ID, name, duration, rating.
	 * @param ID ID of the student.
	 * @param name Name of the student.
	 * @param duration Number of semesters that the student will study.
	 * @param rating Minimum rating criterion of the student to accept a house.
	 */
	public Student(final int ID, final String name, final int duration, final double rating) {
		this.ID = ID;
		this.name = name;
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
	 * Returns whether the student is graduating this semester or not.
	 * @return TRUE if duration is 0, FALSE otherwise.
	 */
	public boolean isGraduating() {
		return duration == 0;
	}
	
	/**
	 * Overrides to compareTo method to sort Students according to ascending ID order.
	 * @param s Other Student.
	 * @return 1 if this Student is of top priority, -1 if the other Student is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(final Student s) {
		if(ID < s.ID)
			return -1;
		if(ID > s.ID)
			return 1;
		return 0;
	}
	
	
	
	/**
	 * Getter method for the field "name".
	 * @return Name of the student.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for the field "duration".
	 * @return Number of semesters that the student will study.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Getter method for the field "rating".
	 * @return Minimum rating criterion of the student to accept a house.
	 */
	public double getRating() {
		return rating;
	}
}
