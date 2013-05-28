/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 2/25/13
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */


import java.util.Arrays;
import java.util.Comparator;
public class Fast {
    public static void main(String[] args) {
        assert (args.length == 1);

        //In in = new In(args[0]);
        String[] lines = In.readStrings(args[0]);
        int maxPoints = Integer.parseInt(lines[0]);
        Point[] pts = new Point[maxPoints];
        int x, y;
        Point p;
        int cnt = 0;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        //StdDraw.show(0);
        /* convert to points */
        for(int i = 1; i < lines.length - 1; i+=2 ) {
            x = Integer.parseInt(lines[i]);
            y = Integer.parseInt(lines[i + 1]);
            p = new Point(x, y);
            pts[cnt++] = p;
        }
        Stopwatch st = new Stopwatch();
        for (Point ps: pts) {
            ps.draw();
        }
        StdDraw.show(0);
        //
        Point[] pts_copy = pts.clone();
        Point[] pts_drawn = new Point[pts.length];
        Point[] sameSlope = new Point[pts.length];
        double slope;
        int numSameSlope;
        int drawn = 0;
        //Stopwatch st = new Stopwatch();
        for(int i = 0; i< pts.length ; i++) {
            Point first ;
            first = pts[i];
            Comparator cmp = first.SLOPE_ORDER;
            quickSort(pts_copy,cmp);
           //Arrays.sort(pts_copy,cmp);
            for (int j = 1; j < pts_copy.length - 2; j++) {
                sameSlope[0] = first;
                sameSlope[1] = pts_copy[j];
                numSameSlope = 2;
                slope = first.slopeTo(pts_copy[j]);
//                while(first.slopeTo(pts_copy[j + numSameSlope + 1]) == slope) {
//                    sameSlope[numSameSlope + 1] = pts_copy[j + numSameSlope + 1] ;
//                    numSameSlope++;
//                }
                for(int k = j + 1;  k < pts_copy.length   ; k ++) {
                    Double slopeNext = first.slopeTo(pts_copy[k]);
                    double mD = Math.abs(slopeNext);
                    double mDD = Math.abs(slope);
                    if ( Math.abs(mD - mDD) ==0 || Math.abs(mD - mDD) < 0.2) {
                        sameSlope[numSameSlope++] = pts_copy[k] ;
                    }
                    else break;
                    //numSameSlope++;
                }
                if(numSameSlope > 3) {
                    //Point[] line = { first, second, third ,fourth};
                    insertionSort(sameSlope,0,numSameSlope);
                    //Arrays.sort(sameSlope);
                    boolean[] ptsInArr = pointsInArrayRange(pts_drawn,sameSlope,drawn - 1,numSameSlope);
                    if(!lineDrawn(ptsInArr)) {
                        sameSlope[0].drawTo(sameSlope[numSameSlope -1 ]);
                        System.out.print(sameSlope[0]);
                        for(int l=1 ; l< ptsInArr.length - 1; l++) {
                            System.out.print(" -> " + sameSlope[l]);
                        }
                        System.out.print(" -> " + sameSlope[numSameSlope -1] + "\n");
                        for(int l=0 ; l< ptsInArr.length; l++) {
                            if (!ptsInArr[l]) { pts_drawn[drawn++] = sameSlope[l] ;}
                        }
                       insertionSort(pts_drawn,0,drawn);
                    }
                }
            }
        }
        StdDraw.show(0);
    }


                    //sameSlope[0].drawTo(sameSlope[numSameSlope - 1]);
                    //System.out.println(sameSlope[0] + " -> " + line[1] + " -> " + line[2] + " -> " + line[3] );

//                    second = pts_copy[j];
//                    third  = pts_copy[j + 1];
//                    fourth = pts_copy[j + 2];
//                    if(equal(third, second, cmp) && equal(fourth, third, cmp)  ) {
//                        boolean[] ptsInArr = pointsInArray(pts_drawn,pts_copy[0],pts_copy[j],pts_copy[j + 1], pts_copy[j + 2], drawn -1 );
//                        if(!lineDrawn(ptsInArr)) {
//                            Point[] line = { first, second, third ,fourth};
//                            insertionSort(line,0,4);
//                            line[0].drawTo(line[3]);
//                            System.out.println(line[0] + " -> " + line[1] + " -> " + line[2] + " -> " + line[3] );
////                        if (!(binarySearchInRange(pts_drawn, pts_copy[0], 0 , drawn - 1) >= 0)) {pts_drawn[drawn++] = pts_copy[0]; }
////                        if (!(binarySearchInRange(pts_drawn, pts_copy[j], 0 , drawn - 1) >= 0)) {pts_drawn[drawn++] = pts_copy[j]; }
////                        if (!(binarySearchInRange(pts_drawn, pts_copy[j + 1], 0 , drawn - 1) >= 0)) {pts_drawn[drawn++] = pts_copy[j + 1]; }
////                        if (!(binarySearchInRange(pts_drawn, pts_copy[j + 2], 0 , drawn - 1) >= 0)) {pts_drawn[drawn++] = pts_copy[j + 2]; }
//                            if(!ptsInArr[0]) { pts_drawn[drawn++] = pts_copy[0]; }
//                            if(!ptsInArr[1]) { pts_drawn[drawn++] = pts_copy[j]; }
//                            if(!ptsInArr[2]) { pts_drawn[drawn++] = pts_copy[j + 1]; }
//                            if(!ptsInArr[3]) { pts_drawn[drawn++] = pts_copy[j + 2]; }
//                            insertionSort(pts_drawn,0,drawn);
//                            // int k=0;
//                            break;
//                        }
//                    }
//                }
//                }
//
//            }
//
//        }
        //System.out.println("Time: " +st.elapsedTime());
        /* find line segments */
//    }
    private static void insertionSort(Comparable[] arr,int low, int high) {
        //Comparable[] arr= array.clone();
        int i, j;
        for (i = low ; i < high; i ++) {
            for(j = i; j > 0 && less(arr[j], arr[j - 1]) ; j--) {
                    exchng(arr,j - 1, j);
            }
        }
      //  return arr;
    }

    private static int binarySearchInRange(Comparable[] pts, Comparable key, int low, int high) {
        int mid ;
        while(low <= high) {
            mid = low + ((high - low) / 2 );
            if(less(key, pts[mid])) { high = mid - 1 ; }
            else if(key.compareTo(pts[mid]) == 1) { low = mid + 1; }
            else { return mid; }
        }
        return -1;
    }
    private static boolean[] pointsInArrayRange(Point[] ptsArr, Point[] pts, int rangeOfArr, int rangeOfpts) {
        //boolean pD =  binarySearchInRange(pts_drawn, p, 0, range) >= 0;
        //boolean qD =  binarySearchInRange(pts_drawn, q, 0, range) >= 0;
        //boolean rD =  binarySearchInRange(pts_drawn, r, 0, range) >= 0;
        //boolean sD =  binarySearchInRange(pts_drawn, s, 0, range) >= 0;
        //boolean[] ptsInArr = { pD, qD, rD, sD};
        boolean[] blPts = new boolean[rangeOfpts];
        for(int i = 0; i < blPts.length ; i++) {
            blPts[i] = (binarySearchInRange(ptsArr, pts[i], 0, rangeOfArr) >= 0);
        }
        //
        return blPts;
    }
//    private static boolean lineDrawn(boolean[] arr) {
//        boolean pD = arr[0];
//        boolean qD = arr[1];
//        boolean rD = arr[2];
//        boolean sD = arr[3];
//
//       if      ((pD && qD) || (pD && rD) || (pD && sD)) { return true; }
//       else if ((qD && pD) || (qD && rD) || (qD && sD)) { return true; }
//       else if ((rD && pD) || (rD && qD) || (rD && sD)) { return true; }
//       else if ((sD && pD) || (sD && qD) || (sD && rD)) { return true; }
//
//      return false;
//    }
    private static boolean  lineDrawn(boolean[] arr) {
        int count = 0;
        boolean allDrawn = true;
        for (int i = 0; i< arr.length; i++ ) {
            //if (arr[i]) { count++; }
            if(!arr[i]) { allDrawn = false;  }
        }
//        if( count == arr.length )
//            return true;
//        else
//            return false;
         return  allDrawn;

    }
    private static boolean ptInArray(Point[] arr, Point p) {

        for(int i=0; i< arr.length && arr[i]!=null; i++) {
            if (arr[i].equals(p)) { return true; }
        }
        return false;
    }

    /**
     * Sort an array and returns a new array.
     * Shuffles the array.
     * @param a array to be sorted
     * @return  new array which is sorted
     */
    private static void quickSort(Point[] a, Comparator<Point> cmp) {
        //Comparable[] arr = a.clone();
        StdRandom.shuffle(a);
        quickSort(a, 0, a.length - 1, cmp);
        //return arr;
    }
    /**
     * Sorts an array by recursively calling partition and itself.
     * @param a    array to be sorted
     * @param low  start of range
     * @param high end of range
     */
    private static void quickSort(Point[] a, int low, int high, Comparator<Point> cmp) {
        if(low < high){
            int pivot_index = partition(a, low, high, cmp);
            quickSort(a, pivot_index + 1, high, cmp);
            quickSort(a, low, pivot_index, cmp);

            //assert  isSorted(a, low, high);
            //assert  isSorted(a, low, high);
        }
    }
    private static Point select(Point[] a, int nth, Comparator<Point> cmp) {
        //Comparable[] arr = a.clone();
        StdRandom.shuffle(a);
        int n = nth;
        int low = 0;
        int high = a.length - 1;
        int j = low;
        while(low < high) {
            j = partition(a, low, high, cmp);
            if      (n > j) { low  = j + 1;  }
            else if (n < j) { high = j - 1;  }
            else            { return  a[j];  }
        }
        return a[n];
    }

    private static int partition(Point[] a, int low, int high, Comparator<Point> cmp) {
        int pivot = low;
        int i = low + 1;
        int j = high;
        while(true ) {

            while(less(a[i], a[pivot], cmp) && i != high) { i++; }
            while(!less(a[j], a[pivot], cmp) && j !=low ) { j--; }
            if(i >= j) break;
            exchng(a, i, j);

        }
        exchng(a, j, pivot);
        //
        return j;
    }
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
    private static boolean less(Point a, Point b,Comparator<Point> cmp) {
        return (cmp.compare(a,b) < 0);
    }

    private static boolean equal( Point o1 , Point o2, Comparator<Point> cmp) {
        return cmp.compare(o1, o2) == 0 ;
    }
    private static boolean less(Comparable a, Object b) {
        return (a.compareTo(b) < 0);
    }
}
