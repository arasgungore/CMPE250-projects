
/**
 * Physiotherapist class.
 * @author Aras Gungore
 *
 */
public class Physiotherapist {
	
	/**
	 * Service time of the physiotherapist.
	 */
	private final double service_time;
	/**
	 * Whether the physiotherapist is available or not.
	 */
	public boolean is_available = true;
	
	
	
	/**
	 * Physiotherapist constructor with one parameter; namely time.
	 * @param service_time Service time of the physiotherapist.
	 */
	Physiotherapist(final double service_time) {
		this.service_time = service_time;
	}
	
	/**
	 * Getter method for the field "service_time".
	 * @return Service time of the physiotherapist.
	 */
	public double getServiceTime() {
		return service_time;
	}
}
