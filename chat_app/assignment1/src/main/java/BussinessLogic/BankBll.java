package BussinessLogic;

import DataAccessLogic.*;
import Model.*;

import javax.swing.*;
import java.util.List;

public class BankBll {
    AccountDAO ad =  new AccountDAO();
    ActivityDAO actd = new ActivityDAO();
    ClientDAO cd = new ClientDAO();
    EmployeeDAO ed = new EmployeeDAO();
    UtilityDAO ud = new UtilityDAO();
    //---------------------------------------------
    //                  VIEW tables

    public List<String> viewAccount(){
        List<String> ls = ad.view();
        return ls;
    }
    public List<String> viewActivity(int id, int from, int to){
        List<String> ls = actd.view(id,from,to);
        return ls;
    }
    public List<String> viewClient(){
        List<String> ls = cd.view();
        return ls;
    }
    public List<String> viewEmployee(){
        List<String> ls = ed.view();
        return ls;
    }
    public List<String> viewUtility(int id){
        List<String> ls = ud.view(id);
        return ls;
    }
    //------------------------------------------------
    //                  ADD into TABLES

    public void addAccount(Account a){
        ad.add(a);
    }

    public void addActivity(Activity a){
        actd.add(a);
    }

    public void addClient(Client c){
        cd.add(c);
    }

    public void addEmployee(Employee e){
        ed.add(e);
    }
    public void addUtility(Utility u){
        ud.add(u);
    }
    //------------------------------------------------
    //                  UPDATE TABLES

    public void updateAccount(Account a, int id){
        ad.update(id, a);
    }
    public void updateClient(Client c, int id){
        cd.update(id, c);
    }
    public void updateEmployee(Employee e, int id){
        ed.update(id, e);
    }
    //----------------------------------------------------
    //                  DELETE from TABLES

    public void deleteAccount(int id){
        ad.delete(id);
    }
    public void deleteEmployee(int id) {
        ed.delete(id);
    }
    public void deleteClientUtility(int id){
        ud.delete(id);
    }
    //-----------------------------------------------------
    //                    AUX FUNCTIONS

    public boolean transferMoney(int id1, int id2, int sum){
        if(ad.getMoneyId(id1) - sum >= 0){
            ad.updateMoneyId(id1,sum,false);
            ad.updateMoneyId(id2,sum,true);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Not enough money", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public Client getClientWid(int id){
        return cd.getClientId(id);
    }
    public Account getAccountWid(int id){
        return ad.getAccountId(id);
    }
    public Employee getEmployeeWid(int id){
        return ed.getEmployeeId(id);
    }
    public boolean verEmployee(String input){
        try{
            int id = Integer.parseInt(input);
            if(ed.verEmpId(id)){
                return true;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Incorrect User", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean verClient(String input){
        int id = Integer.parseInt(input);
        if(cd.verClientId(id)){
            return true;
        }
        return false;
    }
    public boolean verAccount(String input){
        int id = Integer.parseInt(input);
        if(ad.verAccountId(id)){
            return true;
        }
        return false;
    }

    public String userPass(String input){
       String aux = "";
        for(int i = 0; i < 5; i++){
           aux += input;
       }
        return aux;
    }
}
