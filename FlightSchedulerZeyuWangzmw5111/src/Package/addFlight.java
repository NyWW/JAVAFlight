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
public class addFlight {
    private static Connection flightACon;
    private static PreparedStatement addFlightState;

    public static void addFlight(String string, int seats) {
        flightACon = startConnection.dbCon();
        try {
            addFlightState = flightACon.prepareStatement("INSERT into FLIGHT values (?,?)");
            addFlightState.setString(1, string);
            addFlightState.setInt(2, seats);
            addFlightState.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
