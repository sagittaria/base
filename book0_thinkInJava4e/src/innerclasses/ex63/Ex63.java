package innerclasses.ex63;

import innerclasses.ex61.Ex61;
import innerclasses.ex62.Ex62;

public class Ex63 extends Ex62 {

    private Ex61 getEx62In() {
        return new Ex62In();
    }

    public static void main(String[] args) {
        Ex63 e = new Ex63();
        Ex61 e1 = e.getEx62In();
        e1.hi();
    }
}
