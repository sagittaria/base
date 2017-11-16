package homework.sorting;

public class Selection extends Sort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int min = i;
            for(int j=i+1;j<N;j++){
                if(less(a[j],a[min])){
                    min=j;
                }
            }
            exch(a,i,min);
        }
    }

    public static void main(String[] args) {
        Double[] a={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        sort(a);
        show(a);
        String[] b={"S","E","L","E","C","T","I","O","N","S","O","R","T","E","X","A","M","P","L","E"};
        show(b);
        sort(b);
        show(b);
    }
}