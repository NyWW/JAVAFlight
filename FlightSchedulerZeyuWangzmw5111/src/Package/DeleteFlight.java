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
public class DeleteFlight {
    private static Connection dfCon;
    private static PreparedStatement flightDrop = null;
    public static ArrayList<String> droppedName = new ArrayList<>();
    public static ArrayList<Date> droppedDate = new ArrayList<>();
    public static ArrayList<String> droppedName1 = new ArrayList<>();
    public static ArrayList<Date> droppedDate1 = new ArrayList<>();
    public static int j = 0;
    //return an array list of flights

    public static ArrayList<String> getDroppedName() {
        return droppedName;
    }

    public static ArrayList<Date> getDroppedDate() {
        return droppedDate;
    }

    public static ArrayList<String> getDroppedName1() {
        return droppedName1;
    }

    public static ArrayList<Date> getDroppedDate1() {
        return droppedDate1;
    }
    
    public static void DeleteFlight(String flightName) {

        ArrayList<String> availableFlights;
        dfCon = startConnection.dbCon();
        try {   //store all the dropped customer from booking
            flightDrop = dfCon.prepareStatement("SELECT * FROM JAVA.BOOKING WHERE flightname = ?");
            flightDrop.setString(1, flightName);
            ResultSet rs = flightDrop.executeQuery();
            while (rs.next()) {
                droppedDate.add(rs.getDate(2));
                droppedName.add(rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {   //store all the dropped customer from waitlist
            flightDrop = dfCon.prepareStatement("SELECT * FROM JAVA.WAITLIST WHERE FLIGHT = ?");
            flightDrop.setString(1, flightName);
            ResultSet rs = flightDrop.executeQuery();
            while (rs.next()) {

                droppedDate1.add(rs.getDate(2));
                droppedName1.add(rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            flightDrop = dfCon.prepareStatement("DELETE FROM JAVA.WAITLIST WHERE FLIGHT = ?");
            flightDrop.setString(1, flightName);
            flightDrop.executeUpdate();
            
            flightDrop = dfCon.prepareStatement("DELETE FROM JAVA.FLIGHT WHERE flightname = ?");
            flightDrop.setString(1, flightName);
            flightDrop.executeUpdate();
            
            flightDrop = dfCon.prepareStatement("DELETE FROM JAVA.BOOKING WHERE flightname = ?");
            flightDrop.setString(1, flightName);
            flightDrop.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        GUIClass.updateBookingText("Flight " + flightName + " has been dropped.\n");
        
        availableFlights = Flight.getMyFlights();
        if(availableFlights.size() == 0)
        {
            if(!droppedName.isEmpty())
            {
                for(int k = 0; k < droppedName.size(); k++)
                {
                    GUIClass.updateBookingText(droppedName.get(k) + ". Your Flight" + " on "+ droppedDate.get(k) + ". Cancelled. \n"); 
                }
            }
            if(!droppedName1.isEmpty())
            {
                for(int k = 0; k < droppedName1.size(); k++)
                {
                    GUIClass.updateBookingText(droppedName1.get(k) + ". Your Flight" + " on "+ droppedDate1.get(k) + ". Cancelled. \n"); 
                }
            }
        }
        if(!droppedName.isEmpty())
        {
            for (int i = 0; i < availableFlights.size(); i++) {
                for(j = 0; j < droppedName.size(); j++){
                    if(i == availableFlights.size() - 1)
                    {
                        Booking.Booking(droppedName.get(j), availableFlights.get(i), droppedDate.get(j),4);

                    }
                    else
                    {
                        System.out.println(j);
                        System.out.println(droppedName.size());
                        Booking.Booking(droppedName.get(j), availableFlights.get(i), droppedDate.get(j),3);
                    }
                }
            }
        }
        if(!droppedName1.isEmpty())
        {
            for (int i = 0; i < availableFlights.size(); i++) {
                for(j = 0; j < droppedName1.size(); j++){
                    if(i == availableFlights.size() - 1)
                    {
                        Booking.Booking(droppedName1.get(j), availableFlights.get(i), droppedDate1.get(j),2);

                    }
                    else
                    {
                        Booking.Booking(droppedName1.get(j), availableFlights.get(i), droppedDate1.get(j),1);
                    }
                }
            }
        }
        droppedName.clear();
        droppedName1.clear();
        droppedDate.clear();
        droppedDate1.clear();
    }
    
}
