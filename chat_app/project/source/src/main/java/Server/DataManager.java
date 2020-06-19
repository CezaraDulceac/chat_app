package Server;

import Model.Grup;
import Model.Room;
import Model.User;
import ModelProviders.*;
import Reports.IReport;
import Reports.ReportHtml;
import Reports.ReportPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class DataManager {
    public static IMessageProvider messageProvider = new MessageProvider();
    public static IRoomProvider roomProvider = new RoomProvider();
    public static IUserProvider userProvider = new UserProvider();
    public static IGroupProvider groupProvider = new GroupProvider();
    private static Room room;
    private static User user;
    private static int ok = 0;
    private static int id = -1;
    private static int okD = 0;
    private static int idD = -1;
    private static int rId = -1;


    public static Request login(String username, String password){
        if(userProvider.verifyUserByUsername(username)){
                if(userProvider.verifyUserAndPass(username,password)){
                    User u = userProvider.findUserByUsername(username);
                    List<Object> params = new ArrayList<Object>();
                    params.add(u);
                    return new Request(Request.TYPE.loginSuccess, params);
                }else{
                    return new Request(Request.TYPE.loginFail, Collections.singletonList("Wrong password"));
                }
            }else {
                 return new Request(Request.TYPE.loginFail, Collections.singletonList("Invalid username"));
            }
    }

    public static Request addRoom(String user1, String user2){
           if(verifyNumber(user1) && verifyNumber(user2) && !user1.equals(user2)) {
            int u1 = Integer.parseInt(user1);
            int u2 = Integer.parseInt(user2);
            if(userProvider.verifyUser(u1) && userProvider.verifyUser(u2)){ 
                if (!roomProvider.verifyRoomByUsers(u1, u2)) {
                        roomProvider.addRoom(u1, u2, getDate());
                        return new Request(Request.TYPE.createRoomSuccess, Collections.singletonList("Success created Room"));
                } else {
                    return new Request(Request.TYPE.createRoomFail, Collections.singletonList("Room already exists or it is blocked!"));
                }
             }else{
                return new Request(Request.TYPE.createRoomFail, Collections.singletonList("Inexistent user"));
            }
        }else{
               return new Request(Request.TYPE.createRoomFail, Collections.singletonList("Invalid users ID"));
        }
    }

    public static Request findRoomU(String roomId){
        int id = -1;
        List<Object> params = new ArrayList<Object>();
        if(verifyNumber(roomId)){ //daca e numar bun
            id = Integer.parseInt(roomId);
            System.out.println(id);
            if(roomProvider.verifyRoom(id)){
                room = roomProvider.findRoomById(id);  //gaseste camera existenta

                params.add(Integer.toString(room.getUser1()));
                params.add(Integer.toString(room.getUser2()));
                params.add((String)"Room Found");
                return new Request(Request.TYPE.findRoomUSuccess, params);
            }else{
                return new Request(Request.TYPE.findRoomUFail, Collections.singletonList("Room doesn't exists"));
            }
        }else{
            return new Request(Request.TYPE.findRoomUFail,  Collections.singletonList("Invalid input"));
        }
    }

    public static Request updateRoom(String user1, String user2){
        if(verifyNumber(user1) && verifyNumber(user2) && !user1.equals(user2)) {
            int u1 = Integer.parseInt(user1);
            int u2 = Integer.parseInt(user2);
            if(userProvider.verifyUser(u1) && userProvider.verifyUser(u2)) { //daca exista cei 2 useri exista, se poate updata camera
                if (!roomProvider.verifyRoomByUsers(u1, u2)) { //daca exista camera deja nu pot face update, inclusiv daca nu schimb nimic, i cant
                     roomProvider.updateRoomById(room.getId(),u1,u2);
                    return new Request(Request.TYPE.updateRoomSuccess, Collections.singletonList("Room updated"));
                }else{
                    return new Request(Request.TYPE.updateRoomFail, Collections.singletonList("Same room / room exists already"));
                }
            }else{
                return new Request(Request.TYPE.updateRoomFail, Collections.singletonList("Inexistent user"));
            }
        }else{
            return new Request(Request.TYPE.updateRoomFail, Collections.singletonList("Invalid users ID"));
        }
    }

    public static Request findRoomD(String roomId){

        if(verifyNumber(roomId)){ //daca e numar bun
            id = Integer.parseInt(roomId);
            if(roomProvider.verifyRoom(id)){
                ok = 1;
                return new Request(Request.TYPE.findRoomDSuccess, Collections.singletonList("Found"));
            }else{
                return new Request(Request.TYPE.findRoomDFail, Collections.singletonList("Room doesn't exists"));
            }
        }else{
            return new Request(Request.TYPE.findRoomDFail, Collections.singletonList("Invalid Room ID"));
        }
    }

    public static Request deleteRoomD(){
        if(ok == 1){
            roomProvider.deleteRoomById(id);
            return new Request(Request.TYPE.findRoomDSuccess, Collections.singletonList("Deleted"));
        }else{
            return new Request(Request.TYPE.findRoomDSuccess, Collections.singletonList("not Deleted"));
        }
    }

    public static Request viewRooms (){
        List<String> ls = roomProvider.view();
        String[] columnName = {"Id", "User1", "User2", "Creation Date", "Star"};
        DefaultTableModel model = new DefaultTableModel(columnName, 0);
        Object rowData[] = new Object[ls.size()];

        for (int i = 0; i < ls.size(); i++) {
            String[] s = ls.get(i).split("\\s+");
            model.addRow(s);
        }
        List<Object> params = new ArrayList<Object>();
        params.add(model);
        return new Request(Request.TYPE.viewRooms, params);
    }

    public static Request createUser(String username, String password){
        if (verString(username) && verString(password)) {
            if (!userProvider.verifyUserByUsername(username)) {  //daca exista deja acel username
                userProvider.addUser(username, password);
                return new Request(Request.TYPE.createUserSuccess, Collections.singletonList("User added successfully"));
            } else {
                return new Request(Request.TYPE.createUserFail, Collections.singletonList("Username already exists"));
            }
        } else {
            return new Request(Request.TYPE.createUserFail, Collections.singletonList("Empty fields"));
        }
    }

    public static Request findUserU(String userId){
        int id = -1;
        List<Object> params = new ArrayList<Object>();
        if(verifyNumber(userId)){ //daca e numar bun
            id = Integer.parseInt(userId);
            if(userProvider.verifyUser(id)){
                user = userProvider.findUserById(id);

                params.add(user.getUsername());
                params.add(user.getPass());
                params.add((String)"User Found");
                return new Request(Request.TYPE.findUserUSuccess, params);
            }else{
                return new Request(Request.TYPE.findUserUFail, Collections.singletonList("User doesn't exists"));
            }
        }else{
            return new Request(Request.TYPE.findUserUFail, Collections.singletonList("Invalid User ID"));
        }
    }

    public static Request updateUser(String username, String password){
        if (verString(username) && verString(password)) {
            User s = userProvider.findUserById(user.getId());
            if (!userProvider.verifyUserByUsername(username) || !((s.getUsername().equals(username) && (s.getPass().equals(password))))) {
                userProvider.updateUserById(user.getId(), username, password);
                return new Request(Request.TYPE.updateUserSuccess, Collections.singletonList("User updated"));
            } else {
                return new Request(Request.TYPE.updateUserFail, Collections.singletonList("Username already exists / no changes made"));
            }
        }else{
            return new Request(Request.TYPE.updateUserFail, Collections.singletonList("Empty fields"));
        }
    }

    public static Request findUserD(String userId){

        if(verifyNumber(userId)){ //daca e numar bun
            idD = Integer.parseInt(userId);
            if(userProvider.verifyUser(idD)){
                okD = 1;
                return new Request(Request.TYPE.findUserDSuccess, Collections.singletonList("Found"));
            }else{
                return new Request(Request.TYPE.findUserDFail, Collections.singletonList("User doesn't exists"));
            }
        }else{
            return new Request(Request.TYPE.findUserDFail, Collections.singletonList("Invalid room ID"));
        }
    }

    public static Request deleteUser(){
        if(okD == 1){
            userProvider.deleteUserById(idD);
            return new Request(Request.TYPE.deleteUserSuccess, Collections.singletonList("Deleted"));
        }else{
            return new Request(Request.TYPE.deleteUserFail, Collections.singletonList("not Deleted"));
        }
    }

    public static Request viewUsers(){

        List<String> ls = userProvider.view();
        String[] columnName = {"Id", "Username", "Password", "status"};
        DefaultTableModel model = new DefaultTableModel(columnName, 0);
        Object rowData[] = new Object[ls.size()];

        for (int i = 0; i < ls.size(); i++) {
            String[] s = ls.get(i).split("\\s+");
            model.addRow(s);
        }
        List<Object> params = new ArrayList<Object>();
        params.add(model);
        return new Request(Request.TYPE.viewUsers,params);
    }

    public static Request generate(String formBtn, String location){
        int formatBtn = Integer.parseInt(formBtn);
        if(formatBtn == 2){ //pdf
            IReport rep = new ReportHtml(location);
            rep.generateRaport();
            return new Request(Request.TYPE.generate,Collections.singletonList("PDF report generated"));
        }else if(formatBtn == 1){ //html
            IReport rep = new ReportPDF(location);
            rep.generateRaport();
            return new Request(Request.TYPE.generate,Collections.singletonList("HTML report generated"));
        }
        return new Request(Request.TYPE.generate,Collections.singletonList("generation fail"));
    }



    //------------------------------------------------------
    public static Request chat(String username, String name, String eu){
        if(!username.equals("nada")){

            User user = userProvider.findUserByUsername(username); //aici ar trb ceva gen findGroupbyName
            List<Object> params = new ArrayList<Object>();
            params.add(user);
            params.add("nada");
            User me = userProvider.findUserByUsername(eu);

            rId = roomProvider.findRoomIdByUsernames(me.getId(),user.getId());

            DefaultListModel model1 =  new DefaultListModel();
            List<String> ConvId = messageProvider.getAllMessagesByRoom(rId); //iau toate converstaiile cu omu asta
            for(int i = 0; i < ConvId.size(); i++){
                String[] s = ConvId.get(i).split("\\s+");

                int id11 = Integer.parseInt(s[0]);
                String nume =  userProvider.getUsernameById(id11);

                s[0] = nume;
                String str = String.join(" ", s);
                model1.addElement(str);
            }
            JList conv = new JList(model1);
            conv.setSelectedIndex(conv.getSelectedIndex());
            conv.setFont(new Font("Sylfaen", Font.PLAIN, 25));
            params.add(conv);

            return new Request(Request.TYPE.chat,params);

        }else if(!name.equals("nada")){
            List<Object> params = new ArrayList<Object>();
            User me = userProvider.findUserByUsername(eu);
            System.out.println(name);
            System.out.println(me.getId());
            Grup gr = groupProvider.findGroupByNameAndUser(name, me.getId()); //sau aici daca nu merge caut numai dupa nume
            params.add("nada");
            params.add(gr);
            DefaultListModel model1 =  new DefaultListModel();
            List<String> ConvId = messageProvider.getAllMessagesByGroup(gr.getId()); //iau toate mesajele din grup
            if(ConvId != null){
                for(int i = 0; i < ConvId.size(); i++){
                    String[] s = ConvId.get(i).split("\\s+");

                    int id11 = Integer.parseInt(s[0]);
                    String nume =  userProvider.getUsernameById(id11);

                    s[0] = nume;
                    String str = String.join(" ", s);
                    model1.addElement(str);
                }
            }
            JList conv = new JList(model1);
            conv.setSelectedIndex(conv.getSelectedIndex());
            conv.setFont(new Font("Sylfaen", Font.PLAIN, 25));
            params.add(conv);

            return new Request(Request.TYPE.chat,params);
        }
        return null;
    }

    public static Request createChat(String username, String user) {

        List<Object> params = new ArrayList<Object>();

        if (userProvider.verifyUserByUsername(username)) {

            User u1 = userProvider.findUserByUsername(user);
            User u2 = userProvider.findUserByUsername(username);

            if (!(roomProvider.verifyRoomByUsers(u1.getId(), u2.getId()))) { //daca camera nu exista
                roomProvider.addRoom(u1.getId(), u2.getId(), getDate());
                JOptionPane.showMessageDialog(null, "Room created successfully");


                DefaultListModel model =  new DefaultListModel();
                List<String> RoomIdsStar = roomProvider.getAllRoomsByUserStar(u1.getId(), 1); //iau toate camerele cu star
                for(int i = 0; i < RoomIdsStar.size(); i++){
                    int aux =  Integer.parseInt(RoomIdsStar.get(i));
                    String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
                    String newName = name + "   *";
                    model.addElement(newName);
                }

                List<String> RoomIdsNoStar = roomProvider.getAllRoomsByUserStar(u1.getId(), 0); //iau toate camerele cu star
                for(int i = 0; i < RoomIdsNoStar.size(); i++){
                    int aux =  Integer.parseInt(RoomIdsNoStar.get(i));
                    String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
                    model.addElement(name);
                }
                JList roomList = new JList(model);
                roomList.setSelectedIndex(roomList.getSelectedIndex());
                roomList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
                params.add(roomList);
                return new Request(Request.TYPE.createChatSuccess,params);
            } else {
                return new Request(Request.TYPE.createChatFail,Collections.singletonList("Room already exists or it is Blocked!"));
            }
        } else {
            return new Request(Request.TYPE.createChatFail,Collections.singletonList("User invalid. Try again"));
        }
    }

    public static Request createGroup(String eu, String numeGroup, String user) {

        List<Object> params = new ArrayList<Object>();

        if (userProvider.verifyUserByUsername(user)) {

            User u1 = userProvider.findUserByUsername(user);
            User u2 = userProvider.findUserByUsername(eu);

            if (!(groupProvider.verifyGroupByName(numeGroup))) { //daca grupul nu exista
                groupProvider.addGroup(u2.getId(), u1.getId(), numeGroup);
                JOptionPane.showMessageDialog(null, "Group created successfully");

                DefaultListModel model =  new DefaultListModel();

                List<Grup> grups = groupProvider.getAllGroupsByUser(u2.getId());

                for(int i = 0; i < grups.size(); i++){
                    String name = grups.get(i).getName(); //numele grupului
                    String owner = (userProvider.findUserById(grups.get(i).getOwnerId())).getUsername(); //numele ownerului
                    String body = name + ":("+owner+")";
                    System.out.println(body);
                    model.addElement(body);
                }

                JList roomList = new JList(model);
                roomList.setSelectedIndex(roomList.getSelectedIndex());
                roomList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
                params.add(roomList);
                return new Request(Request.TYPE.createGroupSuccess,params);
            } else {
                return new Request(Request.TYPE.createGroupFail,Collections.singletonList("Group name already exists"));
            }
        } else {
            return new Request(Request.TYPE.createGroupFail,Collections.singletonList("User invalid. Try again"));
        }
    }

    public static Request send(String mesaj, String eu, String grupName, String selector){
        List<Object> params = new ArrayList<Object>();
        User u = userProvider.findUserByUsername(eu);

        DefaultListModel model1 = new DefaultListModel();
        if(selector.equals("unu")){
            if(!mesaj.equals("")) {
                messageProvider.addMess(u.getId(), rId, -1, mesaj, getDate());
            }
            List<String> ConvId = messageProvider.getAllMessagesByRoom(rId); //iau toate converstaiile cu omu asta
            for (int i = 0; i < ConvId.size(); i++) {
                String[] s = ConvId.get(i).split("\\s+");

                int id = Integer.parseInt(s[0]);
                String name =  userProvider.getUsernameById(id);

                s[0] = name;
                String str = String.join(" ", s);
                model1.addElement(str);
            }
        }else if(!mesaj.equals("doi")){
            Grup g = groupProvider.findGroupByName(grupName);
            if(!mesaj.equals("")) {
                messageProvider.addMess(u.getId(), -1, g.getId(), mesaj, getDate());
            }
            List<String> ConvId = messageProvider.getAllMessagesByGroup(g.getId());
            for (int i = 0; i < ConvId.size(); i++) {
                String[] s = ConvId.get(i).split("\\s+");

                int id = Integer.parseInt(s[0]);
                String name =  userProvider.getUsernameById(id);

                s[0] = name;
                String str = String.join(" ", s);
                model1.addElement(str);
            }
        }

        JList conv = new JList(model1);
        conv.setSelectedIndex(conv.getSelectedIndex());
        conv.setFont(new Font("Sylfaen", Font.PLAIN, 25));

        params.add(conv);
        return new Request(Request.TYPE.send,params);
    }


    public static Request setRsp(String id){

        List<Object> params = new ArrayList<Object>();
        int Id = Integer.parseInt(id);
        DefaultListModel model =  new DefaultListModel();

        List<String> RoomIdsStar = roomProvider.getAllRoomsByUserBlockAndNoStar(Id, 1,1); //iau toate camerele cu star
        for(int i = 0; i < RoomIdsStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            String newName = name + "   *";
            System.out.println(newName);
            model.addElement(newName);
        }
        List<String> RoomIdsNoStar = roomProvider.getAllRoomsByUserBlockAndNoStar(Id, 1,0); //iau toate camerele fara star
        for(int i = 0; i < RoomIdsNoStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsNoStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            System.out.println(name);
            model.addElement(name);
        }
        JList roomList;
        roomList = new JList(model);
        roomList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        roomList.setSelectedIndex(roomList.getSelectedIndex());
        params.add(roomList);
        return new Request(Request.TYPE.setRsp,params);
    }

    public static Request setGsp(String id){

        List<Object> params = new ArrayList<Object>();
        int Id = Integer.parseInt(id);
        DefaultListModel model =  new DefaultListModel();

        List<Grup> grups = groupProvider.getAllGroupsByUser(Id);

        for(int i = 0; i < grups.size(); i++){
            String name = grups.get(i).getName(); //numele grupului
            String owner = (userProvider.findUserById(grups.get(i).getOwnerId())).getUsername(); //numele ownerului
            String body = name + ":("+owner+")";
            model.addElement(body);
        }
        JList groupList;
        groupList = new JList(model);
        groupList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        groupList.setSelectedIndex(groupList.getSelectedIndex());
        params.add(groupList);
        return new Request(Request.TYPE.setGsp,params);
    }

    public static Request star(String eu, String user){

        User euu = userProvider.findUserByUsername(eu);
        User userSel = userProvider.findUserByUsername(user);
        int idRoom = roomProvider.findRoomIdByUsernames(euu.getId(),userSel.getId());
        Room r = roomProvider.findRoomById(idRoom);
        roomProvider.setRoomStar(r.getId(), 1);  //am setat camera cu fav

        List<Object> params = new ArrayList<Object>();
        DefaultListModel model =  new DefaultListModel();
        List<String> RoomIdsStar = roomProvider.getAllRoomsByUserStar(euu.getId(), 1); //iau toate camerele cu star
        for(int i = 0; i < RoomIdsStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            String newName = name + "   *";
            model.addElement(newName);
        }

        List<String> RoomIdsNoStar = roomProvider.getAllRoomsByUserStar(euu.getId(), 0); //iau toate camerele fara star
        for(int i = 0; i < RoomIdsNoStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsNoStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            model.addElement(name);
        }

        JList roomList;
        roomList = new JList(model);
        roomList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        roomList.setSelectedIndex(roomList.getSelectedIndex());
        params.add(roomList);
        return new Request(Request.TYPE.star,params);
    }

    public static Request block(String eu, String user){

        User euu = userProvider.findUserByUsername(eu);
        User userSel = userProvider.findUserByUsername(user);
        int idRoom = roomProvider.findRoomIdByUsernames(euu.getId(),userSel.getId());
        Room r = roomProvider.findRoomById(idRoom);

        roomProvider.setRoomBlock(r.getId(), 0);  //setez conversatia ca conversatie blocata


        List<Object> params = new ArrayList<Object>();
        DefaultListModel model =  new DefaultListModel();

        List<String> RoomIdsStar = roomProvider.getAllRoomsByUserBlockAndNoStar(euu.getId(), 1,1); //iau toate camerele cu star
        for(int i = 0; i < RoomIdsStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            String newName = name + "   *";
            System.out.println(newName);
            model.addElement(newName);
        }
        List<String> RoomIdsNoStar = roomProvider.getAllRoomsByUserBlockAndNoStar(euu.getId(), 1,0); //iau toate camerele fara star
        for(int i = 0; i < RoomIdsNoStar.size(); i++){
            int aux =  Integer.parseInt(RoomIdsNoStar.get(i));
            String name = userProvider.getUsernameById(aux); //imi da usernameul celui cu care am eu conversatia
            System.out.println(name);
            model.addElement(name);
        }

        JList roomList;
        roomList = new JList(model);
        roomList.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        roomList.setSelectedIndex(roomList.getSelectedIndex());
        params.add(roomList);
        return new Request(Request.TYPE.block,params);
    }

    public static Request allowUser(String Eu, String wholeName,String UserName){
        if(userProvider.verifyUserByUsername(UserName)){
            String name1[] = wholeName.split(":");
            String name = name1[0];
           Grup g =  groupProvider.findGroupByName(name);
           User u = userProvider.findUserByUsername(Eu);
           User user = userProvider.findUserByUsername(UserName);

           if(g.getOwnerId() == u.getId()){ // daca sunt admin la acel grup
               if(!groupProvider.verifuUserInRoomByIds(g.getId(), user.getId())){
                   groupProvider.addGroup(g.getOwnerId(),user.getId(),g.getName());
                   return new Request(Request.TYPE.allowUser,Collections.singletonList("User added with success"));
               }else {
                   return new Request(Request.TYPE.allowUser,Collections.singletonList("User already in this Group"));
               }
           }else
           {
               return new Request(Request.TYPE.allowUser,Collections.singletonList("You're not the owner of this Group"));
           }
        }else{
            return new Request(Request.TYPE.allowUser,Collections.singletonList("User invalid. Try again"));
        }
    }

    public static Request denyUser(String Eu, String wholeName,String UserName){
        if(userProvider.verifyUserByUsername(UserName)){
            String name1[] = wholeName.split(":");
            String name = name1[0];
            Grup g =  groupProvider.findGroupByName(name);
            User u = userProvider.findUserByUsername(Eu);
            User user = userProvider.findUserByUsername(UserName);

            if(g.getOwnerId() == u.getId()){ // daca sunt admin la acel grup
                if(!groupProvider.verifuUserInRoomByIds(g.getId(), user.getId())){
                    groupProvider.denyUser(g.getOwnerId(),user.getId());
                    return new Request(Request.TYPE.denyUser,Collections.singletonList("User denied with success"));
                }else {
                    return new Request(Request.TYPE.denyUser,Collections.singletonList("User already in this Group"));
                }
            }else
            {
                return new Request(Request.TYPE.denyUser,Collections.singletonList("You're not the owner of this Group"));
            }
        }else{
            return new Request(Request.TYPE.denyUser,Collections.singletonList("User invalid. Try again"));
        }
    }

    public static boolean verifyNumber(String s){
        String regex = "[0-9]+";
        if(s.matches(regex)) return true;
        return false;
    }

    public static boolean verString(String a){
        if(a.equals("")) return false;
        return true;
    }

    public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
