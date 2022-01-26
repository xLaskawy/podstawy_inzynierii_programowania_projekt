package serwerKlient;

import inne.Placowka;
import zasobyLudzkie.Pracownik;
import zasobyLudzkie.Stanowisko;
import zasobyLudzkie.Uzytkownik;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static serwerKlient.ServerW.p;

public class ClientH implements Runnable
{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientH> clients;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream is;


    public ClientH(Socket clientSocket, ArrayList<ClientH> clients,ObjectOutputStream outStream,ObjectInputStream inStream) throws IOException, ClassNotFoundException
    {
        this.clients=clients;
        this.client=clientSocket;
        in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        out=new PrintWriter(client.getOutputStream(),true);
        this.objectOutputStream=outStream;
        this.is=inStream;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String request = in.readLine();
                //   String command=keyboard.readLine();
                if(request.startsWith("say"))
                {
                    int firtsSpace=request.indexOf(" ");
                    if(firtsSpace!=-1)
                    {
                        outToAll(request.substring(firtsSpace+1));
                    }
                }

                else if(request.contains("dodajPracownika"))
                {

                    String imie,nazwisko;
                    Stanowisko stanowisko;
                    out.println("Podaj imie : ");
                    imie = in.readLine();
                    out.println("Podaj nazwisko : ");
                    nazwisko = in.readLine();
                    out.println("Wybierz stanowisko : ");
                    out.println(p.selectStanowiska());
                    stanowisko = p.selectStanowiska().get(in.read());





                }


            }
        }catch (IOException e) {
            System.err.println("IOX ");
            System.err.println(e.getStackTrace());

        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("IOX close ");

            }
        }
    }
    private void outToAll(String msg){
        for(ClientH aClients:clients){
            aClients.out.println(msg);
        }
    }


}
