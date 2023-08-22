package connection;

import java.sql.*;

public class ConnectDB {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/shop_online?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "SaintRaiYugi1998";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);           
        } catch (Exception e) {}
    return null;
    }
    
    /*
    public static void main(String[] args) {
        try {
            System.out.println(new ConnectDB().getConnection());
        } catch (Exception e) {
        }
    }
    */
}
