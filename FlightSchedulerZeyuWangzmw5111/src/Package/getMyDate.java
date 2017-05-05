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
public class getMyDate {
    private static Connection dateCon;
    private static PreparedStatement dateState;

    public static ArrayList<Date> getMyDate() {
        ArrayList<Date> myDates = new ArrayList<>();
        dateCon = startConnection.dbCon();
        try {
            dateState = dateCon.prepareStatement("SELECT DATE FROM JAVA.DATE");
            ResultSet rs = dateState.executeQuery();
            while (rs.next()) {
                myDates.add(rs.getDate(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return myDates;

    }
}
