import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/movie_booking";
    private static final String USER = "root";
    private static final String PASS = "Rakchana@2008"; 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}