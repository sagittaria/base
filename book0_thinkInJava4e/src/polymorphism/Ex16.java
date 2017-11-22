package polymorphism;

public class Ex16 {
    public static void main(String[] args) {
        StarShip ss = new StarShip();
        ss.gogogo();
        ss.change();
        ss.gogogo();
    }
}

class StarShip {
    private Status status = new FullSpeed();

    void change() {
        status = new Combat();
    }

    void gogogo() {
        status.act();
    }
}

class Status {
    void act() {
    };

    protected String status;
}

class FullSpeed extends Status {
    FullSpeed() {
        this.status = "full speed";
    }

    void act() {
        System.out.println("now:" + status);
    }
}

class Combat extends Status {
    Combat() {
        this.status = "get into combat";
    }

    void act() {
        System.out.println("now:" + status);
    }
}