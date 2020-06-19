package Client;

import Controller.UserController;
import Server.Request;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReaderThread extends Thread {

    private ObjectInputStream input;
    private UserController userController;
    boolean ok = true;
    public ReaderThread(ObjectInputStream input,UserController userController){ // only user
        this.input = input;
        this.userController =userController;
    }

    @Override
    public void run() {
        while(ok){
            try {
                Request rsp = (Request) input.readObject();
                if(rsp.getT().equals(Request.TYPE.notificare)) {
                    userController.notificareView((String) rsp.getParams().get(0));
                }
                else if(rsp.getT().equals(Request.TYPE.notificareOnline)){
                    userController.notificareOnline((String) rsp.getParams().get(0),(String) rsp.getParams().get(1));
                }else{
                    switch(rsp.getT()){
                        case chat:
                            userController.chatReceiveR(rsp);
                            break;
                        case createChatSuccess:
                        case createChatFail:
                            userController.createChatReceiveR(rsp);
                            break;
                        case send:
                            userController.sendReceiveR(rsp);
                            break;
                        case setRsp:
                            userController.setRSPReceiveR(rsp);
                            break;
                        case setGsp:
                            userController.setGSPReceiveR(rsp);
                            break;
                        case star:
                            userController.starReceiveR(rsp);
                            break;
                        case block:
                            userController.blockReceiveR(rsp);
                            break;
                        case createGroup:
                            userController.createGroupReceiveR(rsp);
                            break;
                        case allowUser:
                            userController.allowUserReceiveR(rsp);
                            break;
                        case denyUser:
                            userController.denyUserReceiveR(rsp);
                            break;
                        default:
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOk(boolean val){
        ok = val;
    }

}
