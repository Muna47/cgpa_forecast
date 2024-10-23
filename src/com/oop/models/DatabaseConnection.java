package com.oop.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/cgpa";
    private static final String username = "root";
    private static final String password = "mysql";

    public static Connection getconnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}