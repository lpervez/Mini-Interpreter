// TO DO: JavaDocs

/**
 * A generic class representing a node in the linked list implementation
 * LList.
 * @author W. Masri
 * @param <T> is my type parameter.
 */
public class Node<T> {
	/**
	 * The node's value.
	 */
	private T value;

	/**
	 * The node's link to the next element in the linked list.
	 */
	private Node<T> next;

	/**
	 * constructors.
	 * @param value value
	 * @param next next
	 */
	public Node(T value, Node<T> next) {
		this.value = value;
		this.next = next;
	}

	/**
	 * Node of type T.
	 * 
	 * @param value value
	 */
	public Node(T value) {
		this.value = value;
		this.next = null;
	}

	/**
	 * getters and setters for the value and next attributes.
	 * @return value.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Set.
	 * 
	 * @param value value
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Get.
	 * 
	 * @return this.next
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * setNext.
	 * 
	 * @param next next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * returns a string description of the node's value attribute.
	 */
	@Override
	public String toString() {
		return value.toString();
	}
}