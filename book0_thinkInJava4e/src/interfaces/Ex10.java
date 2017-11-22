package interfaces;

public class Ex10 {
    public static void main(String[] args) {
        Violin v = new Violin();
        System.out.println("this is: " + v.what());
        tune(v);
    }

    static void tune(Playable p) {
        p.play();
    }
}

interface Playable {
    void play();
}

abstract class Instrument {
    protected String name;

    String what() {
        return name;
    };
}

class Violin extends Instrument implements Playable {
    Violin() {
        name = "violin";
    }

    public void play() {
        System.out.println("violin playing...");
    }
}