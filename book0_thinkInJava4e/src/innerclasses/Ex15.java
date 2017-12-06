package innerclasses;

class Ex15Pre {
    Ex15Pre(int i) {
        System.out.println(i);
    }
}

public class Ex15 {
    Ex15Pre getEx15Pre() {
        return new Ex15Pre(5) {
            // 没有默认构造器，只有带参数的，则必须传参
        };
    }
}
