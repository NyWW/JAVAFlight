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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Wongsinsanity
 */
public class EnterWaitlist {
     private static Connection con = startConnection.dbCon();
     private static PreparedStatement myStat = null;
     private static ArrayList<String> waitlistMembers = new ArrayList<String>();
     private static ArrayList<String> waitlistFlight = new ArrayList<String>();
   public static void insertWait(String name, String flight, Date dated) 
   {
      Timestamp t;
       try {
        //name flight date
        //insert into waitlist table
          myStat = con.prepareStatement("INSERT into waitlist values(?,?,?,?)"); 
          myStat.setString(1, flight);
          myStat.setDate(2, dated);
          myStat.setString(3,name);
          myStat.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
          myStat.executeUpdate();
    } catch (SQLException ex) {
       ex.printStackTrace();
       
    }
   }
   public static void getWaitlistedPeople( String flight, Date dated){
       //get the waitlist of people on the flight on a day in timestamp ascending order
       con = startConnection.dbCon();
        try {
            //myStat = con.prepareStatement("SELECT name FROM JAVA.waitlist WHERE flight =? AND date = ? ORDER BY timestamp ASC");
            myStat = con.prepareStatement("SELECT * FROM JAVA.waitlist WHERE date = ? ORDER BY timestamp ASC");
            //myStat.setString(1,flight);
            myStat.setDate(1,dated);
            ResultSet rs = myStat.executeQuery();
            while (rs.next()) {
                waitlistMembers.add(rs.getString(3));
                waitlistFlight.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        //if the arraylist of names is empty print noone on waitlist
        if(waitlistMembers.isEmpty()){
             GUIClass.insertAreaStatus("No Waitlist \n");

        }
        //print the names on the waitlist
        for(int i = 0; i < waitlistMembers.size(); i++)
        {
            GUIClass.insertAreaStatus(waitlistMembers.get(i) +"\t");
            GUIClass.insertAreaStatus(waitlistFlight.get(i) +"\n");
        }
        //clear array after printed
          waitlistMembers.clear();
          waitlistFlight.clear();
    }
  
}
