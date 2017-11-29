package innerclasses;

public class Parcel8 {
    int f(){
        return 1;
    }
    
    // void f(){} // 不能只靠返回类型区分重载的方法
    
    public Contents contents(int x) {
        return new Contents(x) {
            public int value() {
                return super.value() + x;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Contents c = p.contents(10);
        System.out.println(c.value());// (10+1)+10
        // 构造时contents(10)把父类的i改成10，
        // 再调经过子类覆盖的c.value时取父类的i+1与x加和
    }
}
