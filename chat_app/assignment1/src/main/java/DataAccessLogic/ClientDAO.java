package DataAccessLogic;

import Model.Client;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO {

    final Logger LOGGER =  Logger.getLogger("ClientDAO");

    //add into DB
    public void add(Client c){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into client values (null, \'" + c.getName() + "\' , \'" + c.getCnp() + "\' , \'" + c.getAddress() +
                    "\' , \'" + c.getPhone() + "\');");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Client already exists", "ERROR",JOptionPane.ERROR_MESSAGE);
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

    //update DB
    public void update(int id, Client c){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("UPDATE client SET name = \'" + c.getName()  +
                    "\', cnp =  \'" + c.getCnp() +
                    "\', address =  \'" + c.getAddress() +
                    "\', phone = \'" + c.getPhone() +
                    "\' WHERE idNumber = " + id +" ;");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:update" + e.getMessage());

        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:update" + e.getMessage());
                }
            }
        }
    }

    //view DB
    public List<String> view(){
        List<String> rez = new ArrayList<String>();
        String aux;
        ResultSet rs = null;
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("select * from client");
            info = rs.getMetaData();
            while(rs.next()){
                aux = "";
                for(int i = 1; i <= info.getColumnCount(); i++){
                    aux += rs.getString(i) + " ";
                }
                rez.add(aux);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:view" + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                    if(rez == null) return null;
                    return rez;
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:update" + e.getMessage());
                }
            }
        }

        if(rez == null) return null;

        return rez;
    }
    public boolean verClientId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select idNumber from client WHERE idNumber = " + id + ";");
            info = rs.getMetaData();
            if(rs.getInt("idNumber") == id)
                return true;
            return false;
        }
        catch(Exception e){
            return false;
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:verID" + e.getMessage());
                }
            }
        }

    }

    public Client getClientId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select idNumber,name,cnp,address,phone from client WHERE idNumber = " + id + ";");
            info = rs.getMetaData();
            while(rs.next()){
                if(rs.getInt("idNumber") == id){
                    Client c = new Client(rs.getString("name"), rs.getString("cnp"),rs.getString("address"), rs.getString("phone"));
                    return c;
                }
            }


            return null;
        }
        catch(Exception e){
            LOGGER.log(Level.WARNING, "DAO:verID" + e.getMessage());
            return null;
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:verID" + e.getMessage());
                }
            }
        }

    }

}
