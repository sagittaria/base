package homework.sorting;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;// 必须先+1，扩出位置给新增来的末位元素
        swim(N);
    }

    public Key delMax() {
        Key t = pq[1];
        exch(1, N--);
        pq[N + 1] = null;// 上面已减，所以这行(为了指原先的末位元素)要加回去1
        sink(1);
        return t;

    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) { // 可以等于
            int j = 2 * k;
            if (j < N && less(j, j + 1)) // 不要漏掉j<N的判断！
                j++;
            if (!less(k, j)) // 这里不需要else，因为break相当于自带了else的目的
                break;
            exch(k, j);
            k = j;
        }

    }
}
