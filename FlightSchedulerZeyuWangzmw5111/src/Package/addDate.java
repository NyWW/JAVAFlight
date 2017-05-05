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
public class addDate {
    private static Connection dateACon;
    private static PreparedStatement addDateState;

    public static void addDate(Date date) {
        dateACon = startConnection.dbCon();
        try {
            addDateState = dateACon.prepareStatement("INSERT into DATE values ?");
            addDateState.setDate(1, date);
            addDateState.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
