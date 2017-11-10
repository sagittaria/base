package sorting;

import edu.princeton.cs.algs4.In;

public class Insertion extends Sort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                // 因为j--之后可能为零，所以必须先判断(j>0)，否则可能越界（a[0-1]）
                exch(a, j, j - 1);
                // show(a);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        System.out.println("finally: ");
        show(a);
    }
}
