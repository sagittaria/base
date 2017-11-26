package ex6.p3;

import ex6.p1.Cycle;
import ex6.p2.P2Class;

public class P3Class extends P2Class{
    Cycle getCycle(){
        return this.new Unicycle(); // P3Class继承了P2Class的内部类Unicycle
    }
}
