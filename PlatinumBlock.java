/**
 * Normal block class  that implements the needed API
 */
public class PlatinumBlock {

    /* Weight of the block */
    protected final int weight_;

    /**
     * Creates a new PlatinumBlock
     * @param weight weight of the block
     */
    PlatinumBlock(int weight) {
        weight_ = weight;
    }

    /**
     * Exchange the block for equivalent currency
     * @return currency
     */
    final protected int exchngCurrency() {
        return weight_;
    }

    /**
     * Get the max possible currency
     * @return max currency
     */
    public long maxExchng() {
        /* all values > 2 && < 12 have same value */
        if (weight_ > 11 )  { return Math.max(this.exchngCurrency(), this.exchngBlocks()); }
        else                { return this.exchngCurrency(); }
    }

    /**
     * Exchange block for sub blocks
     * @return currency
     */
    protected long exchngBlocks() {
        final long quarter =  new PlatinumBlock((weight_/4)).maxExchng();
        final long third   =  new PlatinumBlock((weight_/3)).maxExchng();
        final long half    =  new PlatinumBlock((weight_/2)).maxExchng();
        return (quarter + third + half);
    }

}