/**
 * Block class that uses array for optimizing and reducing object
 * creation.
 */
final public  class MemoizedPlatinumBlock extends PlatinumBlock {

    /* Array reference for memoization */
    private final int[] msArr_;
    /* threshold for searching within the HashMap */
    private final int threshold_;

    /**
     * note: the array needs to be pre-populated !!
     * @param weight    weight of the block
     * @param msArr     Array used for memoization
     * @param threshold the threshold to search within the HashMap
     */

    MemoizedPlatinumBlock(final int weight, int[] msArr, final int threshold) {
        super(weight);
        msArr_ = msArr;
        threshold_ = threshold;

    }

    /**
     * Get the max possible currency
     * @return max currency
     */
    final public long maxExchng() {
        /*  if weight is less than threshold, look up value in array,
            else calculate value
         */
        if(weight_ < threshold_) { return msArr_[weight_]; }
        else {
            if (weight_ > 11 )   { return Math.max(this.exchngCurrency(), this.exchngBlocks()); }
            else                 { return this.exchngCurrency(); }
        }
    }

    /**
     * Exchange block for sub blocks
     * @return currency
     */
    final protected long exchngBlocks() {
        final long quarter =  new MemoizedPlatinumBlock((weight_/4), msArr_, threshold_).maxExchng();
        final long third   =  new MemoizedPlatinumBlock((weight_/3), msArr_, threshold_).maxExchng();
        final long half    =  new MemoizedPlatinumBlock((weight_/2), msArr_, threshold_).maxExchng();
        return (quarter + third + half);
    }
}