package polymorphism;

public class Ex17 {
    public static void main(String[] args) {
        Cycle[] c = new Cycle[] { new Unicycle(), new Bicycle(), new Tricycle() };
        c[0].balance();
        c[1].balance();
        c[2].balance();
        // 上下没区别
        ((Unicycle)c[0]).balance();
        ((Bicycle)c[1]).balance();
        ((Tricycle)c[2]).balance();// Tri-不覆盖的话，还是调从父类继承来的balance()

    }
}

class Cycle {
    void balance() {
        System.out.println("cycle's blance");
    }
}

class Unicycle extends Cycle {
    void balance() {
        System.out.println("unicycle blance");
    }
}

class Bicycle extends Cycle {
    void balance() {
        System.out.println("bicycle blance");
    }
}

class Tricycle extends Cycle {

}