/**
 * This is LList Class.
 * 
 * @param <T> of type Parameter.
 */
// TO DO: add your implementation and JavaDocs.
public class LList<T> {
	// singly linked list with both head and tail
	/**
	 * Head.
	 */
	private Node<T> head; // first
	/**
	 * Tail.
	 */
	private Node<T> tail;// second
	/**
	 * Size.
	 */
	private int size;// how many

	// initialize the list to being an empty list
	/**
	 * Constructor.
	 */
	public LList() {
		size = 0;
		head = null;
		tail = null;
	}

	/**
	 * returns the first node in the list.
	 * 
	 * @return data
	 */
	// returns the first node in the list
	// O(1)
	public Node<T> getFirst() {
		if (head == null) {
			return null;
		}
		return head;
	}

	/**
	 * Inserts a new node with value T at the beginning of the list.
	 * 
	 * @param value value.
	 */
	// inserts a new node with value T at the beginning of the list
	// O(1)
	public void insertFirst(T value) {
		Node<T> newNode = new Node<T>(value);
		// if the list is empty
		if (head == null) {
			tail = newNode;
		} else {
			Node<T> temp = head;
			newNode.setNext(temp);
		}
		head = newNode;
		size++;
	}

	/**
	 * inserts a new node with value T at the beginning of the list.
	 * 
	 * @param newNode newNode.
	 */
	public void insertFirst(Node<T> newNode) {

		// if the list is empty
		if (head == null) {
			tail = newNode;
		} else {
			Node<T> temp = head;
			newNode.setNext(temp);
		}
		head = newNode;
		size++;
	}

	/**
	 * inserts a new node with value T at the end of the list.
	 * 
	 * @param newNode newNode
	 */
	// inserts a new node with value T at the end of the list
	// O(1)
	public void insertLast(Node<T> newNode) {
		// if the list is empty
		if (size == 0) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
	}

	/**
	 * remove and return the first node in the list.
	 * 
	 * @return node
	 */
	public Node<T> removeFirst() {
		// if list is empty return null
		if (head == null) {
			return null;
		}

		Node<T> temp = head;
		head = head.getNext();
		temp.setNext(null);
		size--;
		return temp;
	}

	/**
	 * return a string representing the values in the list separated by a single.
	 * 
	 * @return string
	 */
	public String listToString() {

		String retStr = "";
		Node<T> current = head;

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
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		/**
		 * Some type.
		 * @author laraibpervez
		 *
		 */
		class SomeType {
			/**
			 * value.
			 */
			private int value;
			/**
			 * constructor.
			 * @param value value
			 */
			public SomeType(int value) {
				this.value = value;
			}
			/**
			 * To string.
			 * @return string
			 */
			public String toString() {
				return "" + value;
			}
			/**
			 * Equals.
			 * @param o o
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
		Node<SomeType> n5 = new Node<>(new SomeType(500));

		LList<SomeType> list = new LList<>();
		list.insertFirst(item1);
		list.insertFirst(item2);
		list.insertFirst(item3);
		list.insertFirst(item4);

		if (list.listToString().equals("400 300 200 100")) {
			System.out.println("Yay1");
		}

		list.insertLast(n5);
		if (list.listToString().equals("400 300 200 100 500")) {
			System.out.println("Yay2");
		}

		list.removeFirst();
		if (list.listToString().equals("300 200 100 500")) {
			System.out.println("Yay3");
		}

		if (list.getFirst().getValue().toString().equals("300")) {
			System.out.println("Yay4");
		}

		list.insertFirst(new SomeType(600));
		if (list.listToString().equals("600 300 200 100 500")) {
			System.out.println("Yay5");
		}
	}
}
