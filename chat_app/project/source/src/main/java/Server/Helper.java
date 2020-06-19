package Server;

import Model.Notificare;

import java.io.IOException;
import java.util.*;

public class Helper implements Observer {

    private String username;
    private ThreadServer thread;

    public Helper(String username, ThreadServer thread) {
        this.username = username;
        this.thread = thread;
    }

    @Override
    public void update(Observable o, Object arg) {
        Notificare n = (Notificare) arg;
        if(n.getReceiver().equals(username)){
            try {
                thread.writeOutput(new Request(Request.TYPE.notificare, Collections.singletonList(n.getSender())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(n.getReceiver().equals("")){  //pentru cand intra
            try {
                List<Object> params = new ArrayList<Object>();
                params.add(n.getSender());
                params.add(n.getReceiver());
                thread.writeOutput(new Request(Request.TYPE.notificareOnline, params));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
