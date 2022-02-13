
/**
 * Pair class.
 * @author Aras Gungore
 *
 */
public class Pair<K, V> {
	
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
