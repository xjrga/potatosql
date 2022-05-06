import java.sql.*;

public class Test {

    private Connection connection;

    public Test() {
        try {
            //connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/db01", "sa", "password");
            //connection = DriverManager.getConnection("jdbc:hsqldb:file:data/database/db;shutdowncompact=true", "sa", "password");
            //connection = DriverManager.getConnection("jdbc:hsqldb:mem:db", "sa", "password");
            //connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/", "admin", "password");
            if (connection.isValid(1)) {
                connection.setAutoCommit(false);
                //call insert, update, delete or select methods here
                connection.commit();
                //connection.rollback();
            } else {
                System.out.println("Connection not valid.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}

