package innerclasses;

public class Ex18 {
    static class Ex18In {
        // 嵌套类
    }

    public static void main(String[] args){
        Ex18In e18in = new Ex18In();
        // 不必像普通内部类那样，Outer.Inner i = OuterInstance.new Inner();
    }
    
}
