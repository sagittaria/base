package access.ex8;

class Connection {
    private Connection() {
        System.out.println("private constructor called.");
    }
    
    static Connection generateConnection(){
        return new Connection();
    }
}
