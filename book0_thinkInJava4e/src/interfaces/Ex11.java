package interfaces;

public class Ex11 {
    public static void main(String[] args) {
        Apply.apply(new CutterAdapter(new Cutter(5)), "Why are you here?");
    }
}

class Apply {
    static void apply(Processor p, Object s) {
        System.out.println("using: " + p.name());
        System.out.println(p.process(s));
    }
}

interface Processor {
    String name();

    Object process(Object input);
}

class Cutter {
    int topN;

    Cutter(int topN) {
        this.topN = topN;
    }

    String topNOfStr(String str) {
        return str.substring(0, topN);
    }
}

class CutterAdapter implements Processor {
    String name = "CutterAdapter";
    Cutter c;

    CutterAdapter(Cutter c) {
        this.c = c;
    }

    public String name() {
        return name;
    }

    public String process(Object str) {
        return c.topNOfStr((String) str);
    }

}