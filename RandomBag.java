import java.util.Iterator;
import java.util.ArrayList;

public class RandomBag<Item> implements Iterable<Item>{

	Node<Item> head;
	int size;
	
	RandomBag(){
		head=null;
		size=0;
	}
	public boolean isEmpty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	public void add(Item item) {
		if(head==null) { head=new Node<Item>(item,null); }
		else { head=new Node<Item>(item,head); }
		size++;
	}
	
	/**
	 * API test code
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomBag<String> bag=new RandomBag<String>();
		for(int i=0;i<50;i++) { bag.add("Item " + i); }
		for(String s: bag) { System.out.println(s) ; }
		//Iterator<String> it= bag.iterator();
		//while(it.hasNext())
			//System.out.println(it.next());
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new BagRandomAccess();
	}
	
	private class BagRandomAccess implements Iterator<Item>{
        Node<Item> current;
		ArrayList<Integer> accessed;
		public BagRandomAccess() {
			accessed=new ArrayList<Integer>();
		}
		@Override
		public boolean hasNext() {
			return accessed.size() != size();
		}

		@Override
		public Item next() {
			int rnd;
			current=head;
			while( accessed.contains(rnd=StdRandom.uniform(0, size)) ) ;
			accessed.add(rnd);
			for(int i=0;i<rnd;i++)
		        current=current.next;
			return current.data;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
