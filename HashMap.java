// TO DO: add your implementation and JavaDocs.

// ONLY IMPLEMENT THE TWO METHODS: put() and get()
// ALL ELSE KEEP AS IS
/**
 * Hash Map.
 * 
 * @author laraib pervez.
 * @param <K> is type parameter.
 * @param <V> is type parameter.
 */
public class HashMap<K, V> {

	// This HashMap implementation uses a LList<T> composed of Node<T>.
	// Since two generic parameters <K, V> are needed instead of one,
	// the Pair class below is provided to be used as follows: Node<Pair> and
	// LList<Pair>
	/**
	 * This is Pair Class.
	 * 
	 * @author laraibpervez.
	 * @param <K> is type parameter.
	 * @param <V> is type parameter.
	 */
	class Pair<K, V> {
		/**
		 * key.
		 */
		private K key;
		/**
		 * value.
		 */
		private V value;

		/**
		 * Constructor.
		 * 
		 * @param key   key.
		 * @param value value.
		 */
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Get key.
		 * 
		 * @return K
		 */
		public K getKey() {
			return key;
		}

		/**
		 * Get Value.
		 * 
		 * @return V
		 */
		public V getValue() {
			return value;
		}

		/**
		 * set Key.
		 * 
		 * @param key key.
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * set Value.
		 * 
		 * @param value value.
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * HashCode.
		 */
		@Override
		public int hashCode() {
			return key.hashCode();
		}

		/**
		 * Equals.
		 * 
		 * @param obj obj.
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (!(obj instanceof Pair))
				return false;
			Pair pair = (Pair) obj;
			return pair.key.equals(key);
		}
	}

	/**
	 * array of LLists where each list will be composed of Node.
	 */
	private LList<Pair>[] buckets;

	/**
	 * will fix the capacity to 20.
	 */
	final static private int DEFAULT_CAPACITY = 20;
	/**
	 * Current capacity.
	 */
	private int currCap; // current capacity

	/**
	 * track how many elements in HashMap.
	 */
	private int size = 0;

	/**
	 * Constructor.
	 */
	public HashMap() {
		this(DEFAULT_CAPACITY);
		currCap = DEFAULT_CAPACITY;
	}

	/**
	 * Overloaded Constructor.
	 * 
	 * @param capacity capacity.
	 */
	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		buckets = (LList<Pair>[]) new LList[capacity];
		currCap = capacity;
	}

	/**
	 * size.
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Capacity.
	 * 
	 * @return capacity
	 */
	private int getCapacity() {
		return buckets.length;
	}

	/**
	 * HashCode.
	 * 
	 * @param key key.
	 * @return key
	 */
	private int getHash(K key) {
		return key == null ? 0 : Math.abs(key.hashCode());
	}

	/**
	 * Override ToString.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (LList<Pair> list : buckets) {
			sb.append("[");
			if (list != null) {
				sb.append(list.listToString());
			}
			sb.append(", ");
			sb.append("]");
		}
		return "{" + sb.toString() + "}";
	}

	/**
	 * Hint: This function involves LList and Node.
	 * 
	 * @param key   key.
	 * @param value value.
	 */
	// Cost: O(1) on average, and O(n) worst case
	//
	// if element already exists (the keys matched), override the old value with the
	// new value
	@SuppressWarnings("unchecked")
	public void put(K key, V value) {

		int hashCode = getHash(key);
		int location = hashCode % currCap;

		Pair val = new Pair<>(key, value);
		Node<Pair> node = new Node<>(val);

		if (buckets[location] == null) {
			buckets[location] = new LList<Pair>();
			buckets[location].insertFirst(node);
			size++;

		} else {
			Node<Pair> current = buckets[location].getFirst();
			while (current != null) {
				if (current.getValue().getKey().equals(val.getKey())) {
					current.getValue().setValue(val.getValue());
					return;
				}

				current = current.getNext();
			}
			buckets[location].insertLast(node);
			size++;
		}

	}

	/**
	 * Hint: This function involves LList and Node.
	 * 
	 * @param key key.
	 * @return V V.
	 */
	// Cost: O(1) on average, and O(n) worst case
	//
	// if element was not found return null
	@SuppressWarnings("unchecked")
	public V get(K key) {

		int hashCode = getHash(key);
		int location = hashCode % currCap;

		if (buckets[location] == null) {
			return null;
		}

		Node<Pair> current = buckets[location].getFirst();

		while (current != null) {
			if (current.getValue().getKey().equals(key)) {
				return (V) current.getValue().getValue();
			}
			current = current.getNext();
		}

		return null;

	}

	/**
	 * Main Method.
	 * 
	 * @param args args.
	 */
	public static void main(String args[]) {
		HashMap<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			map.put(i, "Val" + i);
		}

		if (map.size() == 10000) {
			System.out.println("Yay1");
		}

		if (map.get(5).equals("Val5") && map.get(500).equals("Val500") && map.get(5000).equals("Val5000")
				&& map.get(9999).equals("Val9999")) {
			System.out.println("Yay2");
		}

		map.put(0, "Val" + 0);
		if (map.size() == 10000) {
			System.out.println("Yay3");
		}

	}

}