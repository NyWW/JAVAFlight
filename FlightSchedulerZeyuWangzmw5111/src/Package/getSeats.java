/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Wongsinsanity
 */
public class getSeats {
    static int totalSeats;
    static int bookedSeats;
    private static Connection FlightCon;
    private static PreparedStatement FlightStat= null;
    
    public static int getAllSeats(String fName)
    {
     FlightCon = startConnection.dbCon();
        try {
            FlightStat = FlightCon.prepareStatement("SELECT seats FROM Java.Flight WHERE flightname = ?");
            FlightStat.setString(1, fName);
            ResultSet rs = FlightStat.executeQuery();
            rs.next();
            totalSeats = rs.getInt(1);
        } 
        catch (SQLException ex) {
                ex.printStackTrace();
        }
     return totalSeats;
    }
   
    // get number of booked seats by flight and date and return it
     public static int getBookedSeats(String fName, Date dated)
    {
     FlightCon = startConnection.dbCon();
        try {
            FlightStat = FlightCon.prepareStatement("SELECT Count(flightname) From Java.booking WHERE flightname = ? AND date = ?");
            FlightStat.setString(1, fName);
            FlightStat.setDate(2, dated);
            ResultSet rs = FlightStat.executeQuery();
            rs.next();
            bookedSeats = rs.getInt(1);
        } 
        catch (SQLException ex) {
                ex.printStackTrace();
        }
     return bookedSeats;
    }
    
}
