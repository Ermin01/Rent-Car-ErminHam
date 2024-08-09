package com.example.rentcarerkos;

import java.sql.*;
public class Database {


    public static Connection connectDb(){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/rentcarbeginer", "root" , "");
            return connect;

        }catch (ClassNotFoundException e){
            System.err.println(" System.err.println(\"MySQL JDBC driver not found. Make sure it's included in the classpath.");
                    e.printStackTrace();
        }catch (SQLException e){
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return null;
    }
}
