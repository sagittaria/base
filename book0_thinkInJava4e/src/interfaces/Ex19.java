package interfaces;

public class Ex19 {
    public static void main(String[] args) {
        Func t = new ThrowCoinFactory().getFunc();
        t.execute();
        Func r = new RollDiceFactory().getFunc();
        r.execute();
    }
}

interface Func {
    void execute();
}

interface Factory {
    Func getFunc();
}

class ThrowCoin implements Func {
    public void execute() {
        System.out.println("throw a coin");
    }
}

class RollDice implements Func {
    public void execute() {
        System.out.println("roll a dice");
    }
}

class ThrowCoinFactory implements Factory {
    public Func getFunc() {
        return new ThrowCoin();
    }
}

class RollDiceFactory implements Factory {
    public Func getFunc() {
        return new RollDice();
    }
}