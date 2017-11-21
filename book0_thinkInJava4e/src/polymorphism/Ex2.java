package polymorphism;

public class Ex2 {
    public static void main(String[] args) {
        Shape s1=new Circle();
        Shape s2=new Triangle();
        // 子类未覆盖，打印“shape”和“shape”
        s1.what(); s2.what();
        // Circle自己覆盖what()之后，打印“circle”、“shape”

    }
}
