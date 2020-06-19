package Controller;

import Model.User;

import Server.Request;

import View.IAdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    IAdminView adminView;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    List<Object> params;
    Request r;
    Request rsp;
    public AdminController(IAdminView am, ObjectInputStream input, ObjectOutputStream output){
        this.adminView = am;
        this.input = input;
        this.output = output;
    }

    //--------------------------------------------------------------------------------------------------------
    //                                      CHAT ROOMS

    public void createRoom() throws IOException, ClassNotFoundException {
        String user1 = adminView.getUser1();
        String user2 = adminView.getUser2();
        params = new ArrayList<Object>();
        params.add(user1);
        params.add(user2);
        r = new Request(Request.TYPE.createRoom, params);
        output.writeObject(r);

        rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void findRoomU() throws IOException, ClassNotFoundException{
        String roomId =  adminView.getRoomId();
        params = new ArrayList<Object>();
        params.add(roomId);

        Request r = new Request(Request.TYPE.findRoomU, params);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();

        if(rsp.getT().equals(Request.TYPE.findRoomUSuccess)){
            adminView.setUser1Update((String)rsp.getParams().get(0));
            adminView.setUser2Update((String)rsp.getParams().get(1));
            JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(2));
        }else if (rsp.getT().equals(Request.TYPE.findRoomUFail)){
            JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
        }
    }

    public void updateRoom() throws IOException, ClassNotFoundException {
        String user1 = adminView.getUser1Update();
        String user2 = adminView.getUser2Update();
        params = new ArrayList<Object>();
        params.add(user1);
        params.add(user2);

        Request r = new Request(Request.TYPE.updateRoom, params);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    int id = -1;
    int ok = 0;
    public void findRoomD() throws IOException, ClassNotFoundException {
        String roomId =  adminView.getDeleteRoomId();
        params = new ArrayList<Object>();
        params.add(roomId);

        Request r = new Request(Request.TYPE.findRoomD, params);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void deleteRoom() throws IOException, ClassNotFoundException {
        Request r = new Request(Request.TYPE.deleteRoom, null);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void viewRooms() throws IOException, ClassNotFoundException {

        Request r = new Request(Request.TYPE.viewRooms, null);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        DefaultTableModel model = (DefaultTableModel) rsp.getParams().get(0);
        adminView.getRoomsTable().setModel(model);
    }

    //--------------------------------------------------------------------------------------------------------
    //                                      CHAT USERS
    public void createUser() throws IOException, ClassNotFoundException {
        String username = adminView.getUsername();
        String password = adminView.getPassword();
        params = new ArrayList<Object>();
        params.add(username);
        params.add(password);
        r = new Request(Request.TYPE.createUser, params);
        output.writeObject(r);

        rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }


    User u = null;
    public void findUserU() throws IOException, ClassNotFoundException {
        String userId =  adminView.getUserId();
        params = new ArrayList<Object>();
        params.add(userId);
        Request r = new Request(Request.TYPE.findUserU, params);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        if(rsp.getT().equals(Request.TYPE.findUserUSuccess)){
            adminView.setUsernameUpdate((String)rsp.getParams().get(0));
            adminView.setPasswordUpdate((String)rsp.getParams().get(1));
            JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(2));
        }else if (rsp.getT().equals(Request.TYPE.findUserUFail)){
            JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
        }
    }

    public void updateUser() throws IOException, ClassNotFoundException {
        String username = adminView.getUsernameUpdate();
        String password = adminView.getPasswordUpdate();

        params = new ArrayList<Object>();
        params.add(username);
        params.add(password);
        Request r = new Request(Request.TYPE.updateUser, params);
        output.writeObject(r);
        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void findUserD() throws IOException, ClassNotFoundException {
        String userId =  adminView.getDeleteUserId();
        params = new ArrayList<Object>();
        params.add(userId);
        Request r = new Request(Request.TYPE.findUserD, params);
        output.writeObject(r);
        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void deleteUser() throws IOException, ClassNotFoundException {
        Request r = new Request(Request.TYPE.deleteUser, null);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

    public void viewUsers() throws IOException, ClassNotFoundException {

        Request r = new Request(Request.TYPE.viewUsers, null);
        output.writeObject(r);

        Request rsp = (Request) input.readObject();
        DefaultTableModel model = (DefaultTableModel) rsp.getParams().get(0);
        adminView.getUsersTable().setModel(model);
    }

    //--------------------------------------------------------------------------------------------------------
    //                                        REPORTS

    public static String getLocation(Component parent)
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
        {
            return fc.getSelectedFile().getAbsolutePath();
        }

        return null;
    }
    String location = null;
    public void choose() throws IOException, ClassNotFoundException {

        location = getLocation(null);
        adminView.setPathLabel(location);
    }

    public void generate() throws IOException, ClassNotFoundException {
        int getFormatBtn = adminView.getSelectedFormatButton();
        params = new ArrayList<Object>();
        params.add(Integer.toString(getFormatBtn));
        params.add(location);

        Request r = new Request(Request.TYPE.generate, params);
        output.writeObject(r);
        Request rsp = (Request) input.readObject();
        JOptionPane.showMessageDialog(adminView.getFrame(), (String)rsp.getParams().get(0));
    }

}
