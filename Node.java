

/**
 * A node in a linked list data 
 * structure implementation.
 * Contains link to another node
 * and the data itself.
 * @author dare
 *
 * @param <Item> type of data to store on the node
 */
public class Node<Item> {
	Item data;
	Node<Item> next;
	/**
	 * 
	 * @param value    data to store
	 * @param nextLink reference to next node
	 */
	Node(Item value, Node<Item> nextLink){
		data=value;
		next=nextLink;
	}
	public Node() {
		// TODO Auto-generated constructor stub
	}
}


