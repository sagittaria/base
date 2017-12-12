package searching;

//public class ST<Key,Value> {
public class ST<Key extends Comparable<Key>, Value> {
//    private int size;
//
//    ST() {
//    }
//
//    void put(Key key, Value val) {
//    }
//
//    Value get(Key key) {
//    }
//
//    void delete(Key key) {
//        put(key, null);
//    }
//
//    boolean contains(Key key) {
//        return get(key) != null;
//    }
//
//    boolean isEmpty() {
//        return size == 0;
//    }
//
//    int size() {
//        return size;
//    }
//
//    Key min() {
//    }
//
//    Key max() {
//    }
//
//    Key floor(Key key) {
//    }
//
//    Key ceiling(Key key) {
//    }
//
//    int rank(Key key) {
//    }
//
//    Key select(int k) {
//    }
//
//    void deleteMin() {
//        delete(min());
//    }
//
//    void deleteMax() {
//        delete(max());
//    }
//
//    int size(Key lo, Key hi) {
//        if (hi.compareTo(lo) < 0)
//            return 0;
//        else if (contains(hi))
//            return rank(hi) - rank(lo) + 1;
//        else
//            return rank(hi) - rank(lo); // 这行算什么意思？？
//    }
//
//    Iterable<Key> keys() {
//        return keys(min(), max());
//    }
//
//    Iterable<Key> keys(Key lo, Key hi) {
//    }
}
