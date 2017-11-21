package polymorphism;

public class Shape {
  public void what(){
      System.out.println("shape");
  }
  
  public void printName(){
      String n = getName();
      System.out.println(n);
  }
  
  public String getName(){
      return "shape-name";
  }
}
