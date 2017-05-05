/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.sql.*;

/**
 *
 * @author Wongsinsanity
 */
public class startConnection {
    static Connection finalCon = null;
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/FlightSchedulerDBZeyuWangzmw5111";
 
    //make one connection
public static Connection dbCon() {
//make sure its the first time connection is made
        if (finalCon == null)
        {    
        try {
            finalCon = DriverManager.getConnection(DATABASE_URL, getUsername(), getPassword());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
 
        return finalCon;
        
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}