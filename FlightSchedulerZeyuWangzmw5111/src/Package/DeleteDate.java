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
import java.util.ArrayList;

/**
 *
 * @author Wongsinsanity
 */
public class DeleteDate {
    private static Connection ddCon;
    private static PreparedStatement dateDrop = null;

    public static ArrayList<String> droppedName2 = new ArrayList<>();
    public static ArrayList<String> droppedFlight = new ArrayList<>();
    public static ArrayList<String> droppedName3 = new ArrayList<>();
    public static ArrayList<String> droppedFlight1 = new ArrayList<>();
    public static int d = 0;

    public static void DeleteDate(Date date) {
        ArrayList<Date> availableDates;
        ddCon = startConnection.dbCon();
        try {
            dateDrop = ddCon.prepareStatement("SELECT * FROM JAVA.BOOKING WHERE DATE = ?");
            dateDrop.setDate(1, date);
            ResultSet rs = dateDrop.executeQuery();
            while (rs.next()) {
                droppedFlight.add(rs.getString(1));
                droppedName2.add(rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {   //store all the dropped customer from waitlist
            dateDrop = ddCon.prepareStatement("SELECT * FROM JAVA.WAITLIST WHERE DATE = ?");
            dateDrop.setDate(1, date);
            ResultSet rs = dateDrop.executeQuery();
            while (rs.next()) {

                droppedFlight1.add(rs.getString(1));
                droppedName3.add(rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            dateDrop = ddCon.prepareStatement("DELETE FROM JAVA.WAITLIST WHERE DATE = ?");
            dateDrop.setDate(1, date);
            dateDrop.executeUpdate();
            
            dateDrop = ddCon.prepareStatement("DELETE FROM JAVA.DATE WHERE DATE = ?");
            dateDrop.setDate(1, date);
            dateDrop.executeUpdate();
            
            dateDrop = ddCon.prepareStatement("DELETE FROM JAVA.BOOKING WHERE DATE = ?");
            dateDrop.setDate(1, date);
            dateDrop.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        GUIClass.updateBookingText("Date: " + date + " has been dropped.\n");

        
        
        availableDates = getMyDate.getMyDate();
        if(availableDates.size() == 0)
        {
            if(!droppedName2.isEmpty())
            {
                for(int k = 0; k < droppedName2.size(); k++)
                {
                    GUIClass.updateBookingText(droppedName2.get(k) + ". Your Flight" + " on "+ droppedFlight.get(k) + ". Cancelled. \n"); 
                }
            }
            if(!droppedName3.isEmpty())
            {
                for(int k = 0; k < droppedName3.size(); k++)
                {
                    GUIClass.updateBookingText(droppedName3.get(k) + ". Your Flight" + " on "+ droppedFlight1.get(k) + ". Cancelled. \n"); 
                }
            }
        }
        if(!droppedName2.isEmpty())
        {
            for (int i = 0; i < availableDates.size(); i++) {
                for(d = 0; d < droppedName2.size(); d++){
                    if(i == availableDates.size() - 1)
                    {
                        Booking.Booking(droppedName2.get(d), droppedFlight.get(d), availableDates.get(i),8);

                    }
                    else
                    {
                        Booking.Booking(droppedName2.get(d), droppedFlight.get(d), availableDates.get(i),7);
                    }
                }
            }
        }
        if(!droppedName3.isEmpty())
        {
            for (int i = 0; i < availableDates.size(); i++) {
                for(d = 0; d < droppedName3.size(); d++){
                    if(i == availableDates.size() - 1)
                    {
                        Booking.Booking(droppedName3.get(d), droppedFlight1.get(d), availableDates.get(i),6);

                    }
                    else
                    {
                        Booking.Booking(droppedName3.get(d), droppedFlight1.get(d), availableDates.get(i),5);
                    }
                }
            }
        }
        droppedName2.clear();
        droppedName3.clear();
        droppedFlight.clear();
        droppedFlight1.clear();
    }

}
