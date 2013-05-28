import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the double ended queue datatype. 
 * with Java Generics support
 * @author Prasanth Ravi
 * @param <Item> type of data to store
 */
public class Deque<Item> implements Iterable<Item> {
	
	/**
	 * A double ended node  with previous and next links.
	 * @author dare
	 *
	 * @param <Item> type of data
	 */
	@SuppressWarnings("hiding")
	private class DblNode<Item> {
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
	
	/** leftmost node. */
	private DblNode<Item> left;
	/** rightmost node. */
	private DblNode<Item> right;
	/** number of nodes. */
	private int size;
	
	/** Initialize to empty deque. */
	public Deque() {
		left = null;
		right = null;
		size = 0;
	}
	/**
	 * @return <code>true</code> if queue is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	/**
	 * @return size of queue
	 */
	public int size() {
		return size;
	}
	/**
	 * inserts <code>value</code> onto the left of the queue.
	 * @param value data to be stored in node
	 * @throws Exception  when null is passed as data
	 */
	public void addFirst(final Item value) throws Exception {
		if (value == null) { throw new NullPointerException(); } 
		if (left == null) { left = right = new DblNode<Item>(value, null,null); }
		else if (left == right) { 
			//DblNode<Item> tmp = new DblNode<Item>(value,left,null);
			
			//right.prev
			left = new DblNode<Item>(value, right,null);
			//left.next = right;
			right.prev = left;
		}
		else {
			DblNode<Item> tmp = new DblNode<Item>(value,left,null);
			left.prev=tmp;
			left = tmp;
		}
		size++;
	}
	/**
	 * inserts <code>value</code> onto the right of the queue
	 * @param value data to be stored in node
	 * @throws Exception  when null is passed as data
	 */
	public void addLast(final Item value) throws Exception {
		if (value == null) { throw new NullPointerException(); }
		if (right == null) { left = right = new DblNode<Item>(value, null, null); }
		else if (left == right) { 
			right= new DblNode<Item>(value, null, left);
			left.next = right;
		}
		else { 
			//Node<Item> tmp = new Node<Item>(value, null);
			DblNode<Item> tmp = new DblNode<Item>(value, null, right);
			right.next=tmp;
			right = tmp; 
		}
		size++;
	}
	/**
	 * Remove node from left and return the data
	 * @return data of the removed node 
	 * @throws Exception  when deque is empty
	 */
	public Item removeFirst() throws Exception{
		Item it;
		if (isEmpty()) { throw new NoSuchElementException(); } 
		if (left == right) { 
			it = left.data; 
			left = right = null; 
		} 
		else { 
			DblNode<Item> oldleft = left;
			it = left.data; 
			left = left.next; 
			left.prev = null;
			oldleft.data = null;
			oldleft.next = null;
			oldleft.prev = null;
			oldleft = null;
		}
		size--;
		return it;
	}
	/**
	 * Remove node from right and return the data.
	 * @return data of the removed node 
	 * @throws Exception  when deque is empty
	 */
	public Item removeLast() throws Exception{
		Item it;
		if (isEmpty()) { throw new NoSuchElementException(); } 
		if (left == right) { 
			it = right.data; 
			left = right = null; 
		} 
		else { 
			DblNode<Item> oldright = right;
			it = right.data; 
			right = right.prev; 
			right.next = null;
			oldright.data = null;
			oldright.next = null;
			oldright.prev = null;
			oldright = null;
		}
		size--;
		return it;
	}
	
	/**
	 * API test code.
	 */
	public static void main(final String[] args) {
		int i;
		int rnd;
		int [] max = new int[]{ 1024, 4096, 16384, 128000, 256000, 1024000, 2048000 };
		//int max = 1024;
		Stopwatch st= new Stopwatch();
		Deque<String> dq = new Deque<String>();
		try {
			System.out.println("   N      Seconds");
			Stopwatch sr = new Stopwatch();
			for (int j = 0 ; j < 7 ; j++) {
				for (i = 0; i < max[j]  ; i++) {
					rnd=StdRandom.uniform(4);
					switch(rnd) {
						case 0: 
							dq.addFirst("Item " + i);
							break;
						case 1:
							dq.addLast("Item " + i);
							break;
						case 2:
							try { 
								 dq.removeFirst();  
							} catch (Exception e) {
								;
							}
							break;
						case 3:
							if(!dq.isEmpty()) { dq.removeLast(); }
							break;
						case 4:
							System.out.println("wtf??");
							int cnt=0;
							for (String s : dq) { cnt++; }
							if (cnt!=dq.size()) { System.out.println("Sizes do not match!!"); }
							break;
						default: 
						{ ;}
					}
					
				/*if (i % 2 == 0) { dq.addFirst("Item" + i); }
				else 			{ dq.addLast("Item" + i); }*/
				}
				System.out.println(""+max[j] +"      "+ sr.elapsedTime());
				//max= max * 4;
				
			}
			System.out.println("Size= " + dq.size());
			//for (String s : dq) { System.out.println(s); }
			//for (i = 0; i < max; i++) { System.out.println(dq.removeLast()); }
			//for (i = 0; i < max; i++) { System.out.println(dq.removeLast()); }
		} catch (Exception e) {
			System.out.println(e.getStackTrace()[0]);
			
		}
		//System.out.println("Time for " + max[7] + " random calls: " + st.elapsedTime());
	}
	/**
	 * Provide Iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new dequeIterator();
	}
	
	/**  Iterator for deque */
	
	private class dequeIterator implements Iterator<Item> {
		
		private DblNode<Item> current;
		private int sz;
		dequeIterator() {
			current = left;
			sz=size();
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (sz != 0);
		}

		@Override
		public Item next() throws RuntimeException {
			// TODO Auto-generated method stub
			if (!hasNext()) { throw new NoSuchElementException(); }
			Item it=current.data;
			current = current.next;
			sz--;
			return it;
		}

		@Override
		public void remove() throws RuntimeException {
			// TODO Auto-generated method stub
			throw new  UnsupportedOperationException();
		}
		
	}


}
