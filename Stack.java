import java.util.Iterator;



/**
 *
 * An implementation of the stack datatype 
 * with Java Generics support
 * @author Prasanth Ravi
 * @param <Item> type of data to
 * 				 be stored in the stack
 */
public class Stack<Item>  implements Iterable<Item>{

	private Node<Item> top;
	private int size;
	//Node head;
	
	/**
	 * @return size of the stack
	*/
	public int size() {
		return size;
	}
	/**
	 * API test code
	 */
	public static void main(String[] args) {
		Stack<String> ss=new Stack<String>();
		for(int i=0;i<50;i++)
			ss.push("Item " + i);
		for( String it : ss) {
			try {
				System.out.println(it);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		for(int i=100;i<150;i++)
			ss.push("Item " + i);
		for(int i=0;i<100;i++) {
			try {
				System.out.println(ss.pop());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
		
	}
	public Stack() {
		top=null;
		size=0;
	}
	/**
	 * Pushes node onto top of stack
	 * @param value data to push onto the stack 
	 */
	public void push(Item value) {
		Node<Item> tmp=new Node<Item>(value,top);
		top=tmp;
		size++;
	}
	/**
	 * Removes node from top of stack and returns data stored in it
	 * @return the item value that 
	 * 		   was popped out of the stack
	 * @throws UnderFlowException when the user tries to pop
	 * 							 while the stack is empty
	 */
	public Item pop() throws Exception{
		Item it;
		if(top==null ) { throw new UnderFlowException(); }
		else if (top.next==null) { 
			it=top.data; 
			top=null;
			size--;
			return it; 
		} 
		else { 
			it= top.data; 
			top=top.next;
			size--;
			return it; 
		}
	}
	/**
	 * provides Iterator functionality
	 */
	@Override
	public Iterator<Item> iterator() {
		
		return new StackIter();
	}
	
	private class StackIter implements Iterator<Item> {
		Node<Item> current=top;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current!=null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item it=current.data;
			current=current.next;
			return it;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		
	}

}
