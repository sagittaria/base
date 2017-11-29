package innerclasses;

public class Factories {
    public static void serviceConsumer(ServiceFactory sf) {
        Service s = sf.getService();
        s.method();
    }

    public static void main(String[] args) {
        serviceConsumer(Impl1.sf);
        serviceConsumer(Impl2.sf);
    }
}

interface Service {
    void method();
}

interface ServiceFactory {
    Service getService();
}

// 根据Impl1和Impl2的实现，不需要再分别为它们做具体的工厂类

class Impl1 implements Service {
    public void method() {
        System.out.println("Impl1.method");
    }

    public static ServiceFactory sf = new ServiceFactory() {
        @Override // 这匿名类算是ServiceFactory的子类，所以要实现getService
        public Service getService() {
            return new Impl1();
        }
    };
}

class Impl2 implements Service {
    public void method() {
        System.out.println("Impl2.method");
    }

    public static ServiceFactory sf = new ServiceFactory() {
        @Override 
        public Service getService() {
            return new Impl2();
        }
    };
}