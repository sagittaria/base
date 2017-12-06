package innerclasses;

public class Ex7 {
    private int i = 2;

    private int f() {
        return i;
    }

    class Ex7InnerClass {
        void behave() {
            i = 2 * f();
        }
    }

    public static void main(String[] args) {
        Ex7 e = new Ex7();
        Ex7InnerClass ic = e.new Ex7InnerClass();
        ic.behave();
        System.out.println(e.i);// 4
    }
}
