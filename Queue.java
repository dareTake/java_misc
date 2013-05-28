import java.util.Iterator;

/**
 * An implementation of the Queue data structure
 * with Java Generics support
 * @author Prasanth Ravi
 * @param <Item> type of data to store
 */
public class Queue<Item> implements Iterable<Item>{


	Node<Item> first;
	Node<Item> last;
	
	/**
	 * API test code
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> Q=new Queue<Integer>();
		for(int i=0;i<20;i++)
			Q.enqueue(i);
		for(int i=0;i<20;i++)
			try {
				System.out.println(Q.dequeue());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		for(int i=0;i<20;i++)
			Q.enqueue(i*2);
		
		for(Integer i: Q)
			System.out.println(i);
	}
	public Queue() {
		first=last=null;
	}
	/**
	 * Inserts node to end of queue
	 * @param value value to insert at end of the queue
	 */
	public void enqueue(Item value) {
		if(first==null) { first=last=new Node<Item>(value,null);}
		else {
			Node<Item> tmp=new Node<Item>(value,null);
			last.next=tmp;
			last=tmp;
		}
	}
	/**
	 * Removes node from front of the queue
	 * @return value of the node removed
	 * @throws UnderFlowException when queue is empty
	 */
	public Item dequeue() throws Exception{
		if(first==null) throw new UnderFlowException();
		else {
			Item it=first.data;
			if(first==last) { first=last=null;}
			else {first=first.next;}
			return it;
		}
	}
	/**
	 * Provides Iterator()
	 */
	public Iterator<Item> iterator(){
		return new QueueIterable();
	}
	private class QueueIterable implements Iterator<Item>{
		Node<Item> current;
		public QueueIterable() {
			current=first;
		}
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
