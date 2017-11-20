package sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Quick extends Sort {
    
    public static void main(String[] args) {
        String[] a = {"Q","U","I","C","K","S","O","R","T","E","X","A","M","P","L","E"};
        //String[] a = {"Q","U","I","C","K"};
//        Double[] a = {3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        System.out.println("-------");
        sort(a);
        System.out.println("finally");
        show(a);
    }
    
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        //快速排序是偏好随机性的，先随机重排一次以避免碰到最坏情况
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo+10 >= hi){
            Insertion.sort(a,lo,hi);//子数组长度小于10的，切换用插入排序
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j + 1, hi);
    }
    
    public static void sort3way(Comparable[] a){
        StdRandom.shuffle(a);
        sort3way(a, 0, a.length - 1);
    }
    
    private static void sort3way(Comparable[] a, int lo, int hi){
        // 适用于有大量重复元素的三向切分【自己还没逐步推演过】
        if(lo>=hi) return;
        int lt=lo, gt=hi, i=lo+1;
        Comparable v = a[lo];
        while(i<=gt){
            int cmp = a[i].compareTo(v);
            if(cmp<0) exch(a,lt++,i++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }
        // 此时 a[lo,...,lt-1] < v = a[lt,...,gt] < a[gt+1,...,hi]
        sort3way(a,lo,lt-1);
        sort3way(a,gt+1,hi);
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
