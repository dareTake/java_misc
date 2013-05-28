

/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 3/7/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int maxCap;
    int N;

    MinPQ(int capacity){
        maxCap = capacity;
        keys = (Key[]) new Comparable[capacity + 1];

    }
    private void sink(int k) {
        while((2 * k) <= N) {
            int j = 2 *k;
            if ((j < N) && less(j, j + 1)) { j++; }
            if (!less(k, j)) { break; }
            exchng(k, j);
            k = j;
       }
    }
    private void swim(int k) {
        while(k>1  && less(k / 2, k)) {
            exchng(k / 2, k);
            k = k / 2;
        }
    }
    public Key delMax() {
        if(N < 1) return null;
        Key max = keys[1];
        exchng(1, N--);
        keys[N + 1] = null;
        sink(1);
        return max;
    }
    public void insert(Key k) {
       if(N == maxCap) return;
        keys[++N] = k;
        swim(N);
    }

    private boolean less(int p, int q) {
        return keys[p].compareTo(keys[q]) > 0;
    }
    private void exchng(int p, int q) {
        Key tmp = keys[p];
        keys[p] = keys[q];
        keys[q] = tmp;

    }
    public static void main(String[] args) {

        MinPQ<Integer> mpq = new MinPQ<Integer>(100);
        for(int i = 0;i < 150; i++)   {
            mpq.insert(StdRandom.uniform(0,1000));
        }
        int k=0;
        for(int i = 0;i < 150; i++)
            System.out.println(mpq.delMax());
    }

}
