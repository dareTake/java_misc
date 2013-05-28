
/**
 * A node in a linked list data 
 * structure implementation.
 * Contains link to the next & previous node
 * and the data itself.
 * @author Prasanth Ravi
 *
 * @param <Item> type of data to store on the node
 */
public class DblNode<Item> {
	Item data;
	DblNode<Item> next;
	DblNode<Item> prev;
	/**
	 * 
	 * @param value    data to be stored
	 * @param nextLink reference to next node
	 * @param prevLink reference to previous node
	 */
	DblNode(Item value, DblNode<Item> nextLink, DblNode<Item> prevLink){
		data=value;
		next=nextLink;
		prev=prevLink;
	}
	public DblNode() {
		
		// TODO Auto-generated constructor stub
	}
}
