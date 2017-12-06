package innerclasses;

public interface Ex20 {
    void how();

    class Ex20In implements Ex20 {
        // 自动成为“静态”内部类，或叫嵌套类

        private static Ex20 e20 = new Ex20Impl();
        // 接口嵌套类里面的必须都是static的

        @Override
        public void how() {
            System.out.println("how?");
        }

        public static void main(String[] args) {
            new Ex20In().how();
        }

        static void doHow() {
            e20.how();
        }
    }
}

class Ex20Impl implements Ex20 {

    @Override
    public void how() {
        System.out.println("Impl");
    }

}
