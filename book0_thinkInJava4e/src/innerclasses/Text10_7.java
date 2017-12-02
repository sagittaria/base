package innerclasses;

public class Text10_7 {

    class InnerClass {
        int i;
        // 不能声明成静态的：static double d;
        // The field d cannot be declared static in a non-static inner type,
        // unless initialized with a constant expression
    }

    static class StaticInnerClass {
        int j;
        static double k;
    }
}
