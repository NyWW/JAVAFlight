/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Wongsinsanity
 */
public class EnterBooking {
    private static Connection con = startConnection.dbCon();
    private static PreparedStatement myState = null;
       public static void insertEntry(String name, String flight, Date date){   
        try {
            //insert into booking table
              myState = con.prepareStatement("INSERT into BOOKING values(?,?,?)"); 
              myState.setString(1, flight);
              myState.setDate(2, date);
              myState.setString(3, name);
              myState.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
