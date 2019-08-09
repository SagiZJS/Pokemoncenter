package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    private static final Connection CONNECTION;
    
    static {
        try {
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost/pokemoncenter", "root", "Sagi");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("connection fail", e);
        }
        
    }
    
    public static Connection getConnection() {
        return CONNECTION;
    }
    public static void shudown() {
        try {
            CONNECTION.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
