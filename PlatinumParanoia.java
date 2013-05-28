import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Prasanth Ravi
 * Date: 3/6/13
 * Time: 11:23 PM
 */
final public class PlatinumParanoia {


    public static void main(String args[]) throws InputMismatchException {

        /* Get value from input */
        final Scanner sc = new Scanner(System.in);
        final int initValue = sc.nextInt();

        /* test for range, if not in range throw exception!! */
        if (initValue < 2 || initValue > 1000000000)
                    { throw new InputMismatchException(); }

        /* Do we need to use MemoizedPlatinumBlock or PlatinumBlock?
         *  The 10,00,000 value was calculated by trying
         *  different values for max input.
         *  If there is at least 1000 values to fill the array, use MemoizedPlatinumBlock.
         */
        if(initValue > 1000000) {

            final int  threshold = 1000;
            final int[] arr = new int[1000];  //array for memoization


            /* Populate the array */
            for (int j=2 ; j< threshold; j++) {
                PlatinumBlock pb = new PlatinumBlock(j);
                arr[j] = (int) pb.maxExchng();
            }

            /* create a memoized block & find max currency */
            final MemoizedPlatinumBlock pBlock =
                    new MemoizedPlatinumBlock(initValue, arr, threshold);
            System.out.println(pBlock.maxExchng());
        }
        else {

            /* create a block & find max currency */
            PlatinumBlock pBlock = new PlatinumBlock(initValue);
            System.out.println(pBlock.maxExchng());
        }

    }
}
