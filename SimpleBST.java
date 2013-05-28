/**
 * Created with IntelliJ IDEA.
 * User: dare
 * Date: 3/8/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public final class SimpleBST<Key extends Comparable<Key>,Value> {

    final private class Node {
        private Node left_;
        private Node right_;
        private Key key_;
        private Value val_;

        Node(Key key, Value val, Node left, Node right){
            right_ = right;
            left_  = left;
            val_   = val;
            key_   = key;
        }

    }

    Node root_;
    public Key min() {
        return min(root_).key_;
    }
    private Node min(Node x) {
        if (x.left_ == null)  { return x;            }
        else                  { return min(x.left_); }
    }
    public void deleteMin() {
        root_ = deleteMin(root_);
    }
    private Node deleteMin(Node x) {
        if (x.left_ == null) { return x.right_; }
        x.left_ = deleteMin(x.left_);
        return x;
    }
    public void delete(Key k){
        root_ = delete(root_,k);
    }
    public Node delete(Node x,Key k) {
        if (x == null)         { return null;                    }
        int cmp = k.compareTo(x.key_);
        if      (cmp > 0)      { x.right_ = delete(x.right_, k); }
        else if (cmp < 0)      { x.left_ = delete(x.left_, k);   }
        else {
            if (x.right_ == null) { return x.left_;  }
            if (x.left_ == null)  { return x.right_; }
            Node t = x;
            x = min(x.right_);
            x.right_ = deleteMin(t.right_);
            x.left_ = t.left_ ;
        }
        return x;
    }

    public void put(Key key, Value val) {
        root_ = put(root_, key, val);
    }
    private Node put(Node x, Key key, Value v) {
        if (x == null)    { return new Node(key, v, null, null); }
        int cmp = (key.compareTo(x.key_));
        if      (cmp > 0) { x.right_ = put(x.right_, key, v);    }
        else if (cmp < 0) { x.left_  = put(x.left_, key, v);     }
        else              { x.val_   = v;                        }
        return x;
    }

    public Value get(Key key) {
        return get(root_, key);
    }
    private Value get(Node x, Key key) {
        if (x == null)       { return null;               }
        int cmp = key.compareTo(x.key_);
        if      (cmp < 0)    { return get(x.left_, key);  }
        else if (cmp > 0)    { return get(x.right_, key); }
        return x.val_;
    }


    public static void main(String[] args) {
        SimpleBST<Integer,String> sym = new SimpleBST<Integer,String>();
        for (int i = 0; i < 100; i++) {
            sym.put(i, "val " + i);
        }

        for (int i = 0; i < 100; i++) {
            sym.delete(i);
        }
        int k=0;
//        System.out.println("Min: " + sym.min());

    }
}
