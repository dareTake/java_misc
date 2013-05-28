/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 2/15/13
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    /**
     * Converts int[] to Integer[].
     * @param array array to convert
     * @return the converted array
     */
    public static Integer[] convInteger(int[] array) {
        Integer[] arr = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = Integer.valueOf(array[i]);
        }
        return arr;
    }

    /**
     * Converts Integer[] to int[].
     * @param array array to convert
     * @return the converted array
     */
    public static int[] convInt(Integer[] array) {
        int[] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }

    /**
     *
     */
     public static boolean integerArrEquals(Integer[] a, Integer[] b ) {
         if (a.length != b.length) { return false; }
         boolean equals = true;
         for( int i = 0; i < a.length; i++) {
            if (a[1] != b[i]) { equals = false; }
         }
         return equals;
     }


}
