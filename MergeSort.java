/**
 * Mergesort implementation
 * @author Prasanth Ravi
 *
 */
public class MergeSort {

    private int[] numbers;
    private int[] tmp;
    private long inversions;
    
    /**
     * 
     * @param input array to be sorted
     */
    public MergeSort(int[] input) {
    	numbers=new int[input.length-1];
    	numbers=input.clone();
    	inversions=0;
    }
    
    /**
     * 
     * @return array of numbers
     */
    public int[] getArray() {
    	return numbers;
    } 
    /**
     * 
     * @return number of inversions
     */
    
    public long getInversions() {
    	return inversions;
    }
    /**
     * Sorts two N/2 sub arrays and merges them
     * 
     * @param start start of the range 
     * @param end  end of the range
     */
    public void sort(int start , int end ) {
    	if(start<end){
    		int middle=(start+end)/2;
    		sort( start , middle);
    		sort(middle+1 , end);
    		merge(start,middle, end);
    	}
    }
    /**
     * Merges two lists
     * 
     * @param start start of the range
     * @param middle middle of the range
     * @param end end of the range
     */
    private void merge( int start, int middle, int end) {
        tmp=new int[end-start+1];
        int count=0;
        int left=start;
        int right=middle+1;
        
        //are there elements in both arrays?
        while(left <=middle && right<=end) {
        	if(numbers[left]<=numbers[right])
        		tmp[count++]=numbers[left++];
        	else {
        		tmp[count++]=numbers[right++];
        		inversions+=(middle-left)+1;
        	}
        }

        //copy elems from left array
        while(left<=middle)
        	tmp[count++]=numbers[left++];

        //copy elems from right array
        while(right<=end)
        	tmp[count++]=numbers[right++];

        //copy the values back from the temp array
        for(int i=0;i<count;i++) 
        	numbers[start+i]=tmp[i];
	
    }
    
}
