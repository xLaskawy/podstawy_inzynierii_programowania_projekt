package serwerKlient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client
{
    private static final String Server_IP="127.0.0.1";
    private static final int Server_port=6666;
    private static BufferedReader in;
    private static List<Integer> listap=new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        Socket socket=new Socket(Server_IP,Server_port);//utworzenie polaczenie z serverem
        ServerCon serverConn=new ServerCon(socket);
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        for(int i=0;i<15;i++)
        {
            listap.add(i);

        }
        new Thread(serverConn).start();

        while (true) {


            System.out.println(">");
            String command = keyboard.readLine();

            if (command.equals("quit")) break;


            out.println(command);


        }

        socket.close();
        System.exit(0);
    }

}
