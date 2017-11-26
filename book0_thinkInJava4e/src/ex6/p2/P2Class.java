package ex6.p2;

import ex6.p1.Cycle;


public class P2Class {
    protected class Unicycle implements Cycle{

        public Unicycle() {
        }

        @Override
        public void roll() {
            System.out.println("unicycle rolling!");
        }
        
    }
}
