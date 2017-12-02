package innerclasses;

interface Counter{
    int next();
}

public class LocalInnerClass {
    Counter getCounter(){
        class LocalCounter implements Counter{
            public int next(){
                return 0;
            }
        }
        return new LocalCounter();
    }
    
    Counter getCounter2(){
        return new Counter(){
          public int next(){
              return 1;
          }
        };
    }
}
