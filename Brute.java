

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 2/25/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Brute {

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
        /* convert to points */
        for(int i = 1; i < lines.length - 1; i+=2 ) {
            x = Integer.parseInt(lines[i]);
            y = Integer.parseInt(lines[i + 1]);
            p = new Point(x, y);
            pts[cnt++] = p;
        }
        for (Point ps: pts) {
             ps.draw();
        }
        // &&
        // equal(pts[k], pts[l], pts[i].SLOPE_ORDER)
        /* find line segments */
        for(int i = 0; i < pts.length; i++) {
            for(int j = i + 1; j < pts.length; j++) {
                for(int k = j + 1; k < pts.length; k++) {
                    if (equal(pts[j], pts[k], pts[i].SLOPE_ORDER)) {
                        for(int l = k + 1; l < pts.length; l++) {
                            if (equal(pts[l], pts[k], pts[i].SLOPE_ORDER)) {
                                //pts[l].drawTo(pts[i]);
                               // System.out.println("i: " + pts[i]  + "  j: " + pts[j] + "k: " + pts[k]  + "  l: " + pts[l] );
                               // System.out.println(pts[i] + " -> " + pts[j] + " -> " + pts[k] + " -> " + pts[l] );
                                //pts[i].draw();
                                //pts[j].draw();
                                //pts[k].draw();
                                //pts[l].draw();
                                // ;
                                Point[] line = { pts[i], pts[j], pts[k] ,pts[l]};
                                insertionSort(line,0,4);
                                line[0].drawTo(line[3]);
                                System.out.println(line[0] + " -> " + line[1] + " -> " + line[2] + " -> " + line[3] );
                                 // pts[i].drawTo(pts[maxDistant(pts, i, j, k, l)]);
                                //pts[i].drawTo(pts[l]);
                               // pts[i].drawTo(pts[j]);
                               // pts[j].drawTo(pts[k]);
                                //pts[k].drawTo(pts[l]);
                               // break;
                            }
                        }
                    }
                }
            }
        }
    }
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

    private static boolean equal( Point o1 , Point o2, Comparator<Point> cmp) {
        return cmp.compare(o1, o2) == 0 ;
    }
    private static  void exchng(Comparable arr[], int x, int y) {
        Comparable c = arr[x];
        arr[x] = arr[y];
        arr[y] = c;
    }
    private static boolean less(Comparable a, Object b) {
        return (a.compareTo(b) < 0);
    }
    private static int[] maxDistant(Point[] pts, int first_index, int second_index, int third_index, int fourth_index) {
        //Point origin = pts[origin_index];
        //Point second = pts[second_index];
        ///Point third = pts[third_index];
        //Point fourth = pts[fourth_index];
        int[] maxMin = new int[2];
        Point[] line = { pts[first_index], pts[second_index], pts[third_index], pts[fourth_index]};
        Arrays.sort(line);

        Point end_point = line[3];
        Point start_point = line[0];
        if      (end_point.equals(pts[first_index]))    { maxMin[0] = first_index;  }
        else if (end_point.equals(pts[second_index]))   { maxMin[0] = second_index; }
        else if (end_point.equals(pts[third_index]))    { maxMin[0] = third_index;  }
        else                                            { maxMin[0] = fourth_index; }

        if      (start_point.equals(pts[first_index]))  { maxMin[1] = first_index;  }
        else if (start_point.equals(pts[second_index])) { maxMin[1] = second_index; }
        else if (start_point.equals(pts[third_index]))  { maxMin[1] = third_index;  }
        else                                            { maxMin[1] = fourth_index; }

        return maxMin;

    }
}
