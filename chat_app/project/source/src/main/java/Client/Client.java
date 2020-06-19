package Client;

import View.ILoginView;
import View.LoginView;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5050);
        try {

            ObjectInputStream  input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream   output = new ObjectOutputStream(socket.getOutputStream());

            ILoginView lv = new LoginView(input, output);

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());//NOPMD

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

