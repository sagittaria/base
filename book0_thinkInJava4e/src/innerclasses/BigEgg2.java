package innerclasses;

class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    private Yolk y = new Yolk();

    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void g() {
        y.f();
    }
}

public class BigEgg2 extends Egg2 {
    public static void main(String[] args) {
        BigEgg2 e2 = new BigEgg2();
        e2.g();
    }
}
