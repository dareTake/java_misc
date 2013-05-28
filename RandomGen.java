/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 2/15/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomGen {
    /**
     * Fills array with random values between [0,max].
     * @param arr array to be filled
     * @param max upper limit
     */
    public static void genInt(int[] arr, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(max + 1);
        }
    }

    /**
     * Fills array with random values between 0 and array length.
     * @param arr array to be filled
     */
    public static void genInt(int[] arr) {
        int max = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(max + 1);
        }
    }


}
