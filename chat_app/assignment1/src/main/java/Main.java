import BussinessLogic.BankBll;
import BussinessLogic.Controller;
import DataAccessLogic.*;
import Model.Account;
import Model.Activity;
import Model.Client;
import Model.Employee;
import PresentationLogic.View;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        z_DBinit db = new z_DBinit(); //just once
        List<String> rez;
        View view = new View();
        Controller c = new Controller(view);
        /*
        ClientDAO cd =  new ClientDAO();
        Client c = new Client("om de test","1234324", "adress", "phone");
        //cd.add(c);
         rez = cd.view();
        for(String i : rez)
        {
            System.out.println(i);
        }

        cd.update(1, c);
        rez = cd.view();
        for(String i : rez)
        {
            System.out.println(i);
        }
---------------------------------------------------
        EmployeeDAO em =  new EmployeeDAO();
        Employee emp = new Employee("omu nostru", "are si un cnp",1234);
        em.add(emp);
        rez = em.view();
        for(String i : rez)
        {
            System.out.println(i);
        }

        System.out.println("\n");
        em.delete(1);
        em.delete(6);
        em.update(2,emp);

        rez = em.view();
        for(String i : rez)
        {
            System.out.println(i);
        }
--------------------------------------------------


        AccountDAO ad =  new AccountDAO();
        Account a = new Account("al meu", 123,124);

        ad.add(a);
        rez = ad.view();
        for(String i : rez)
        {
            System.out.println(i);
        }

        ad.delete(2);
        ad.delete(6);
        ad.update(3,a);
        rez = ad.view();
        for(String i : rez)
        {
            System.out.println(i);
        }
        ---------------------------------------------------

        //ActivityDAO aa = new ActivityDAO();
        Activity a = new Activity(1, "Transfer Money", 303);
        BankBll bb = new BankBll();

        bb.addActivity(a);
        rez = bb.viewActivity();
        for(String i : rez)
        {
            System.out.println(i);
        }

        if(bb.verEmployee("5")) System.out.println("da exista"); else System.out.println("nu exista");
*/




    }


}
