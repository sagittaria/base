package homework.sorting;

public class Sort{
    public static boolean less(Comparable m, Comparable n){
        return m.compareTo(n)<0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            System.out.printf(a[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Double[] a={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        System.out.println(less(a[2],a[5]));
        exch(a,0,1);
        show(a);
    }
}