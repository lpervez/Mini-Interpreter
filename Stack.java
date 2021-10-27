
// TO DO: add your implementation and JavaDocs.
/**
 * This is Stack Class.
 * 
 * @author laraibpervez
 *
 * @param <T> type parameter
 */
public class Stack<T> {
	// use a linked list (not an array)
	/**
	 * Elements.
	 */
	private LList<T> elements;
	/**
	 * Count.
	 */
	private int count;
	/**
	 * Top.
	 */
	private Node<T> top;

	// keeps track of the number of elements on the stack

	// initialize the stack to being an empty stack (or list)
	/**
	 * Constructor.
	 */
	public Stack() {
		elements = new LList<T>();
		count = 0;
		top = null;
	}

	/**
	 * Insert element at the beginning of the list.
	 * 
	 * @param e e
	 */
	public void push(T e) {
		elements.insertFirst(e);
		top = elements.getFirst();
		count++;
	}

	/**
	 * Remove element at the beginning of the list and return it.
	 * 
	 * @return value
	 */
	// O(1)
	public T pop() {
		T e = top.getValue();
		top = elements.getFirst().getNext();
		elements.removeFirst();
		count--;

		return e;
	}

	/**
	 * return element at beginning of list.
	 * 
	 * @return value
	 */
	// O(1)
	public T peek() {
		return top.getValue();
	}

	/**
	 * O(1).
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (elements == null);
	}

	/**
	 * O(1).
	 * 
	 * @return Integer
	 */
	public int getSize() {
		return count;
	}

	/**
	 * return string representing the values in the stack from top to bottom.
	 * @return String
	 */
	public String toString() {

		String retStr = "";
		Node<T> current = top;

		while (current != null) {
			retStr += current.getValue();
			current = current.getNext();

			if (current != null) {
				retStr += " ";
			}

		}

		return retStr;
	}

	/**
	 * Main Method.
	 * @param args args
	 */
	public static void main(String[] args) {
		/**
		 * Sometype.
		 * @author laraibpervez
		 *
		 */
		class SomeType {
			/**
			 * Value.
			 */
			private int value;
			/**
			 * Sometype.
			 * @param value value
			 */
			public SomeType(int value) {
				this.value = value;
			}
			/**
			 * To String.
			 * @return String
			 */
			public String toString() {
				return "" + value;
			}
			/**
			 * Equals.
			 * @param o o.
			 * @return boolean
			 */
			public boolean equals(Object o) {
				if (!(o instanceof SomeType))
					return false;
				return ((SomeType) o).value == value;
			}
		}

		SomeType item1 = new SomeType(100);
		SomeType item2 = new SomeType(200);
		SomeType item3 = new SomeType(300);
		SomeType item4 = new SomeType(400);

		Stack<SomeType> s = new Stack<>();
		s.push(item1);
		s.push(item2);

		if (s.getSize() == 2) {
			System.out.println("Yay1");
		}

		if (s.peek().toString().equals("200")) {
			System.out.println("Yay2");
		}
		if (s.pop().toString().equals("200")) {
			System.out.println("Yay3");
		}

		s.push(item3);
		s.push(item4);
		if (s.toString().equals("400 300 100")) {
			System.out.println("Yay4");
		}

		s.pop();
		s.pop();
		if (s.toString().equals("100")) {
			System.out.println("Yay5");
		}

		s.pop();
		if (s.isEmpty()) {
			System.out.println("Yay6");
		}

	}
}
