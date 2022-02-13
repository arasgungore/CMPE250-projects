
/**
 * Offer class.
 * @author Aras Gungore
 *
 */
public class Offer implements Comparable<Offer> {
	
	/**
	 * Start time of the offer.
	 */
	private int startTime;
	/**
	 * End time of the offer.
	 */
	private int endTime;
	/**
	 * Profit of the offer.
	 */
	private int profit;
	
	
	
	/**
	 * Offer constructor with 3 parameters; namely startTime, endTime, and profit.
	 * @param startTime Start time of the offer.
	 * @param endTime End time of the offer.
	 * @param profit Profit of the offer.
	 */
	public Offer(final int startTime, final int endTime, final int profit) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}
	
	/**
	 * Overrides the compareTo method to sort Offers according to ascending endTime order.
	 * @param o Other Offer.
	 * @return 1 if this Offer is of top priority, -1 if the other Offer is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(final Offer o) {
		if(endTime < o.endTime)
			return -1;
		if(endTime > o.endTime)
			return 1;
		return 0;
	}
	
	
	
	/**
	 * Getter method for the field "startTime".
	 * @return Start time of the offer.
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * Getter method for the field "endTime".
	 * @return End time of the offer.
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Getter method for the field "profit".
	 * @return Profit of the offer.
	 */
	public int getProfit() {
		return profit;
	}
	
}
