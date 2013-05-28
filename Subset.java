/**
 *  Subset client. Write a client program Subset.java that takes a command-line integer k, 
 *  reads in a sequence of N strings from standard input using StdIn.readString(), and prints out exactly k of them, 
 *  uniformly at random. Each item from the sequence can be printed out at most once. You may assume that k ≥ 0 
 *  and no greater than the number of string on standard input.

    % echo A B C D E F G H I | java Subset 3       % echo AA BB BB BB BB BB CC CC | java Subset 8
    C                                              BB
    G                                              AA
    A                                              BB
                                                   CC
    % echo A B C D E F G H I | java Subset 3       BB
    E                                              BB
    F                                              CC
    G                                              BB

Your client should use only constant space plus one object either of type Deque or of type RandomizedQueue; 
use generics properly to avoid casting and compiler warnings. It should also use time and space proportional 
to at most N in the worst case, where N is the number of strings on standard input. (For an extra challenge, 
use space proportional to k.) It should have the following API.

    public class Subset {
       public static void main(String[] args)
    }


 * @author dare
 *
 */
public class Subset {

	/**
	 * @param args
	 */
	//private int k;
	//private int[] rnd_numbers;
	/*
	 * private int binarySearch(int[] arr, int num) {
		int low = 0;
		int high = arr.length;
		while(low <= high) {
			int mid = low + (high - low);
			if (num < arr[mid]) { high = mid -1 ; }
			else if (num > arr[mid]) { low = mid + 1 ; }
			else return mid;for( int i = 0; i < max; i++) { 
				System.out.println(rqStr.dequeue()); 
				}
		}
		return -1;
	}
	private boolean contains(int[] arr, int key) {
		if(binarySearch(arr, key) != -1 ) { return true; }
		else { return false; }
	}
	private insertionSort(int[] arr) {
		
	}*/
	/*private boolean contains(int[] arr, int key) {
		boolean found= false;
		for(int i=0; i< arr.length ; i++) {
			if (arr[i] == key) { found = true; break; }
		}
		return found;
	}
	
	private int[] createRandomArray( int N) {
		int[] randomSet = new int[N];
		int rnd = StdRandom.uniform(N);
		for(int i = 0;i < N; i++) {
			while(!contains(randomSet,rnd)) { rnd = StdRandom.uniform(N); }
			randomSet[i] = rnd;
		}
		return randomSet;
	}*/
	
	public static void main(String[] args) {
		try {
			int k = Integer.parseInt(args[0]);
			//sub.k = Integer.parseInt(args[0]);
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			while(!StdIn.isEmpty()) { rq.enqueue(StdIn.readString()); }
			for( int i = 0; i < k ; i++) { 
				System.out.println(rq.dequeue()); 
				}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
