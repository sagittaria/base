package sorting;

public class Merge extends Sort {
    
    private static double[] aux;

    public static void sort(double[] a) {
        aux = new double[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(double[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(double[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        } // 保存原数组

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(a[j], a[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    } // 默写
    
    /*
    public static void sort(double[] a) {
        aux = new double[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(double[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    
    public static void merge(double[] a, int lo, int mid, int hi) {
        // 归并a[lo,...,mid]和a[mid+1,...,hi]
        
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        } // 存放原数组

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) // 左半边用完了取右边的元素
                a[k] = aux[j++];
            else if (j > hi) // 右半边用完了取左边的元素
                a[k] = aux[i++];
            else
                if (less(a[j], a[i])) // 两边都没用完，取小的元素
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
        }
    }
    */
}
