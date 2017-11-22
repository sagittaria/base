package interfaces;

public class Ex4 {
    public static void main(String[] args) {
//1        //如果Ex4Base里为空，那么要向下转型才可用print方法
//1        Ex4Base e4b = new Ex4Derived();
//1        Ex4Derived e4d = Ex4Derived.cast(e4b);
//1        e4d.print();
        
        //2 可以略去向下转型这步，直接：
        Ex4Base e4b = new Ex4Derived();
        e4b.print();
    }
}

abstract class Ex4Base {
    //2 如果加上abstract print
    abstract void print();
}

class Ex4Derived extends Ex4Base {
    
    static Ex4Derived cast(Ex4Base e4b){
        return ((Ex4Derived) e4b);
    }
    
    void print(){
        System.out.println("Ex4Derived");
    }
}