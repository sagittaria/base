package innerclasses;

interface U {
    void f1();

    void f2();

    void f3();
}

class A {
    U af(String a) {
        return new U() {

            @Override
            public void f1() {
                System.out.println(a + " f1");
            }

            @Override
            public void f2() {
                System.out.println(a + " f2");
            }

            @Override
            public void f3() {
                System.out.println(a + " f3");
            }

        };
    }
}

class B {
    private U[] ua = new U[10];
    int size = 0;

    void add(U u) {
        ua[size++] = u;
    }

    void remove() {
        ua[--size] = null;
    }

    void iterate() {
        int i = size;
        while (--i >= 0) {
            ua[i].f1();
        }
    }

}

public class Ex23 { // 其实不是很懂题意。。
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();

        B b = new B();
        b.add(a1.af("a1"));
        b.add(a2.af("a2"));

        b.iterate();
        System.out.println("-----");

        b.remove();
        b.iterate();

        System.out.println("-----");
        b.add(a3.af("a3"));
        b.iterate();
    }
}
