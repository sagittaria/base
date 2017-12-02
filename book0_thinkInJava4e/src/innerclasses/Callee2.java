package innerclasses;

interface Incrementable {
    void increment();
}

class MyIncrement {
    void increment() {
        System.out.println("from MyIncrement");
    }
}

// public class Callee2 extends MyIncrement implements Incrementable{}
// The inherited method MyIncrement.increment() cannot hide the public abstract method in Incrementable

// 继承来的方法和实现某接口带来的方法，不能同名。内部类救场

public class Callee2 extends MyIncrement {

    private class Closure implements Incrementable {
        @Override
        public void increment() {
            System.out.println("from Incrementable");
        }
    }

    public static void main(String[] args) {
        Callee2 c2 = new Callee2();
        Callee2.Closure c2c = c2.new Closure();
        System.out.println("先调的是继承来的increment方法");
        c2.increment();
        System.out.println("再调接口期望的increment方法");
        c2c.increment();
    }
}
