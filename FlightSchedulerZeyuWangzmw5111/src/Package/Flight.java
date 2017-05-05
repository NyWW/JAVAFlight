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
public class Flight {

    int totalSeats;
    int bookedSeats;

    private static Connection FlightCon;
    private static PreparedStatement FlightStat = null;
    private static ArrayList<String> bookedMembers = new ArrayList<String>();

    //return an array list of flights
    public static ArrayList<String> getMyFlights() {
        ArrayList<String> myFlights = new ArrayList<>();
        FlightCon = startConnection.dbCon();
        try {
            FlightStat = FlightCon.prepareStatement("SELECT flightname FROM JAVA.FLIGHT");
            ResultSet rs = FlightStat.executeQuery();
            while (rs.next()) {
                myFlights.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return myFlights;
    }

    //get an arraylist of booked member on certain flight on certain day and output to text area
    public static void getBookedPeople(String flight, Date dated) {
        FlightCon = startConnection.dbCon();
        try {
            FlightStat = FlightCon.prepareStatement("SELECT name FROM JAVA.BOOKING WHERE flightname = ? AND date = ?");
            FlightStat.setString(1, flight);
            FlightStat.setDate(2, dated);
            ResultSet rs = FlightStat.executeQuery();
            while (rs.next()) {
                bookedMembers.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        //if noone is booked
        if (bookedMembers.isEmpty()) {
            GUIClass.insertAreaStatus("None booked \n");
        }
        else
        {
            for (String s : bookedMembers) {
                GUIClass.insertAreaStatus(s + "\n");
            }

        }//print to text area

//clear the array list of names       
        bookedMembers.clear();
    }
}
