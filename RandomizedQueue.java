import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author dare
 *
 */
/**
 public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()           // construct an empty randomized queue
   public boolean isEmpty()           // is the queue empty?
   public int size()                  // return the number of items on the queue
   public void enqueue(Item item)     // add the item
   public Item dequeue()              // delete and return a random item
   public Item sample()               // return (but do not delete) a random item
   public Iterator<Item> iterator()   // return an independent iterator over items in random order
}
 
 *Throw a java.lang.NullPointerException if the client attempts to add a null item; 
 *throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue; 
 *throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; 
 *throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

Your randomized queue implementation should support each randomized queue operation (besides creating an iterator) 
in constant amortized time and use space proportional to the number of items currently in the queue. 
That is, any sequence of M randomized queue operations (starting from an empty queue) should take at most cM steps in the worst case, 
for some constant c. Additionally, your iterator implementation should support construction in time linear in the number of items and 
it should support the operations next() and hasNext() in constant worst-case time; you may use a linear amount of extra memory per iterator. 
The order of two or more iterators to the same randomized queue should be mutually independent; 
each iterator must maintain its own random order. 
 *
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	//Fields
	/** The array that holds the items. */
	private Item[] items;
	/** A boolean array to check whether an element in items[] has been dequeued. */
	private boolean[] itemRecv;
	/** Position of the rightmost element. */
	private int tail;
	/** Total size of the array excluding elements that were dequeued. */
	private int size;
	
	/**
	 * Set initial values for size, items, itemRecv & tail.
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[0];
		itemRecv= new boolean[0];
		tail = 0;
		size = 0;
	}
	/**
	 * 
	 * @return size of the queue
	 */
	public int size() {
		return size;
	}
	/**
	 * 
	 * @return whether queue is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	/**
	 * Resizes the array to newSize, then copies all elements 
	 * that were not dequeued. Set all elements of itemRecv to false.
	 * @param newSize size of the new array
	 */
	@SuppressWarnings("unchecked")
	  private void resize(int newSize) {
		//System.out.println("Resizing to " + newSize);
		Item[] arr = (Item[]) new Object[newSize];
		int count = 0;
		for (int i = 0; i < tail; i++) { 
			if (!itemRecv[i]) { arr[count++] = items[i]; }
			items[i] = null;
		}
		itemRecv = new boolean[newSize];
		//for (int i = 0; i < tail; i++) { itemRecv[i] = null ; }
		tail = count;
		size = count;
		items = null;
		
		items = arr;
	}
	/**
	 * Enqueues an item at the end.
	 * If tail is at end of length, either resize to twice the length
	 * or half the length depending on size. 
	 * @param value item to store
	 * @throws Exception when null item is passed
	 */
	public void enqueue(Item value) throws Exception {
		int curLength=items.length;
		if (value == null) { throw new NullPointerException(); }
		if (tail < curLength) { items[tail++] = value; } 
		else {
			int length = (curLength == 0) ? 1 : curLength;
			if (size <= (length / 2)) { resize(length); }
			else { resize(2 * length ); }
			items[tail++] = value;
		}
		size++;
		//System.out.println( "Inserted " + value + " at " + (tail - 1));
	}
	/**
	 * Remove an item chosen at random and returns it.
	 * @return item that was dequeued
	 * @throws RuntimeException if the queue is empty
	 */
	public Item dequeue() throws RuntimeException {
		if (size <= 0) { throw new NoSuchElementException(); }
		int rnd = StdRandom.uniform(tail);
		Item it;
		while(itemRecv[rnd]) { rnd = StdRandom.uniform(tail); }
		//System.out.println( "rand= "+ rand + " bool= " +itemRecv[rand] + " item= " + items[rand]);
		it=items[rnd];
		items[rnd] = null;
		itemRecv[rnd] = true ;
		size--;
		if (size <= (0.25 * items.length)) { resize(items.length / 2); }
		return it;
	}
	/**  
	 * Return an item chosen at random but don't remove it.
	 * */
	public Item sample() throws RuntimeException {
		if(size == 0) { throw new NoSuchElementException(); }
		int rnd = StdRandom.uniform(tail);
		while(itemRecv[rnd]) { rnd = StdRandom.uniform(tail); }
		return items[rnd];
	}
	/** API test code.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch st= new Stopwatch();
		int max = 3;
		RandomizedQueue<String> rqStr = new RandomizedQueue<String>();
		try {
			System.out.println("Empty?=" + rqStr.isEmpty() );
			for( int i = 0; i < max; i++) { rqStr.enqueue(String.valueOf(i)); }
			/*for( int i = 0; i < max; i++) { 
				//rqStr.dequeue();
				System.out.println(rqStr.dequeue()); 
				}*/
			int[] freq = new int[3];
			for( int i = 0; i < 150000; i++) {
					freq[Integer.parseInt(rqStr.sample())]++; 
				}
			System.out.println("A=" + freq[0] + " B=" + freq[1] +" C=" + freq[2]);
			for (String s : rqStr) { System.out.println(s); } 
			for( int i = 0; i < max; i++) {
					rqStr.dequeue();
					//System.out.println(rqStr.dequeue()); 
				}
			System.out.println("Empty?=" + rqStr.isEmpty() );
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Time taken for [" + max + "]: " + st.elapsedTime() );
	}
	
	/** Iterator functionality */
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomQueueIterator();
	}
	private class RandomQueueIterator implements Iterator<Item> {
		private boolean[] recv;
		private int sz;
		private int tl;
		
		RandomQueueIterator() {
			tl=tail;
			sz = size;
			recv = itemRecv.clone();
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			//System.out.println("hs");
			return (sz > 0);
		}

		@Override
		public Item next() throws RuntimeException{
			// TODO Auto-generated method stub
			
			if (!hasNext()) { throw new NoSuchElementException(); }
			
			int rnd = StdRandom.uniform(tl);
			while(recv[rnd]) {
				//System.out.println("size=" + sz);
				rnd = StdRandom.uniform(tl); 
				}
			//System.out.println( "rand= "+ rand + " bool= " + recv[rand] + " item= " + items[rand]);
			recv[rnd] = true ;
			sz--;
			return items[rnd];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
}
