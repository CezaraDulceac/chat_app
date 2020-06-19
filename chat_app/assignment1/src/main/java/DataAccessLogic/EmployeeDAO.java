package DataAccessLogic;


import Model.Client;
import Model.Employee;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {
    final Logger LOGGER =  Logger.getLogger("EmployeeDAO");
    // add into DB
    public void add(Employee em){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into employee values (null, \'" + em.getName() + "\' , \'" + em.getCnp() + "\'  , " + em.getSalary() + ");");
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "DAO:add" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Employee already exists", "ERROR",JOptionPane.ERROR_MESSAGE);
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
    public void update(int id, Employee em){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("UPDATE employee SET name = \'" + em.getName()  +
                    "\', cnp =  \'" + em.getCnp() +
                    "\', salary =  \'" + em.getSalary() +
                    "\' WHERE employeeId = " + id +" ;");
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
    //delete employee
    public void delete(int id){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("DELETE FROM employee WHERE employeeId = " + id +";");
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
            rs = statement.executeQuery("select * from employee");
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
                    LOGGER.log(Level.WARNING, "DAO:verID" + e.getMessage());
                }
            }
        }
        if(rez == null) return null;
        return rez;
    }

    public boolean verEmpId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select employeeId from employee WHERE employeeId = " + id + ";");
            info = rs.getMetaData();
           // System.out.println(rs.getInt("employeeId"));
            if(rs.getInt("employeeId") == id)
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
    public Employee getEmployeeId(int id){
        Connection connection = null;
        ResultSetMetaData info = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = null;


            rs = statement.executeQuery("select employeeId,name,cnp,salary from employee WHERE employeeId = " + id + ";");
            while(rs.next()){
                if(rs.getInt("employeeId") == id){
                    Employee em = new Employee(rs.getString("name"), rs.getString("cnp"),rs.getInt("salary"));
                    return em;
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
