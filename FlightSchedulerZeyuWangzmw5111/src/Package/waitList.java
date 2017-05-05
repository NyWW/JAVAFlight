/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Wongsinsanity
 */
public class waitList {

    //insert entry into waitlist table
    public static void Waitlist(String name, String flight, Date dated) {     
        EnterWaitlist.insertWait(name, flight, dated);
    }
    
    //get status of all waitlists
      public static void WaitlistStatus( String flight, Date dated){
       //turn date to string
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = df.format(dated);
        //System.out.println(dateText);  
        //GUIClass.insertAreaStatus(" People waitlisted for flight " +flight+ " on " + dateText+".\n");
        GUIClass.insertAreaStatus("People waitlisted" + " on " + dateText+".\n");
        EnterWaitlist.getWaitlistedPeople(flight ,dated);
    
    }

}
