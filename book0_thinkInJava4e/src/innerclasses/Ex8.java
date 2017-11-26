package innerclasses;

public class Ex8 {

    public static void main(String[] args) {
        Ex8 e = new Ex8();
        Ex8.InnerClass ic = e.new InnerClass();
        System.out.println(ic.i);// 外部类可以通过内部类实例访问内部类的私有成员

        Normal nm = new Normal();
        // System.out.println(nm.n); // 但 The field Normal.n is not visible
    }

    // http://blog.csdn.net/u011579908/article/details/68943565
    // Java规范里确实规定了外部类可以访问内部类的private/protected变量，就像访问自己的private/protected变量一样
    // 实际上，编译器实现的时候是这样的： Outer类和Inner类不再是嵌套结构，而是变为一个包中的两个类，
    // 然后，对于private变量的访问，编译器会生成一个accessor函数...

    class InnerClass {
        private int i = 56;
    }
}

class Normal {
    private int n = 32;
}
