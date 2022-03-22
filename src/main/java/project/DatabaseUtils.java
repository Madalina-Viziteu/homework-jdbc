package project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

    static Connection connection;

    public static Connection getConnection() {
        try {

            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_resources",
                        "root", "admin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
