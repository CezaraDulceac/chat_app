package Client;

import Model.User;
import Server.Request;
import View.ILoginView;
import View.LoginView;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientTest {

    @Test
    void testLogin() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            Socket socket = serverSocket.accept();

            ObjectOutputStream   output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream  input = new ObjectInputStream(socket.getInputStream());

            ILoginView lv = mock(LoginView.class);
            when(lv.getUsername()).thenReturn("Cezara");
            when(lv.getPassword()).thenReturn("cezara");
            lv.getConteoller().login();

            Request r = (Request) input.readObject();
            assertTrue(r.getT().equals(Request.TYPE.login));
            assertTrue(r.getParams().get(0).equals("Cezara"));
            assertTrue(r.getParams().get(1).equals("cezara"));
            Request rsp = new Request(Request.TYPE.loginSuccess, null);
            User u = new User("Cezara","cezara");
            verify(lv).showRegularView(u);

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}