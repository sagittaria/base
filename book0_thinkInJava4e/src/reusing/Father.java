package reusing;

public class Father {
    private int v=50;
    
    protected int value(){
        return v;
    }
    
    public static void main(String[] args) {
        Son s = new Son();
        System.out.println(s.value());
        System.out.println(s.fvalue());
        // System.out.println(s.super.fvalue()); //不能直接在实例方法外，通过点号引用父类对象
    }
}

class Son extends Father{
    
    private int u = 25;
    @Override
    public int value(){
        return u;
    }
    
    public int fvalue(){
        return super.value();
    }
}