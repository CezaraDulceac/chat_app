package Controller;

import Client.ReaderThread;
import Model.Grup;
import Model.User;
import Server.Request;
import View.IUserView;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController {

    IUserView userView;
    String eu = null;
    String username = null; //numele celui cu care vorbesc
    String name = null; //numele grupului
    String ver = null;
    ReaderThread readerThread;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public UserController(IUserView userView,ObjectInputStream input, ObjectOutputStream output){ //aici fac un readerTherad - sa il inchid cand ies
        this.input = input;
        this.output = output;
        this.userView = userView;
        eu = userView.getUsername();

        readerThread = new  ReaderThread(input, this); //thread de verificare a tipului de request primit
        readerThread.start();
    }

    public void closeThreadReader(){
        readerThread.setOk(false); //inchide threadul
    }

    public void chatSendR() throws IOException {
        JList listR = userView.getRoomList();
        int idR = listR.getSelectedIndex();

        JList listG = userView.getGroupList();
        int idG = listG.getSelectedIndex();

        int selector;
        if(idR != -1){
            selector = 1;
        } else if(idG != -1){
            selector = 2;
        } else {
            selector = 3; //cumva is selectate ambele
        }

        if(selector == 1){ //for rooms    || username, name, eu
            ver = "unu";
            String aux = (String) listR.getSelectedValue();
            String ap[] = aux.split(" ");
            username = ap[0]; //username e ala cu care vreau eu sa vb
            List<Object> params = new ArrayList<Object>();
            params.add(username);
            params.add("nada");
            params.add(eu);
            Request r = new Request(Request.TYPE.chat, params);
            output.writeObject(r);

        }else if (selector == 2){ //pentru groups
            ver = "doi";
            String aux = (String) listG.getSelectedValue();
            String ap[] = aux.split(":");
            name = ap[0]; //numele camerei
            List<Object> params = new ArrayList<Object>();
            params.add("nada");
            params.add(name);
            params.add(eu);
            Request r = new Request(Request.TYPE.chat, params);
            output.writeObject(r);
        }
    }

    public void chatReceiveR(Request rsp) {
        if(!rsp.getParams().get(0).equals("nada")){
            User u = (User) rsp.getParams().get(0);
            userView.setUser2(username);
            userView.setStatus(u.getStatus());
            userView.switchPanels();
        }else if(!rsp.getParams().get(1).equals("nada")){
            Grup g = (Grup)rsp.getParams().get(1);
            userView.setUser2(g.getName());
            userView.switchPanels();
        }

        JList conv = (JList) rsp.getParams().get(2);
        userView.setConvList(conv);
        userView.setConversation(conv);
    }

    public void createChatSendR() throws IOException {
        String username = JOptionPane.showInputDialog(null,"Please insert username:");
        List<Object> params = new ArrayList<Object>();

        params.add(username);
        params.add(userView.getUsername());

        Request r = new Request(Request.TYPE.createChat, params);
        output.writeObject(r);
    }

    public void createChatReceiveR(Request rsp){

        if(rsp.getT().equals(Request.TYPE.createChatSuccess)){
            JList roomList = (JList) rsp.getParams().get(0);
            userView.setRoomList(roomList);
            userView.setRSP(roomList);
        }else if (rsp.getT().equals(Request.TYPE.createChatFail)){
            JOptionPane.showMessageDialog(userView.getFrame(), (String)rsp.getParams().get(0));
        }
    }

    public void createGroupSendR() throws IOException {
        String name = JOptionPane.showInputDialog(null,"Please insert Group name:");
        String user = JOptionPane.showInputDialog(null,"Please insert an user name:");
        List<Object> params = new ArrayList<Object>();

        params.add(userView.getUsername()); //eu camera user
        params.add(name);
        params.add(user);

        Request r = new Request(Request.TYPE.createGroup, params);
        output.writeObject(r);
    }

    public void createGroupReceiveR(Request rsp){

        if(rsp.getT().equals(Request.TYPE.createGroupSuccess)){
            JList roomList = (JList) rsp.getParams().get(0);
            userView.setGroupList(roomList);
            userView.setGSP(roomList);
        }else if (rsp.getT().equals(Request.TYPE.createGroupFail)){
            JOptionPane.showMessageDialog(userView.getFrame(), (String)rsp.getParams().get(0));
        }
    }

    public void sendSendR() throws IOException {
        String mesaj = userView.getSendTF();
        List<Object> params = new ArrayList<Object>();
        params.add(mesaj);
        params.add(eu);
        params.add(username);
        params.add(name);
        params.add(ver); //1 o sa fie room si 2 o sa fie grup

        Request r = new Request(Request.TYPE.send, params);
        output.writeObject(r);
    }

    public void sendReceiveR(Request rsp) {

        JList conv = (JList) rsp.getParams().get(0);
        userView.setConvList(conv);
        userView.setConversation(conv);

        userView.setSendTF();
    }

    public void setRSPSendR() throws IOException {

        int Id = userView.userId(); //id'ul userului conectat
        List<Object> params = new ArrayList<Object>();
        params.add(Integer.toString(Id));

        Request r = new Request(Request.TYPE.setRsp, params);
        output.writeObject(r);
    }

    public void setRSPReceiveR(Request rsp){

    JList roomList = (JList) rsp.getParams().get(0);
    userView.setRoomList(roomList);
    userView.setRSP(roomList);
    }

    public void setGSPSendR() throws IOException {

        int Id = userView.userId(); //id'ul userului conectat
        List<Object> params = new ArrayList<Object>();
        params.add(Integer.toString(Id));

        Request r = new Request(Request.TYPE.setGsp, params);
        output.writeObject(r);
    }

    public void setGSPReceiveR(Request rsp){
        JList roomList = (JList) rsp.getParams().get(0);
        userView.setGroupList(roomList);
        userView.setGSP(roomList);
    }


    public void starSendR() throws IOException {
        JList list = userView.getRoomList();
        int id = list.getSelectedIndex();
        String name = (String) list.getSelectedValue();
        String eu = userView.getUsername();
        if(id != -1) {
            List<Object> params = new ArrayList<Object>();
            params.add(eu);
            params.add(name);
            Request r = new Request(Request.TYPE.star, params);
            output.writeObject(r);
        }
    }

    public void starReceiveR(Request rsp){
        JList roomList = (JList) rsp.getParams().get(0);
        userView.setRoomList(roomList);
        userView.setRSP(roomList);
    }

    public void blockSendR() throws IOException {
        JList list = userView.getRoomList();
        int id = list.getSelectedIndex();
        String name = (String) list.getSelectedValue();
        String eu = userView.getUsername();
        if(id != -1) {
            List<Object> params = new ArrayList<Object>();
            params.add(eu);
            params.add(name);
            Request r = new Request(Request.TYPE.block, params);
            output.writeObject(r);
        }
    }

    public void blockReceiveR(Request rsp){
        JList roomList = (JList) rsp.getParams().get(0);
        userView.setRoomList(roomList);
        userView.setRSP(roomList);
    }

    public void allowUserSendR() throws IOException {
        String userName = JOptionPane.showInputDialog(null,"Please insert an User name:");
        JList list = userView.getGroupList();
        int id = list.getSelectedIndex();
        String name = (String) list.getSelectedValue(); //tot numele cu :(-)
        String eu = userView.getUsername();

        if(id != -1) {
            List<Object> params = new ArrayList<Object>();
            params.add(eu);
            params.add(name);
            params.add(userName);
            Request r = new Request(Request.TYPE.allowUser, params);
            output.writeObject(r);
        }
    }

    public void allowUserReceiveR(Request rsp){
        JOptionPane.showMessageDialog(userView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void denyUserSendR() throws IOException {
        String userName = JOptionPane.showInputDialog(null,"Please insert an User to block:");
        JList list = userView.getGroupList();
        int id = list.getSelectedIndex();
        String name = (String) list.getSelectedValue(); //tot numele cu :(-)
        String eu = userView.getUsername();

        if(id != -1) {
            List<Object> params = new ArrayList<Object>();
            params.add(eu);
            params.add(name);
            params.add(userName);
            Request r = new Request(Request.TYPE.denyUser, params);
            output.writeObject(r);
        }
    }

    public void denyUserReceiveR(Request rsp){
        JOptionPane.showMessageDialog(userView.getFrame(), (String)rsp.getParams().get(0));
    }


    public void notificareView(String username){
        if(username.equals(this.username)){
            String mesaj = "";
            List<Object> params = new ArrayList<Object>();
            params.add(mesaj);
            params.add(eu);
            params.add(username);

            Request r = new Request(Request.TYPE.send, params);
            try {
                output.writeObject(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(userView.getFrame(), "Notificare mesaj de la " + username);
        }
    }

    public void notificareOnline(String username, String eu){
        if(username.equals(this.username)  && eu.equals(this.eu)){  //daca sunt in conversatie eu cu username

            List<Object> params = new ArrayList<Object>();
            params.add(username);
            JOptionPane.showMessageDialog(userView.getFrame(), "User " + username + " is here");
        }
    }

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }



}
