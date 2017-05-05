/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Wongsinsanity
 */
public class Booking {
    private static int totalSeats;
    private static int bookedSeats;
    public static ArrayList<EnterBooking> entries = new ArrayList<>();

    public static void Booking(String name, String flight, Date dated, int a) {

        //turn date to string
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = df.format(dated);
        //System.out.println(dateText);   

        // get booked and total seats
        bookedSeats = getSeats.getBookedSeats(flight, dated);
        totalSeats = getSeats.getAllSeats(flight);
        //try to book  
        if(a == 0)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Booked. \n");
            }
            //try to add to waitlist
            else
            {
                waitList.Waitlist(name, flight, dated);  
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Waitlisted. \n");
            }
        }
        else if(a == 1)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Booked from waitlist. \n");
                DeleteFlight.getDroppedName1().remove(name);
                DeleteFlight.getDroppedDate1().remove(dated);

                DeleteFlight.j = DeleteFlight.j - 1;
            }
            //try to add to waitlist
            else
            {

            }
        }
        else if(a == 2)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Booked from waitlist. \n");
                DeleteFlight.getDroppedName1().remove(name);
                DeleteFlight.getDroppedDate1().remove(dated);

                DeleteFlight.j = DeleteFlight.j - 1;

            }
            //try to add to waitlist
            else
            {
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Cancelled. \n");
            }
        }
        else if(a == 3)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Rebooked. \n");
                DeleteFlight.getDroppedName().remove(name);
                DeleteFlight.getDroppedDate().remove(dated);

                DeleteFlight.j = DeleteFlight.j - 1;
            }
            //try to add to waitlist
            else
            {

            }
        }
        else if(a == 4)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Rebooked. \n");
                DeleteFlight.getDroppedName().remove(name);
                DeleteFlight.getDroppedDate().remove(dated);

                DeleteFlight.j = DeleteFlight.j - 1;
            }
            //try to add to waitlist
            else
            {
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Cancelled. \n");
            }
        }
        else if(a == 5)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Booked from waitlist. \n");
                DeleteDate.droppedName3.remove(name);
                DeleteDate.droppedFlight1.remove(flight);

                DeleteDate.d = DeleteDate.d - 1;
            }
            //try to add to waitlist
            else
            {

            }
        }
        else if(a == 6)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Booked from waitlist. \n");
                DeleteDate.droppedName3.remove(name);
                DeleteDate.droppedFlight1.remove(flight);

                DeleteDate.d = DeleteDate.d - 1;

            }
            //try to add to waitlist
            else
            {
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Cancelled. \n");
            }
        }
        else if(a == 7)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Rebooked. \n");
                DeleteDate.droppedName2.remove(name);
                DeleteDate.droppedFlight.remove(flight);

                DeleteDate.d = DeleteDate.d - 1;
            }
            //try to add to waitlist
            else
            {

            }
        }
        else if(a == 8)
        {
            if(totalSeats > bookedSeats)
            {
                EnterBooking.insertEntry(name, flight, dated);
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Rebooked. \n");
                DeleteDate.droppedName2.remove(name);
                DeleteDate.droppedFlight.remove(flight);

                DeleteDate.d = DeleteDate.d - 1;
            }
            //try to add to waitlist
            else
            {
                GUIClass.updateBookingText(name + ". Your Flight: " +flight+ " on "+ dateText + ". Cancelled. \n");
            }
        }

    }

        public static void FlightStatus( String flight, Date dated){

            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            String dateText = df.format(dated);
            //System.out.println(dateText); 

            GUIClass.insertAreaStatus(" People booked for flight "+ flight + " on "+ dateText + ". \n");
            Flight.getBookedPeople(flight, dated);


        }


}
