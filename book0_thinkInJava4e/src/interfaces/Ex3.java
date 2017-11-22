package interfaces;

public class Ex3 {
    public static void main(String[] args) {
        Derived d = new Derived();
        d.print();
        Child c = new Child();
        c.print();
        /*
        print-method in Derived: 0
        print-method in Derived: 1
        print-method in Child: 0
        print-method in Child: 1
        */
    }
}

abstract class Base {
    abstract void print();

    Base() {
        print();
    }
}

class Derived extends Base {

    private int i = 1;

    @Override
    void print() {
        System.out.println("print-method in Derived: "+i);
    }
}

class Child extends Base {

    private int i = 1;

    @Override
    void print() {
        System.out.println("print-method in Child: "+i);
    }
}
