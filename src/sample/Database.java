package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    //method for connection
    public Connection Connect() {
        try {
            String url = "jdbc:mysql://localhost:3308/workingdiary";
            String user = "root";
            String password = "1234";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
