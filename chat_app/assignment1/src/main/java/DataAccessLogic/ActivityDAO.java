package DataAccessLogic;

import Model.Activity;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityDAO {
    final Logger LOGGER =  Logger.getLogger("ActivityDAO");
    Connection connection = null;
    // add into DB
    public void add(Activity a){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into activity values (null, \'" + a.getEmployeeId() + "\' , \'" + a.getDescription() + "\'  , " + a.getDate() + ");");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Activity already exists", "ERROR",JOptionPane.ERROR_MESSAGE);
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
                }
            }
        }
    }

    public List<String> view(int id, int from, int to){
        List<String> rez = new ArrayList<String>();
        String aux;
        ResultSet rs = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            rs = statement.executeQuery("select * from activity WHERE (date between " + from + " and " + to + ") and (employeeId = " + id + ");");
            //int empid = rs.getInt("employeeId");
            //if(empid == id){//
                //if(date >= from && date <= to){
                    info = rs.getMetaData();

                    //rs = statement.executeQuery("select employeeId, description, date from activity WHERE (date between " + from + " and " + to + ") and (employeeId = " + id +");");

                    while(rs.next()){
                        aux = "";
                        for(int i = 1; i <= info.getColumnCount(); i++){
                            aux += rs.getString(i) + " ";
                        }
                        rez.add(aux);
                    }

            //}
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:view " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                    if(rez == null) return null;
                    return rez;
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:view " + e.getMessage());
                }
            }
        }

        if(rez == null) return null;
        return rez;
    }
}
