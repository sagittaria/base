package interfaces;

interface Service {
    void method1();

    void method2();
}

interface ServiceFactory {
    Service getService();
}

class Impl1 implements Service {
    Impl1() {
    }

    public void method1() {
        System.out.println("Impl1 Method1");
    }

    public void method2() {
        System.out.println("Impl1 Method2");
    }
}

class Impl1Fac implements ServiceFactory {
    public Service getService() {
        return new Impl1();
    }
}

public class Factories {
    public static void main(String[] args) {
        Service s = new Impl1Fac().getService();
        s.method1();
        s.method2();
    }
}
