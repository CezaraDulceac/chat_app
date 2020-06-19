package Server;

import Model.Notificare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.List;

public class ThreadServer extends Thread{

    private final Socket clientSocket;
    NotificationManager nm;
    Helper help;

    public ThreadServer(Socket clientSocket, NotificationManager nm) {
        this.clientSocket = clientSocket;
        this.nm = nm;
    }
    ObjectOutputStream output = null;
    ObjectInputStream input = null;

    public void writeOutput(Object obj) throws IOException {
        output.writeObject(obj);
    }
    @Override
    public void run()
    {
        try
        {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());

            while (clientSocket.isConnected())  //cat mai am clienti conectati
            {

                boolean clientHasData = clientSocket.getInputStream().available() > 0;  //daca clientul trimite chestii
                if (clientHasData) {

                    Request rsp = (Request) input.readObject();
                    List<Object> params = rsp.getParams(); //aici am lista de parametrii
                    Request DataRaspuns = null;
                    switch (rsp.getT()){
                        case login:
                            String username = (String) params.get(0);
                            String password = (String) params.get(1);
                            DataRaspuns =  DataManager.login(username,password);

                            if(DataRaspuns.getT().equals(Request.TYPE.loginSuccess)){
                                help = new Helper(username, this);  //observerul meu
                                nm.addObserver(help);
                            }
                            output.writeObject(DataRaspuns);
                            break;
                        case createRoom:
                            String user1 = (String) params.get(0);
                            String user2 = (String) params.get(1);
                            DataRaspuns = DataManager.addRoom(user1,user2);
                            output.writeObject(DataRaspuns);
                            break;
                        case findRoomU:
                            String roomId = (String)params.get(0);
                            DataRaspuns =DataManager.findRoomU(roomId);
                            output.writeObject(DataRaspuns);
                            break;
                        case updateRoom:
                            String use1 = (String) params.get(0);
                            String use2 = (String) params.get(1);
                            DataRaspuns = DataManager.updateRoom(use1,use2);
                            output.writeObject(DataRaspuns);
                            break;
                        case findRoomD:
                            String roomIdD = (String)params.get(0);
                            DataRaspuns = DataManager.findRoomD(roomIdD);
                            output.writeObject(DataRaspuns);
                            break;
                        case deleteRoom:
                            DataRaspuns = DataManager.deleteRoomD();
                            output.writeObject(DataRaspuns);
                            break;
                        case viewRooms:
                            DataRaspuns = DataManager.viewRooms();
                            output.writeObject(DataRaspuns);
                            break;
                        case createUser:
                             username = (String) params.get(0);
                             password = (String) params.get(1);
                             DataRaspuns =  DataManager.createUser(username,password);
                             output.writeObject(DataRaspuns);
                            break;
                        case findUserU:
                            String id = (String) params.get(0);
                            DataRaspuns =  DataManager.findUserU(id);
                            output.writeObject(DataRaspuns);
                            break;
                        case updateUser:
                            username = (String) params.get(0);
                            password = (String) params.get(1);
                            DataRaspuns =  DataManager.updateUser(username,password);
                            output.writeObject(DataRaspuns);
                            break;
                        case findUserD:
                             id = (String) params.get(0);
                            DataRaspuns =  DataManager.findUserD(id);
                            output.writeObject(DataRaspuns);
                            break;
                        case deleteUser:
                            DataRaspuns = DataManager.deleteUser();
                            output.writeObject(DataRaspuns);
                            break;
                        case viewUsers:
                            DataRaspuns = DataManager.viewUsers();
                            output.writeObject(DataRaspuns);
                            break;
                        case generate:
                            String format = (String) params.get(0);
                            String location = (String) params.get(1);
                            DataRaspuns = DataManager.generate(format, location);
                            output.writeObject(DataRaspuns);
                            break;
                        case chat:

                            username = (String) params.get(0);
                            String name = (String) params.get(1);
                            String eu = (String) params.get(2);

                            nm.setValue(new Notificare(eu, ""));

                            DataRaspuns = DataManager.chat(username,name, eu);
                            output.writeObject(DataRaspuns);
                            break;
                        case createChat:
                            String username1 = (String) params.get(0);
                            String user =(String) params.get(1);
                            DataRaspuns = DataManager.createChat(username1, user);
                            output.writeObject(DataRaspuns);
                            break;
                        case send:
                            String mesaj = (String) params.get(0);
                            String eu1 = (String) params.get(1);
                            //String receiver = (String) params.get(2);
                            String roomName = (String) params.get(3);
                            String selector = (String) params.get(4);
                            //if((mesaj != null) && !mesaj.equals(""))
                                //nm.setValue(new Notificare(eu1, receiver));

                            DataRaspuns = DataManager.send(mesaj, eu1, roomName, selector);
                            output.writeObject(DataRaspuns);
                            break;
                        case setRsp:
                            String id2 = (String) params.get(0);
                            DataRaspuns = DataManager.setRsp(id2);
                            output.writeObject(DataRaspuns);
                            break;
                        case setGsp:
                            String idCon = (String) params.get(0);
                            DataRaspuns = DataManager.setGsp(idCon);
                            output.writeObject(DataRaspuns);
                            break;
                        case star:
                            String eu2 = (String) params.get(0);
                            String user3 = (String) params.get(1);
                            DataRaspuns = DataManager.star(eu2, user3);
                            output.writeObject(DataRaspuns);
                            break;
                        case block:
                            String eu21 = (String) params.get(0);
                            String user31 = (String) params.get(1);
                            DataRaspuns = DataManager.block(eu21, user31);
                            output.writeObject(DataRaspuns);
                            break;
                        case createGroup:
                            String euu = (String) params.get(0);
                            String numeGroup = (String) params.get(1);
                            String userr =  (String) params.get(2);
                            DataRaspuns = DataManager.createGroup(euu, numeGroup,userr);
                            output.writeObject(DataRaspuns);
                            break;
                        case allowUser:
                            String idEu =(String) params.get(0);
                            String wholeName =(String) params.get(1);
                            String UserName =(String) params.get(2);
                            DataRaspuns = DataManager.allowUser(idEu, wholeName,UserName);
                            output.writeObject(DataRaspuns);
                            break;
                        case denyUser:
                            String idEu1 =(String) params.get(0);
                            String wholeName1 =(String) params.get(1);
                            String UserName1 =(String) params.get(2);
                            DataRaspuns = DataManager.denyUser(idEu1, wholeName1,UserName1);
                            output.writeObject(DataRaspuns);
                            break;
                        default:
                    }

                    System.out.println(Instant.now() + " Sending response: ACK");
                }

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println(Instant.now() + " Client disconnected?");
        }
        catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
