package innerclasses;

interface Ex9I{
    void hi();
}

public class Ex9 {
 Ex9I getEx9I(){
     class Ex9IImpl implements Ex9I{
         public void hi(){
             System.out.println("hi");
         }
     }
     return new Ex9IImpl();
 }
}
