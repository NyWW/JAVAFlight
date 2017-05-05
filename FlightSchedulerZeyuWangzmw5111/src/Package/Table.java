/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;


import java.sql.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wongsinsanity
 */
public class Table extends AbstractTableModel{

    private final Connection con;
    //private final Statement state;
    private ResultSet result;
    private ResultSetMetaData metadata;
    private int numOfRows;
    private boolean connected = false;
    private static PreparedStatement tableState = null;
    public Table(String query, String flight) throws SQLException
    {
        
        con = startConnection.dbCon();
        tableState = con.prepareStatement("SELECT * FROM JAVA.BOOKING WHERE flightname = ? and date = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        tableState.setDate(2, Date.valueOf(query));
        tableState.setString(1, flight);
        //tableState = con.prepareStatement("SELECT * FROM BOOKING"
            //,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        
        //state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        connected = true;
        //String temp = tableState.toString();
        //System.out.println(temp);
        setQuery(tableState);
    }
    @Override
    public Class getColumnClass(int column)
            throws IllegalStateException
    {
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        try
        {
            String className = metadata.getColumnClassName(column + 1);
            return Class.forName(className);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return Object.class;
    }
    @Override
    public int getRowCount() 
    throws IllegalStateException
    {
        
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        return numOfRows;
    }

    @Override
    public int getColumnCount()
        throws IllegalStateException
    {
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        try
        {
            return metadata.getColumnCount();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
        return 0;
    }
    public String getColumnName(int column)
            throws IllegalStateException
    {
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        try
        {
            return metadata.getColumnName(column + 1);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
        throws IllegalStateException
    {
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        try
        {
            result.absolute(rowIndex + 1);
            return result.getObject(columnIndex + 1);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
        return "";
    }

    private void setQuery(PreparedStatement query) 
            throws SQLException,IllegalStateException
    {
        if(!connected){
            throw new IllegalStateException("Not Connected to Database");
        }
        result = query.executeQuery();
        metadata = result.getMetaData();
        result.last();
        numOfRows = result.getRow();
        fireTableStructureChanged();
    }
    
}
