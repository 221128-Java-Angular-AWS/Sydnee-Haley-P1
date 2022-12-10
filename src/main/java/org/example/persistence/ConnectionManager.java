package org.example.persistence;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionManager {
    public static Connection connection;

    public ConnectionManager(){

    }

    public static void getConnection() {
        if (connection == null) {
            connect();
        }
    }

    private static void connect() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            Properties props = new Properties();
            props.load(input);

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(props.getProperty("connection_string"));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    if(connection != null){
    System.out.println("Connected successfully");
}
    }

    public static void main(String[] args){
        ConnectionManager.getConnection();
    }


}