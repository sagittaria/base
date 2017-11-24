package interfaces;

public class Ex18 {
    public static void main(String[] args) {
        Cycle u = new UnicycleFactory().getCycle();
        u.roll();
        Cycle b = new BicycleFactory().getCycle();
        b.roll();
    }
}

interface Cycle {
    void roll();
}

interface CycleFactory {
    Cycle getCycle();
}

class Unicycle implements Cycle {
    public void roll() {
        System.out.println("Unicycle rolling");
    }
}

class Bicycle implements Cycle {
    public void roll() {
        System.out.println("Bicycle riding");
    }
}

class UnicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Unicycle();
    }
}

class BicycleFactory implements CycleFactory {
    public Cycle getCycle() {
        return new Bicycle();
    }
}
