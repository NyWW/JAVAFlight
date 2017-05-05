/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Wongsinsanity
 */
public class Cancel {
    private static Connection canCon;
    private static PreparedStatement flightCancel = null;
    private static ArrayList<String> nextonWait1 = new ArrayList<>();
    private static ArrayList<Date> nextonWait2 = new ArrayList<>();
    private static ArrayList<String> nextonWait3 = new ArrayList<>();
    private static ArrayList<String> noneCancel1 = new ArrayList<>();
    private static ArrayList<String> noneCancel2 = new ArrayList<>();
    
    public static void Cancel(String name, Date bookedDate, String flightName){
        canCon = startConnection.dbCon();

        try
        {
            flightCancel = canCon.prepareStatement("SELECT * FROM JAVA.BOOKING WHERE FLIGHTNAME = ? AND DATE = ? AND NAME = ?");
            flightCancel.setString(1, flightName);
            flightCancel.setDate(2, bookedDate);
            flightCancel.setString(3, name);
            ResultSet rs = flightCancel.executeQuery();
            if (rs.next()) {
                noneCancel1.add(rs.getString(3));
            } 
            
            
            flightCancel = canCon.prepareStatement("DELETE FROM JAVA.BOOKING WHERE FLIGHTNAME = ? AND DATE = ? AND NAME = ?");
            flightCancel.setString(1, flightName);
            flightCancel.setDate(2, bookedDate);
            flightCancel.setString(3, name);
            flightCancel.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        try
        {
            flightCancel = canCon.prepareStatement("SELECT * FROM JAVA.WAITLIST WHERE FLIGHT = ? AND DATE = ? AND NAME = ?");
            flightCancel.setString(1, flightName);
            flightCancel.setDate(2, bookedDate);
            flightCancel.setString(3, name);
            ResultSet rs = flightCancel.executeQuery();
            if (rs.next()) {
                noneCancel2.add(rs.getString(3));
            } 
            
            
            flightCancel = canCon.prepareStatement("DELETE FROM JAVA.WAITLIST WHERE FLIGHT = ? AND DATE = ? AND NAME = ?");
            flightCancel.setString(1, flightName);
            flightCancel.setDate(2, bookedDate);
            flightCancel.setString(3, name);
            flightCancel.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        try
        {
           //flightCancel = canCon.prepareStatement("SELECT * FROM JAVA.BOOKING WHERE FLIGHTNAME = ? AND DATE = ? AND NAME = ?");
            flightCancel = canCon.prepareStatement("SELECT * FROM JAVA.WAITLIST WHERE FLIGHT = ? AND DATE = ? ORDER BY timestamp ASC");
            flightCancel.setString(1, flightName);
            flightCancel.setDate(2, bookedDate);
            //flightCancel.setString(3, name);
            ResultSet rs = flightCancel.executeQuery();
            if (rs.next()) {
                nextonWait1.add(rs.getString(1));
                nextonWait2.add(rs.getDate(2));
                nextonWait3.add(rs.getString(3));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
        
        
        if(noneCancel1.isEmpty() && noneCancel2.isEmpty())
        {
            GUIClass.updateBookingText("No One Found From The Info Provided.");
        }
        else if(noneCancel2.isEmpty())
        {
            GUIClass.updateBookingText(name + ", your flight " + flightName + " on the date of " + bookedDate + " has been cancelled in the booked list. \n");
            if(!nextonWait1.isEmpty())
            {
                try
                {
                    flightCancel = canCon.prepareStatement("DELETE FROM JAVA.WAITLIST WHERE FLIGHT = ? AND DATE = ? AND NAME = ?");
                    flightCancel.setString(1, nextonWait1.get(0));
                    flightCancel.setDate(2, nextonWait2.get(0));
                    flightCancel.setString(3, nextonWait3.get(0));
                    flightCancel.executeUpdate();
                    GUIClass.updateBookingText("Moving " + nextonWait3.get(0) + " from the waitlist.....\n");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Booking.Booking(nextonWait3.get(0), nextonWait1.get(0), nextonWait2.get(0),0);
            }

        }
        else if(noneCancel1.isEmpty())
        {
            GUIClass.updateBookingText(name + ", your flight " + flightName + " on the date of " + bookedDate + " has been cancelled in the wait list.");
        }
        else
        {
            GUIClass.updateBookingText(name + ", your flight " + flightName + " on the date of " + bookedDate + " has been cancelled in the booked list. \n");
            GUIClass.updateBookingText(name + ", your flight " + flightName + " on the date of " + bookedDate + " has been cancelled in the wait list.");
            
            if(!nextonWait1.isEmpty())
            {
                try
                {
                    flightCancel = canCon.prepareStatement("DELETE FROM JAVA.WAITLIST WHERE FLIGHT = ? AND DATE = ? AND NAME = ?");
                    flightCancel.setString(1, nextonWait1.get(0));
                    flightCancel.setDate(2, nextonWait2.get(0));
                    flightCancel.setString(3, nextonWait3.get(0));
                    flightCancel.executeUpdate();
                    GUIClass.updateBookingText("\nMoving " + nextonWait3.get(0) + " from the waitlist.....\n");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Booking.Booking(nextonWait3.get(0), nextonWait1.get(0), nextonWait2.get(0),0);
            }
        }
        nextonWait1.clear();
        nextonWait2.clear();
        nextonWait3.clear();
        noneCancel1.clear();
        noneCancel2.clear();
    } 
}

