
/**
 * Implementation of the Dequeue data structure
 * using doubly linked list
 * @author Prasanth Ravi
 *
 */
public class DequeDbl<Item> {

	DblNode<Item> left;
	DblNode<Item> right;
	int size;
	/**
	 * @return size of queue
	 */
	public int Size() {
		return size;
	}

	/**
	 * @return <code>true</code> if queue is empty
	 */
	public boolean isEmpty() {
		return size==0;
	}
	public DequeDbl() {
		left=right=null;
		size=0;
	}
	
	/**
	 * API test code
	 */
	public static void main(String[] args) {
		int i;
		Deque<String> dq= new Deque<String>();
		try {
			for ( i = 0; i < 20; i++) {
				if (i%2==0) { dq.addLast("Item" + i); }
				else 		{ dq.addFirst("Item" + i); }
			}
			for ( i = 0; i < 20; i++) { 
				System.out.println(dq.removeLast()); 
			}
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}

	}
	/**
	 * inserts <code>value</code> onto the left of the queue
	 * @param value data to be stored in node
	 */
	public void pushLeft(Item value) {
		if(isEmpty()) { left=right= new DblNode<Item>(value,null,null); }
		else if(left==right) { left= new DblNode<Item>(value,right,null); }
		else { left= new DblNode<Item>(value,left,null) ; }
	}
	/**
	 * inserts <code>value</code> onto the right of the queue
	 * @param value data to be stored in node
	 */
	public void pushRight(Item value) {
		if(isEmpty()) { left=right= new DblNode<Item>(value,null,null); }
		else if(left==right) { right= new DblNode<Item>(value,null,left); }
		else { right= new DblNode<Item>(value,null,right) ; }
	}
	/**
	 * Remove node from left and return the data
	 * @return data of the removed node 
	 * @throws UnderlowException  when there is no item in the queue
	 */
	public Item popLeft() throws Exception{
		Item it;
		if(isEmpty()) { throw new UnderFlowException(); }
		else if(left==right) {it=left.data;left=right=null;}
		else {it=left.data; left=left.next;}
		size--;
		return it;
	}
	/**
	 * Remove node from right and return the data
	 * @return data of the removed node 
	 * @throws UnderlowException  when there is no item in the queue
	 */
	public Item popRight() throws Exception{
		Item it;
		if(isEmpty()) { throw new UnderFlowException(); }
		else if(left==right) {it=right.data; left=right=null;}
		else {it=right.data; right=right.prev;}
		size--;
		return it;
	}
	
}
	
	

