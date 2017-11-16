package homework.sorting;

public class Insertion extends Sort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=1;i<N;i++){ // shell排序h=1的特例
            for(int j=i;j>0&&less(a[j],a[j-1]);j--){ // j>0其实是j>=1，确保“如果要挪位子，左边是有位子的”
                exch(a,j,j-1);
            }
        }
    }

    public static void main(String[] args) {
        Double[] a={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        sort(a);
        show(a);
        String[] b={"I","N","S","E","R","T","I","O","N","S","O","R","T","E","X","A","M","P","L","E"};
        show(b);
        sort(b);
        show(b);
    }
}