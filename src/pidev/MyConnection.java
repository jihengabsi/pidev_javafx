/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jihen
 */
public class MyConnection {
    final static String URL = "jdbc:mysql://localhost:3306/pidev";
    final static String username = "root";
    final static String password = "";
    private static MyConnection instance = null;
    private Connection cnx;
    
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection done");
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
    }
    public static MyConnection getInstance(){
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
}
