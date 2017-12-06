package innerclasses;

interface Ex11I {
}

public class Ex11 {

    public static void main(String[] args) {
        Another e11 = new Another();

        Ex11I e = e11.getEx11I();
        // Another.Ex11IImpl ae = (Another.Ex11IImpl) e;
        // 因为是private的内部类，所以这里无法向下转型
    }
}

class Another {
    private class Ex11IImpl implements Ex11I {
    }

    Ex11I getEx11I() {
        return new Ex11IImpl();
    }
}
