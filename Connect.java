import java.sql.DriverManager;
import java.sql.Connection;

public class Connect {

    private static Connection conn;

        public static Connection connect()
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=root");

                return conn;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }





