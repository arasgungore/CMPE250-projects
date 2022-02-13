
/**
 * Player class.
 * @author Aras Gungore
 *
 */
public class Player {
	
	/**
	 * ID of the player.
	 */
	private final int ID;
	/**
	 * Skill level of the player.
	 */
	private final int level;
	
	/**
	 * The time which the player enters the training queue.
	 */
	public double training_queue_entry_time;
	/**
	 * The time which the player enters the physiotherapy queue.
	 */
	public double physio_queue_entry_time;
	/**
	 * The time which the player enters the massage queue.
	 */
	public double massage_queue_entry_time;
	
	/**
	 * The duration which the player does training.
	 */
	public double training_duration;
	/**
	 * The duration which the player gets massage.
	 */
	public double massage_duration;
	
	/**
	 * Whether the player is available or not, meaning not being in any of the training, massage, or physiotherapy queues.
	 */
	public boolean is_available = true;
	/**
	 * Number of times the player has taken the massage service.
	 */
	private int massage_count = 0;
	/**
	 * The ID (aka index) of the physiotherapist the player has last visited.
	 */
	public int physiotherapist_ID;
	/**
	 * The total duration which the player has waited in the massage queue.
	 */
	private double total_massage_waiting_duration = 0.0d;
	/**
	 * The total duration which the player has waited in the physiotherapy queue.
	 */
	private double total_physio_waiting_duration = 0.0d;
	
	
	
	/**
	 * Player constructor with two parameters; namely ID and level.
	 * @param ID ID of the player.
	 * @param level Skill level of the player.
	 */
	Player(final int ID, final int level) {
		this.ID = ID;
		this.level = level;
	}
	
	/**
	 * Player takes the massage service, so this method increases the massage_count by 1.
	 */
	public void takeMassage() {
		++massage_count;
	}
	
	/**
	 * Increases the total_massage_waiting_duration by given amount.
	 * @param waiting_duration The duration which the player has waited in the massage queue.
	 */
	public void increaseMassageWaitingDuration(final double waiting_duration) {
		total_massage_waiting_duration += waiting_duration;
	}
	
	/**
	 * Increases the total_physio_waiting_duration by given amount.
	 * @param waiting_duration The duration which the player has waited in the physiotherapy queue.
	 */
	public void increasePhysioWaitingDuration(final double waiting_duration) {
		total_physio_waiting_duration += waiting_duration;
	}
	
	
	
	/**
	 * Getter method for the field "ID".
	 * @return ID of the player.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Getter method for the field "level".
	 * @return Skill level of the player.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Getter method for the field "massage_count".
	 * @return Number of times the player has taken the massage service.
	 */
	public int getMassageCount() {
		return massage_count;
	}
	
	/**
	 * Getter method for the field "total_massage_waiting_duration".
	 * @return The total duration which the player has waited in the massage queue.
	 */
	public double getTotalMassageWaitingDuration() {
		return total_massage_waiting_duration;
	}
	
	/**
	 * Getter method for the field "total_physio_waiting_duration".
	 * @return The total duration which the player has waited in the physiotherapy queue.
	 */
	public double getTotalPhysioWaitingDuration() {
		return total_physio_waiting_duration;
	}
}
