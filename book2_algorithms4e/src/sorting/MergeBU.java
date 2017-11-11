package sorting;

public class MergeBU extends Sort {
    // 自底向上归并
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                System.out.println("merge: "+lo + " " 
                        + (lo + sz - 1) + " " 
                        + Math.min(lo + sz + sz - 1, N - 1));
                System.out.println("0 1 2 3 4 5 6 7 8 9 a b c d e f g");
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                show(a);
            }
        }
    }
    
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
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
            else if (less(aux[j], aux[i]))
                //不要写成else if (less(a[j], a[i]))，参与比较的应该是aux而非a！
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    
    public static void main(String[] args){
        //String[] a = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        String[] a = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E","B"};
        //String[] a = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E","F","B"};
        //String[] a = {"O","S","R","E"};
        //String[] a = {"S","R"};
        show(a);
        System.out.println("------------");
        sort(a);
//        String c1="R";
//        String c2="S";
//        System.out.println(c1.compareTo(c2));
    }
}
