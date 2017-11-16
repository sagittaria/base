package homework.sorting;

public class Merge extends Sort {

    public static Comparable[] aux;

    public static void sortTD(Comparable[] a){
        aux = new Comparable[a.length];
        sortTD(a,0,a.length-1);
    }

    private static void sortTD(Comparable[] a, int lo, int hi){
        if(lo>=hi){
            return; // 子数组长度为1时不用继续
        }
        int mid=lo+(hi-lo)/2;
        sortTD(a,lo,mid);
        sortTD(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    public static void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N]; 
        // 未免数组超过长度出错，每次调用public的sortBU(或TD)时重新初始化

        for(int sz=1;sz<N;sz+=sz){
            for(int j=0;j<N-sz;j+=(sz+sz)){
                merge(a,j,j+sz-1,Math.min(j+sz+sz-1,N-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi){
        // 归并两个【!各自有序!】的子数组a[lo,...,mid]和a[mid+1,...,hi]
        int i=lo;
        int j=mid+1;

        for(int k=lo;k<=hi;k++){
            aux[k]=a[k];
        }

        for(int k=lo;k<=hi;k++){
            if(i>mid){
                a[k]=aux[j++];
            }else if(j>hi){
                a[k]=aux[i++];
            }else if(less(aux[j],aux[i])){
                a[k]=aux[j++];
            }else{
                a[k]=aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Double[] a={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(a);
        sortTD(a);
        show(a);
        String[] b={"M","E","R","G","E","T","D","S","O","R","T","E","X","A","M","P","L","E"};
        show(b);
        sortTD(b);
        show(b);
        Double[] c={3.,5.,4.,7.,1.,6.,8.,2.,9.};
        show(c);
        sortBU(c);
        show(c);
        String[] d={"M","E","R","G","E","B","U","S","O","R","T","E","X","A","M","P","L","E"};
        show(d);
        sortBU(d);
        show(d);
    }
}