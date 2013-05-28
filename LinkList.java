
public class LinkList<T> {

	/**
	 * @param args
	 */

	private Node<T> head;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkList<String> lnk= new LinkList<String>();
		for(int i=0;i<50;i++)
			lnk.insertEnd( ((Integer)i).toString());
		for(int i=0;i<50;i++) {
			try {
				System.out.println(lnk.retrv(i).data);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		lnk.print_all();
		System.out.println("Length:" + lnk.length());
	}
	public int length() {
		int len=0;
		Node<T> last;
		last=head;
		if( last==null) return -1;
		else while(last.next!=null) {last=last.next;len++;}
		return len+1;
	}
	public void insertStart(T value) {
		
		Node<T> tmp=new Node<T>();
		tmp.data=value;
		if(head==null) tmp.next=null;
		else tmp.next=head;
		head=tmp;
	}
	public void insertEnd(T data) {
		if(head==null) { 
			head=new Node<T>(data,null);
			return;
		}
		Node<T> last=head;
		while(last.next!=null)
			last=last.next;
		Node<T> tmp=new Node<T>(data,null);
		last.next=tmp;
	}
	public void print_all() {
		Node<T> tmp=head;
		if(tmp==null) System.out.println("Nothing to print!!");
		else 
			while(tmp.next!=null) {
				System.out.println(tmp.data);
				tmp=tmp.next;
			}
		System.out.println(tmp.data);
	}
	public Node<T> retrv(int index) throws Exception {
		if (index>length()) throw new Exception("Index lower than length of list");
		else {
			Node<T> elem=head;
			for(int i=0;i<index;i++)
				elem=elem.next;
			return elem;
		}
	}
}
