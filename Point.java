/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate


    private class BySlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            int k = 0;
            //if(slope1 == Double.NEGATIVE_INFINITY || slope1 == Double.NEGATIVE_INFINITY)
              //  return 1;
            //if(slope1 == Double || slope1 == Double.POSITIVE_INFINITY)
              //  return 1;

            if      (slope1 > slope2)     { return  1; }
            else if (slope1 < slope2)     { return -1; }
            else                          { return  0; }
            //return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {

        if      (this.x == that.x && this.y == that.y)    { return Double.NEGATIVE_INFINITY; }
        else if (this.x == that.x)                        { return Double.POSITIVE_INFINITY; }
        else if (this.y == that.y)                        { return ((x==0) ? 0 : ((double)(x-x)/(double)x)); }
        else                                              { return ((double)(that.y - this.y) / (double)(that.x - this.x)); }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if      (this.y > that.y)  { return  1; }
        else if (this.y < that.y)  { return -1; }
        else {
            if (this.x > that.x)   { return  1; }
            else if (this.x < that.x)  { return -1; }
            else {return 0;}
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
