package access.ex8;

public class ConnectionManager {
    private static Connection[] cArray = new Connection[2];

    public static Connection getNewConnection() {
        return Connection.generateConnection();
    }

    public static void main(String[] args) {
        // Connection cElement=new Connection(); // won't do.
        // cArray[0]=new Connection(); // won't do.
        cArray[0] = getNewConnection();
    }
}
