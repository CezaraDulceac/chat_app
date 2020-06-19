package Controller;

import Model.User;
import Server.Request;

import View.ILoginView;


import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginController {

    private final ILoginView loginView;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public LoginController(ILoginView loginView, ObjectInputStream input, ObjectOutputStream output) {
        this.input = input;
        this.output = output;
        this.loginView = loginView;
    }

    public void login() throws IOException, ClassNotFoundException {
        if (loginView.getUsername().equals("admin")) {
            if (loginView.getPassword().equals("admin")) {
                loginView.showAdminView();
                loginView.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username and Password", "Try again!", JOptionPane.ERROR_MESSAGE);
            }
        }else{

            String username = loginView.getUsername();
            String password = loginView.getPassword();

            List<Object> params = new ArrayList<Object>();
            params.add(username);
            params.add(password);
            Request r = new Request(Request.TYPE.login, params);
            output.writeObject(r);

            Request rsp = (Request) input.readObject();
            User u = (User) rsp.getParams().get(0);
            if(rsp.getT().equals(Request.TYPE.loginSuccess)){
                 loginView.showRegularView(u); //cu parametru Userul meu
                 loginView.getIcon().setIcon(null);
                 loginView.clear();
            }else if (rsp.getT().equals(Request.TYPE.loginFail)){
                JOptionPane.showMessageDialog(null, (String)rsp.getParams().get(0), "Try again!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
