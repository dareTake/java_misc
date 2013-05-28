
/**
 * Implementation of the Binary Search algorithm
 * @author Prasanth Ravi
 *
 */
public class BinarySearch {

	/** 
	 * API test code
	 */
	public static void main(String[] args) {
		if(args.length <2) {
			System.out.println("Less than 2 arguments!!");
			System.exit(1);
		}
		
		int[] arr=In.readInts(args[0]);
		int[] items= In.readInts(args[1]);
		int[] sorted_arr;
		MergeSort ms=new MergeSort(arr);
		ms.sort(0, arr.length-1);
		sorted_arr=ms.getArray();
		int foundIndex;
		for(int item : items) {
			foundIndex= rank(item,sorted_arr);
		    if(foundIndex > 0)
		    	System.out.println("The item: " + item + " was found");
		    else
		    	System.out.println("Could not find the item: " + item );
		}
		
	}
	/**
	 * Recursive implementation of BS
	 * 
	 * @param item  data to search for
	 * @param arr 	array to be searched
	 * @param start start of the array range
	 * @param end   end of the array range
	 * @return index of array where match has been found 
	 * 			or -1 otherwise
	 */
	public static int rank_recr(int item,int arr[],int start, int end){
		if (start>end) return -1;
		int mid = (start+end)/2;
		if     (item < arr[mid])    return rank_recr(item,arr,start,mid);
		else if(item > arr[mid])    return rank_recr(item,arr,mid+1,end);
		else return mid;
	}
	
	/**
	 * Non recursive implementation of BS
	 * 
	 * @param item data to search for
	 * @param arr array to be searched
	 * @return index of array where match has been found 
	 * 			or -1 otherwise
	 */
	public static int rank(int item,int arr[]) {
		int start=0;
		int end=arr.length;
		int mid;
		while(start<end) {
			mid=(start+end)/2;
			if (item<arr[mid]) end=mid;
			else if(item>arr[mid]) start=mid+1;
			else return mid;
		}
		return -1;
	}

}
