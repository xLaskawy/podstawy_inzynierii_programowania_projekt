package serwerKlient;

import inne.Placowka;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerW
{
    Socket server;
    public static ArrayList<ClientH> clients=new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    Socket client;
    private static final int PORT=6666;
    public static Placowka p = new Placowka();
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ServerSocket listenner = new ServerSocket(PORT);

        while (true)
        {
            System.out.println("[S] oczekiwanie na polaczenie...");
            Socket client = listenner.accept();
            System.out.println("[S] polaczono!");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            ClientH clientThread = new ClientH(client, clients, objectOutputStream,is);
            clients.add(clientThread);
            System.out.println("nowy klient dodany!");
            pool.execute(clientThread);
        }
    }
}
