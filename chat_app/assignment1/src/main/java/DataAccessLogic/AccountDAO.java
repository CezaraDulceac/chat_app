package DataAccessLogic;


import Model.Account;
import Model.Client;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO {
    final Logger LOGGER =  Logger.getLogger("AccountsDAO");
    Connection connection = null;
    // add into DB
    public void add(Account a){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into account values (null, \'" + a.getType() + "\' , \'" + a.getMoney() + "\'  , " + a.getDate() + ");");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Account already exists", "ERROR",JOptionPane.ERROR_MESSAGE);
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
    public void update(int id, Account a){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("UPDATE account SET type = \'" + a.getType()  +
                    "\', money =  " + a.getMoney() +
                    ", date =  " + a.getDate() +
                    " WHERE idNumber = " + id +" ;");
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
    //delete
    public void delete(int id){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("DELETE FROM account WHERE idNumber = " + id +";");
        } catch (SQLException e) {
            // e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:delete" + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:delete" + e.getMessage());
                }
            }
        }
    }
    //view DB
    public  List<String> view(){
        List<String> rez = new ArrayList<String>();
        String aux;
        ResultSet rs = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("select * from account");
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
                    LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
                }
            }
        }

        if(rez == null) return null;
        return rez;
    }

    public boolean verAccountId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select idNumber from account WHERE idNumber = " + id + ";");
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

    public Account getAccountId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select idNumber, type, money, date from account WHERE idNumber = " + id + ";");
            info = rs.getMetaData();
            while(rs.next()){
                if(rs.getInt("idNumber") == id){
                    Account a = new Account(rs.getString("type"), rs.getInt("money"),rs.getInt("date"));
                    return a;
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
    public int getMoneyId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select idNumber, money from account WHERE idNumber = " + id + ";");

            if(rs.getInt("idNumber") == id){
                System.out.println(rs.getInt("money"));
                return rs.getInt("money");
            }
            return -1;
        }
        catch(Exception e){
            LOGGER.log(Level.WARNING, "DAO:getMoney" + e.getMessage());
            return -1;
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
    public void updateMoneyId(int id, int sum, boolean inOut){ //in = add / out = remove
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;

            int valM = 0;

            rs = statement.executeQuery("select idNumber, money from account WHERE idNumber = " + id + ";");
            if(rs.getInt("idNumber") == id){
                if(inOut){
                    valM = rs.getInt("money") + sum;
                }else
                {
                    valM = rs.getInt("money") - sum;
                }
            }
            statement.executeUpdate("UPDATE account SET money = "
                     + valM +
                    " WHERE idNumber = " + id +" ;");
        }
        catch(Exception e){
            LOGGER.log(Level.WARNING, "DAO:UPDATE money" + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "DAO:UPDATE money" + e.getMessage());
                }
            }
        }

    }
}
