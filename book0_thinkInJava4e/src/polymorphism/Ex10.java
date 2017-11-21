package polymorphism;

public class Ex10 {
    public static void main(String[] args) {
        Shape t = new Triangle();
        t.printName();//基类Shape的printName方法中调用的getName
       // 是Triangle类中经过覆盖的版本，所以输出的是“triangle-name”
    }
}
