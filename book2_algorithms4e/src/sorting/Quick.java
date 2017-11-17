package sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Quick extends Sort {
    
    public static void main(String[] args) {
//        String[] a = {"Q","U","I","C","K","S","O","R","T","E","X","A","M","P","L","E"};
        //String[] a = {"Q","U","I","C","K"};
        Double[] a = {3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        System.out.println("-------");
        sort(a);
        System.out.println("finally");
        show(a);
    }
    
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi)
                    break;
            }
            while (less(v, a[--j])) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        System.out.println("out:(lo,j): "+lo+","+j);
        show(a);
        return j;
    }

}
