package homework.sorting;

public class Shell extends Sort {
    public static void sort(Comparable[] a){
        int N = a.length;

        int h=1;
        while(h<N/3)
            h=3*h+1;

        while(h>0){
            for(int i=h;i<N;i++){ // i=h
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){ // j>=h
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }

    public static void main(String[] args) {
        Double[] a={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        sort(a);
        show(a);
        String[] b={"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
        show(b);
        sort(b);
        show(b);
    }
}