package searching;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fundamentals.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Value getValue(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        int mid = lo + (hi - lo) / 2;
        while (lo <= hi) {
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0)
                lo = mid + 1;
            else if (cmp < 0)
                hi = mid - 1;
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        } // 如果键存在就更新值，否则把所有较大的键向后移动一格再插入新值

        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;// 当心漏掉N的自增1
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }

        return q;
    }

    public boolean contains(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public void delete(Key key) {

    }

}
