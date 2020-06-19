package Server;

import Client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class Server extends Client {

    public static void main(String[] args) throws IOException
    {
        NotificationManager nm = new NotificationManager();

        try (ServerSocket socket = new ServerSocket(5050))
        {
            while (true)
            {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept(); //acepta requestul
                new ThreadServer(clientSocket,nm).start(); //new threadServer().start().
            }
        }
    }
}
