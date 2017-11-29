package innerclasses;

public class Parcel7 {
    public Contents contents() {
        return new Contents() {
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
            
            public int superValue(){
                return super.value(); // super请在实例方法里用...
            }
        };// 这个是[return语句结束]的分号，不能省略
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value()); // 显示11
        // 作为Content的匿名子类，调的是经过覆盖的 return i
        
        //System.out.println(c.superValue()); // c作为Contents类，是没有superValue()方法的
    }
}

class Contents {
    private int i = 5;

    public Contents() {
    }

    public Contents(int x) {
        i = x;
    }

    public int value() {
        return i + 1;
    }
}
