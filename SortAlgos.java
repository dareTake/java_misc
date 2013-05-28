import sun.misc.Sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 2/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortAlgos<Item> {
      //Item[] arr;

    /***     UTILITY FUNCTIONS   ***/

    /**
     * Exchange two values in an array.
     * @param arr  array containing the values
     * @param x    value I
     * @param y    value II
     */
    private static  void exchng(Comparable arr[], int x, int y) {
        Comparable c = arr[x];
        arr[x] = arr[y];
        arr[y] = c;
    }

    /**
     * Use compareTo to find out if one object is less than the other.
     * @param a  Object a of type Comparable
     * @param b  Object a of type Comparable
     * @return   True if a < b
     */
    private static boolean less(Comparable a, Object b) {
        return (a.compareTo(b) < 0);
    }

    /**
     * Use compareTo to find out if one object is less than the other.
     * @param a  Object a of type Comparable
     * @param b  Object a of type Comparable
     * @return   True if a <= b
     */
    private static boolean lessOrEqual(Comparable a, Object b) {
        return (a.compareTo(b) <= 0);
    }

    /**
     *
     * @param a      array to be checked
     * @param begin  start of range
     * @param end    end of range
     * @return       if range is sorted
     */
    private static boolean isSorted(Comparable[] a, int begin , int end) {
        boolean sorted = true;
        for(int i = begin; i < end; i++) {
            if(!lessOrEqual(a[i], a[i+1])) {
                sorted = false;
            }
        }
        return sorted;
    }

    /***     SORTING FUNCTIONS   ***/

    /**
     * Shell sort implementation
     * @param array  array to be sorted
     * @return new sorted array
     */
    public static Comparable[] shellSort(Comparable[] array) {
        Comparable[] arr = array.clone();
        int h=1;
        while (h < (arr.length) / 3 ) { h = 3 * h + 1; }
        while (h >= 1) {
            for (int i = 0; i < arr.length; i ++) {
                for(int j = i; j - h >= 0 ; j = j - h) {
                    if (less(arr[j], arr[j - h]))
                        exchng(arr,j - h, j);
                    else break ;
                }
            }
            h= h / 3;
        }
        return  arr;
    }

    /**
     * Insertion sort implementation
     * @param array  array to be sorted
     * @return new sorted array
     */
    public static Comparable[] insertionSort(Comparable[] array) {
        Comparable[] arr= array.clone();
        int i, j;
        for (i = 0; i < arr.length; i ++) {
            for(j = i; j > 0 ; j--) {
                    if (less(arr[j], arr[j - 1]))
                         exchng(arr,j - 1, j);
                    else break ;

            }
        }
        return arr;
    }

    /**
     * Selection sort implementation
     * @param array  array to be sorted
     * @return new sorted array
     */
    public static Comparable[] selectionSort(Comparable[] array) {
        Comparable[] arr = array.clone();
        int min;
        for (int i = 0; i < arr.length ; i++) {
              min = i;
              for (int j = i; j < arr.length; j++ ) {
                  if (less(arr[j], arr[min])) { min = j; }
              }
            exchng(arr,min,i);
        }
        return arr;
    }

    /**
     * Bubble sort implementation
     * @param array  array to be sorted
     * @return new sorted array
     */
    public static Comparable[] bubbleSort( Comparable[] array) {
        Comparable[] arr= array.clone();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (less(arr[j],arr[i])) { exchng(arr, i, j); }
            }
        }
        return arr;
    }

    /* MergeSort Functions */
    /**
     * API window to access merge sort
     * @param arr array to be sorted
     * @return  new sorted array
     */
    public static Comparable[] mergeSort(Comparable arr[]) {
        Comparable[] aux = arr.clone();
        Comparable[] aux2 = arr.clone();
        //Comparable[] arrCpy = new Comparable[arr.length];

        mergeSort(aux, aux2, 0, arr.length - 1);
        return aux;
    }
    /**
     * Merge subroutine
     * @param arr  array to be sorted
     */
    private static void mergeSort(Comparable arr[], Comparable[] aux, int low, int high) {

        int mid = low + (high - low) / 2;
        if( low < high ) {
            mergeSort(arr, aux, low, mid);
            mergeSort(arr, aux, mid + 1, high);
            merge(arr, aux, low, mid, high);
        }

    }
    /**
     * Merge two sub arrays
     * @param a    array to be sorted
     * @param aux  extra array for storing values during merge
     * @param low  begining of range
     * @param mid  middle of range
     * @param high end of range
     */
    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){

        assert isSorted(a, low, mid);
        assert isSorted(a, mid + 1, high);

        int i =low;
        int j = mid + 1;
        int k ;

        for(k = low; k <= high; k++) {

            if      (i > mid)           { aux[k] = a[j++]; }
            else if (j > high)          { aux[k] = a[i++]; }
            else if (less(a[i], a[j]))  { aux[k] = a[i++]; }
            else                        { aux[k] = a[j++]; }
        }


        for(k = low; k <= high; k++) {
            a[k] = aux[k];
        }

        assert isSorted(a,low,high);
        //assert false;
    }

    /* QuickSort Functions */

    /**
     * Sort an array and returns a new array.
     * Shuffles the array.
     * @param a array to be sorted
     * @return  new array which is sorted
     */
    public static Comparable[] quickSort(Comparable[] a) {
       Comparable[] arr = a.clone();
       StdRandom.shuffle(arr);
       quickSort(arr, 0, arr.length - 1);
       return arr;
    }
    /**
     * Sorts an array by recursively calling partition and itself.
     * @param a    array to be sorted
     * @param low  start of range
     * @param high end of range
     */
    private static void quickSort(Comparable[] a, int low, int high) {
        if(low < high){
            int pivot_index = partition(a, low, high);
            quickSort(a, pivot_index + 1, high);
            quickSort(a, low, pivot_index);

            //assert  isSorted(a, low, high);
            //assert  isSorted(a, low, high);
        }
    }
    /**
     * Partitions a range such that [low,pivot) < pivot &&  (pivot,high] > pivot.
     * Pivot is taken as 1st element of range.
     * @param a     array containing range
     * @param low   start of range
     * @param high  end of range
     * @return      position of pivot
     */
    private static int partition(Comparable[] a, int low, int high) {
        int pivot = low;
        int i = low + 1;
        int j = high;
        while(true ) {

            while(less(a[i], a[pivot]) && i != high) { i++; }
            while(!less(a[j], a[pivot]) && j !=low ) { j--; }
            if(i >= j) break;
            exchng(a, i, j);

        }
        exchng(a, j, pivot);
        //
        return j;
    }

    /**
     * Sort an array and returns a new array.
     * Better then quick sort if large number of duplicate keys.
     * Shuffles the array.
     * @param a array to be sorted
     * @return  new array which is sorted
     */
    public static Comparable[] threeWayquickSort(Comparable[] a) {
        Comparable[] arr = a.clone();
        StdRandom.shuffle(arr);
        threeWayQuickSort(arr, 0, arr.length - 1);
        return arr;
    }
    /**
     *
     * @param a    array containing range to be sorted
     * @param low  start of range
     * @param high end of range
     */
    private static void threeWayQuickSort(Comparable[] a, int low, int high) {
      if( low < high ) {
          int lt = low;
          int gt = high;
          int i = lt + 1;
          int cmp;
          Comparable v = a[low];

          while(i <= gt) {
               cmp = a[i].compareTo(v);
              if(cmp < 0) { exchng(a, lt++, i++); }
              else if(cmp > 0) { exchng(a, i, gt--); }
              else { i++; }
          }

          threeWayQuickSort(a, low, lt - 1);
          threeWayQuickSort(a, gt + 1, high);
      }
    }

    /**
     * Returns the nth element in the array if the array was sorted.
     * @param a   array containing the element
     * @param nth nth index to get the element from
     * @return    the element at nth index
     */
    public static Comparable select(Comparable[] a, int nth) {
        Comparable[] arr = a.clone();
        StdRandom.shuffle(arr);
        int n = nth;
        int low = 0;
        int high = arr.length - 1;
        int j = low;
        while(low < high) {
            j = partition(a, low, high);
            if (n > j) { low = j + 1; }
            else if (n < j) { high = j  - 1; }
            else { return a[j]; }
        }
        return a[n];
    }


    /**     API TEST FUNCTIONS   **/
    public static void main(final String[] args) {
        int max = 50000;
        Stopwatch st;
        int sorts=0;
        int[] arr = new int[max];
        Integer [] x;
        Object[] sortedArr = new Object[5];
        System.out.println("Total elements: " + max);
        System.out.println("Generating randomized elements.");
        RandomGen.genInt(arr);
        x = Utils.convInteger(arr);

//        /** Test code for selection sort */
//        st = new Stopwatch();
//        sortedArr[sorts++] = (Integer[]) selectionSort(x);
//        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
//        System.out.println("Selection sort: " + st.elapsedTime());

        /* Test code for bubble sort*//*
        st = new Stopwatch();
        y = (Integer[]) bubbleSort(x);
        //for (Integer i : y) { System.out.println(i); }
        System.out.println("Bubble sort: " + st.elapsedTime());
        /*//*/

//        /** Test code for insertion sort */
//        st = new Stopwatch();
//        sortedArr[sorts++] = (Integer[]) insertionSort(x);
//        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
//        System.out.println("Insertion sort: " + st.elapsedTime());

        /** Test code for shell sort */
        st = new Stopwatch();
        sortedArr[sorts++] = (Integer[]) shellSort(x);
        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
        System.out.println("Shell sort: " + st.elapsedTime());


        /** Test code for merge sort */
        st = new Stopwatch();
        sortedArr[sorts++]=  (Integer[]) mergeSort(x);
        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
        System.out.println("Merge sort: " + st.elapsedTime());

        /** Test code for quick sort */
        st = new Stopwatch();
        sortedArr[sorts++]=  (Integer[]) quickSort(x);
        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
        System.out.println("Quick Sort: " + st.elapsedTime());

        /** Test code for 3 way quick sort */
        st = new Stopwatch();
        sortedArr[sorts++]=  (Integer[]) threeWayquickSort(x);
        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
        System.out.println("Quick Sort(3 way): " + st.elapsedTime());

        /** Test code for quick select */
        st = new Stopwatch();
        int nth = StdRandom.uniform(x.length -1) ;
        //Comparable res=
        select(x,nth);
        //for (Integer i : (Integer[]) sortedArr[sorts -1]) { System.out.println(i); }
        System.out.println("Quick Select(" + nth + "): " + st.elapsedTime());

        boolean resultsEqual = true;
        for(int i = 0; i < sorts - 1; i++) {
            if (!Arrays.deepEquals((Object[]) sortedArr[i], (Object[]) sortedArr[i+1])) {
                resultsEqual = false;
            }
        }
        System.out.println("Results equal?  " + resultsEqual );
    }


}
