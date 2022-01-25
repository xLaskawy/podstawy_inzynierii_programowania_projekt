package serwerKlient;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ServerCon implements Runnable
{
    private Socket server;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Scanner scann=new Scanner(System.in);

    public ServerCon(Socket s) throws IOException
    {
        server =s;
        out=new PrintWriter(server.getOutputStream(),true);
        in=new BufferedReader(new InputStreamReader(server.getInputStream()));
        objectOutputStream=new ObjectOutputStream(server.getOutputStream());
        objectInputStream=new ObjectInputStream(server.getInputStream());
    }
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                while (true)
                {
                    String serverResponse = in.readLine();

                    if (serverResponse.equals("zakonczono"))
                    {
                        System.out.println("All messages:");
                    }
                    System.out.println("[S]>> " + serverResponse);
                    if (serverResponse == "koniec") break;
                }
            }
            catch (IOException e) {e.printStackTrace();} finally
            {
                try {in.close();}
                catch (IOException e) {e.printStackTrace();}
            }
        }
    }

}
