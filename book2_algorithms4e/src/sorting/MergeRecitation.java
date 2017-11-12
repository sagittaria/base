package sorting;

public class MergeRecitation{
    // 默写作业
    private static Comparable[] aux;

    public static void main(String[] args) {
        String[] a = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        show(a);
        //sortTD(a);
        sortBU(a);
        show(a);
    }

    public static void sortTD(Comparable[] a){
        aux=new Comparable[a.length];
        sortTD(a,0,a.length-1);
    }

    private static void sortTD(Comparable[] a, int lo, int hi){
        if(lo>=hi) return;
        int mid=lo+(hi-lo)/2;
        sortTD(a,lo,mid);
        sortTD(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    public static void sortBU(Comparable[] a){
        int N = a.length;
        aux=new Comparable[N];

        for(int sz=1;sz<N;sz=sz+sz){
            for(int lo=0;lo<N-sz;lo+=(sz+sz)){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi){
        for(int k=lo;k<=hi;k++){
            aux[k]=a[k];
        }

        int i=lo;
        int j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid)
                a[k]=aux[j++];
            else if(j>hi)
                a[k]=aux[i++];
            else if(less(aux[i],aux[j]))
                a[k]=aux[i++];
            else
                a[k]=aux[j++];
        }

    }

    public static boolean less(Comparable i, Comparable j){
        return i.compareTo(j)<0;
    }

    public static void show(Comparable[] a){
        for (int i=0; i<a.length; i++) {
            System.out.printf(a[i]+" ");
        }
        System.out.println();
    }
}