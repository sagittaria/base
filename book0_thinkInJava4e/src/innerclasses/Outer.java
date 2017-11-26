package innerclasses;

public class Outer {

    public Inner getInner(){
        return new Inner();
    }
    
    public static void main(String[] args){
        Outer o=new Outer();
        Outer.Inner i = o.getInner();
    }
    
    class Inner{
        
    }
}
