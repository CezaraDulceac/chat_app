package BussinessLogic;

import Model.*;
import PresentationLogic.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    View view;
    String regex = "[0-9]+";
    String letters = "^[a-zA-Z_\\s]+";
    BankBll bb = new BankBll();

    public Controller(View view) {
        this.view = view;

        view.thisActionListener(new Manager());
    }

    class Manager implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //-------------------------------------------------------------------------------------------
            //                               USER
            //---------------------------------------------------------
            //                     CLIENT

            // -------------------------------------------------------------------------------
            //                  ADD CLIENT

            if (e.getSource() == view.AC) {

                String name = view.nameTF.getText();
                String cnp = view.CNPTF.getText();
                String address = view.addTF.getText();
                String phone = view.phoneTF.getText();

                if (cnp.matches(regex) && name.matches(letters) && phone.matches(regex)) { //
                    Client c = new Client(fixStrings(name), cnp, fixStrings(address), phone);
                    bb.addClient(c);
                    createAddActivity(view.getUserId(), fixStrings("Add Client"), view.getDate());
                    createAddUtility(c.getId(), "Add_Client", 100);
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                // -------------------------------------------------------------------------------
                //                 UPDATE CLIENT

            } else if (e.getSource() == view.FC) {

                if (bb.verClient(view.UIDTF.getText())) {
                    int id = Integer.parseInt(view.UIDTF.getText());
                    Client c = bb.getClientWid(id);

                    if (c != null) {
                        view.UNTF.setText(c.getName());
                        view.UCNPTF.setText(c.getCnp());
                        view.UATF.setText(c.getAddress());
                        view.UPTF.setText(c.getPhone());
                    } else {
                        System.out.println("PRBLEM CREATING CLIENT");
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Client ID doesn't exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == view.UC) {

                String name = view.UNTF.getText();
                String cnp = view.UCNPTF.getText();
                String address = view.UATF.getText();
                String phone = view.UPTF.getText();

                if (cnp.matches(regex) && name.matches(letters) && phone.matches(regex)) { //

                    int id = Integer.parseInt(view.UIDTF.getText());
                    Client c = new Client(fixStrings(name), cnp, fixStrings(address), phone);
                    bb.updateClient(c, id);
                    createAddActivity(view.getUserId(), fixStrings("Update Client"), view.getDate());
                    createAddUtility(id, "Update_Client", 200);
                    JOptionPane.showMessageDialog(null, "Successfully updated client.");
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                // -------------------------------------------------------------------------------
                //                  VIEW CLIENT

            } else if (e.getSource() == view.btnView) {
                List<String> ls = bb.viewClient();
                String[] columnName = {"ID", "Name", "CNP", "Address", "Phone"};
                DefaultTableModel model = new DefaultTableModel(columnName, 0);
                Object rowData[] = new Object[ls.size()];

                for (int i = 0; i < ls.size(); i++) {
                    String[] s = ls.get(i).split("\\s+");
                    rowData[0] = s[0];
                    rowData[1] = s[1];
                    rowData[2] = s[2];
                    rowData[3] = s[3];
                    rowData[4] = s[4];

                    model.addRow(rowData);
                }
                view.CTable.setModel(model);
            }
            /// -------------------------------------------------------------------------------
            //                              ACCOUNT
            // --------------------------------------
            //                  ADD ACCOUNT

            if (e.getSource() == view.AA) {

                String type = view.TTFA.getText();
                String money = view.MTFA.getText();
                String date = view.DTFA.getText();

                if (money.matches(regex) && date.matches(regex) && (type.equals("professional") || type.equals("personal"))) {
                    Account a = new Account(type, Integer.parseInt(money), Integer.parseInt(date));
                    bb.addAccount(a);
                    createAddActivity(view.getUserId(), fixStrings("Add_Account"), view.getDate());
                    createAddUtility(a.getIdNumber(), "Add_Account", 100);
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                //-------------------------------------------------------------------------------
                //                  UPDATE ACCOUNT

            } else if (e.getSource() == view.FA) {
                if (bb.verAccount(view.IDTFUA.getText())) {
                    int id = Integer.parseInt(view.IDTFUA.getText());
                    Account a = bb.getAccountWid(id);


                    if (a != null) {
                        view.TTFUA.setText(a.getType());
                        view.MTFUA.setText(Integer.toString(a.getMoney()));
                        view.DTFUA.setText(Integer.toString(a.getDate()));
                    } else {
                        System.out.println("PROBLEM CREATING ACCOUNT");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Account ID doesn't exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == view.UA) {

                String type = view.TTFUA.getText();
                String money = view.MTFUA.getText();
                String date = view.DTFUA.getText();

                if (money.matches(regex) && type.matches(letters) && date.matches(regex)) { //

                    int id = Integer.parseInt(view.IDTFUA.getText());
                    Account a = new Account(fixStrings(type), Integer.parseInt(money), Integer.parseInt(date));
                    bb.updateAccount(a, id);
                    createAddActivity(view.getUserId(), fixStrings("Update Account"), view.getDate());
                    createAddUtility(id, "Update_Account", 250);
                    JOptionPane.showMessageDialog(null, "Successfully updated Account.");
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            //-------------------------------------------------------------------------------s
            //                          VIEW ACCOUNT
            else if (e.getSource() == view.btnViewA) {
                List<String> ls = bb.viewAccount();
                String[] columnName = {"ID", "Type", "Amount of Money", "Date"};
                DefaultTableModel model = new DefaultTableModel(columnName, 0);
                Object rowData[] = new Object[ls.size()];

                for (int i = 0; i < ls.size(); i++) {
                    String[] s = ls.get(i).split("\\s+");
                    rowData[0] = s[0];
                    rowData[1] = s[1];
                    rowData[2] = s[2];
                    rowData[3] = s[3];

                    model.addRow(rowData);
                }
                view.ATable.setModel(model);
            }
            //-------------------------------------------------------------------------------
            //                         DELETE ACCOUNT

            else if (e.getSource() == view.FAD) {
                if (bb.verAccount(view.IDTFD.getText())) {
                    view.DELETE.setText("Found");
                } else {
                    view.DELETE.setText("Not found");
                }
            } else if (e.getSource() == view.DA) {


                if (bb.verAccount(view.IDTFD.getText())) {
                    bb.deleteAccount(Integer.parseInt(view.IDTFD.getText()));
                    view.DELETE.setText("Deleted");
                    createAddActivity(view.getUserId(), fixStrings("Delete Account"), view.getDate());
                    createAddUtility(Integer.parseInt(view.IDTFD.getText()), "Delete_Account", 300);
                } else {
                    view.DELETE.setText("Couldn't delete");
                }
            }
            /// -------------------------------------------------------------------------------
            //                             TRANSFER MONEY
            else if (e.getSource() == view.T) {

                int id1 = Integer.parseInt(view.IDTFTM1.getText());
                int id2 = Integer.parseInt(view.IDTFTM2.getText());
                int amount = Integer.parseInt(view.AMTFTM.getText());

                if(bb.transferMoney(id1, id2, amount)){
                    view.OK.setIcon(new ImageIcon("images\\a.png"));
                    createAddActivity(view.getUserId(), fixStrings("Transfer Money"), view.getDate());
                    createAddUtility(Integer.parseInt(view.IDTFTM1.getText()), "Transfer_Money", 50);

                }else{
                    JOptionPane.showMessageDialog(null, "Couldn't transfer money", "ERROR", JOptionPane.ERROR_MESSAGE);
                    view.OK.setIcon(new ImageIcon("images\\b.png"));
                }
            }
            //----------------------------------------------------------
            //                      PROCESS UTILITY BILLS
            else if(e.getSource() == view.P){
                int sum = 0;
                String idS = view.IDTFPUB.getText();
                if(bb.verClient(idS)){
                    int id = Integer.parseInt(idS);
                    List<String> ls = bb.viewUtility(id);

                    for (int i = 0; i < ls.size(); i++) {
                        String[] s = ls.get(i).split("\\s+");

                        for(String f : s) System.out.println(f);

                        sum += Integer.parseInt(s[3]);
                        bb.deleteClientUtility(id);
                    }

                    view.PRET.setText(Integer.toString(sum));
                    createAddActivity(view.getUserId(), fixStrings("Process Utility Bills"), view.getDate());
                }else{
                    JOptionPane.showMessageDialog(null, "Client ID doen't exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
                //-------------------------------------------------------------------------------------------
                //                               ADMINISTRATOR
                //----------------------------------------------------------
                //                     EMPLOYEE

                // ---------------------------------------------------------
                //                      ADD EMPLOYEE

            else if(e.getSource() == view.AE){
                String name = view.NTFE.getText();
                String cnp = view.CNPTFE.getText();
                String salary = view.STFE.getText();

                if (cnp.matches(regex) && name.matches(letters) && salary.matches(regex)) { //
                    Employee em = new Employee(fixStrings(name), cnp,  Integer.parseInt(salary));
                    bb.addEmployee(em);
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                //-------------------------------------------------------------------------------
                //                  UPDATE EMPLOYEE

            } else if(e.getSource() == view.FE){
                if (bb.verEmployee(view.IDTFUE.getText())) {
                    int id = Integer.parseInt(view.IDTFUE.getText());
                    Employee em = bb.getEmployeeWid(id);


                    if (em != null) {
                        view.NTFUE.setText(em.getName());
                        view.STFUE.setText(Integer.toString(em.getSalary()));
                        view.CNPTFUE.setText(em.getCnp());
                    } else {
                        System.out.println("PROBLEM UPDATING EMPLOYEE");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID doesn't exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == view.UE) {
                String name = view.NTFUE.getText();
                String salary = view.STFUE.getText();
                String cnp = view.CNPTFUE.getText();

                if (salary.matches(regex) && cnp.matches(regex) && name.matches(letters)) { //

                    int id = Integer.parseInt(view.IDTFUE.getText());
                    Employee em = new Employee(fixStrings(name), cnp,  Integer.parseInt(salary));
                    bb.updateEmployee(em, id);
                    JOptionPane.showMessageDialog(null, "Successfully updated Employee.");
                } else {
                    JOptionPane.showMessageDialog(null, "Some fields were incorrect, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            //-------------------------------------------------------------------------------
            //                         DELETE EMPLOYEE
            else if(e.getSource() == view.FED){
                if (bb.verEmployee(view.IDTFDE.getText())) {
                    view.DELETEE.setText("Found");
                } else {
                    view.DELETEE.setText("Not found");
                }
            } else if (e.getSource() == view.DE) {
                if (bb.verEmployee(view.IDTFDE.getText())) {
                    bb.deleteEmployee(Integer.parseInt(view.IDTFDE.getText()));
                    view.DELETEE.setText("Deleted");
                } else {
                    view.DELETEE.setText("Couldn't delete");
                }
            }
            //-------------------------------------------------------------------------------
            //                         VIEW EMPLOYEE
            else if (e.getSource() == view.EV) {
                List<String> ls = bb.viewEmployee();
                String[] columnName = {"ID", "Name", "CNP", "Salary"};
                DefaultTableModel model = new DefaultTableModel(columnName, 0);
                Object rowData[] = new Object[ls.size()];

                for (int i = 0; i < ls.size(); i++) {
                    String[] s = ls.get(i).split("\\s+");
                    rowData[0] = s[0];
                    rowData[1] = s[1];
                    rowData[2] = s[2];
                    rowData[3] = s[3];

                    model.addRow(rowData);
                }
                view.ETable.setModel(model);
            }
            //----------------------------------------------------------
            //                     GENERATE REPORT

            if(e.getSource() == view.GR){

                String empIdS = view.IDTFR.getText();
                int empId;

                if(bb.verEmployee(empIdS)){
                    empId = Integer.parseInt(empIdS);
                    int from;
                    int to;

                    String fromS = view.GRTF1.getText();
                    String toS = view.GRTF2.getText();

                    if(fromS.equals("") || toS.equals("")){
                        from = 101;
                        to = 1231;
                    }else{
                        from= Integer.parseInt(fromS);
                        to = Integer.parseInt(toS);
                    }

                    System.out.println(empId + " " + from + " " + to);

                    List<String> ls = bb.viewActivity(empId,from,to);

                    String[] columnName = {"ID", "Empployee ID", "Description", "Date"};
                    DefaultTableModel model = new DefaultTableModel(columnName, 0);
                    Object rowData[] = new Object[ls.size()];


                    for (int i = 0; i < ls.size(); i++) {
                        String[] s = ls.get(i).split("\\s+");

                        model.addRow(s);
                    }
                    view.GRTable.setModel(model);
                }else{
                    JOptionPane.showMessageDialog(null, "Employee doesn't exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        public String fixStrings(String s) {
            String sNew = s.replace(" ", "_");
            return sNew;
        }

        public void createAddActivity(int empId, String descr, int date){
            Activity a = new Activity( empId, descr,date);
            bb.addActivity(a);
        }
        public void createAddUtility(int idClient, String descr, int price){
            Utility u = new Utility(idClient, descr, price);
            bb.addUtility(u);
        }
    }
}
