package innerclasses.ex62;

import innerclasses.ex61.Ex61;

public class Ex62 {
    protected class Ex62In implements Ex61 {

        public Ex62In() {// Ex63中要求显示写出这个构造器
        }

        @Override
        public void hi() {
            System.out.println("hello");
        }

    }
}
