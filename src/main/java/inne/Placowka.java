package inne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Placowka
{
    /*
        clasa odpowiedzialna za osbloge z baza danych
     */
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:/D:\\Notatki\\Java IntelliJ\\inzynieria\\podstawy_inzynierii_programowania_projekt\\baza_danych\\bazadanych.db";

    private Connection conn;
    private Statement stat;

    public Placowka()
    {
        try
       {
            Class.forName(Placowka.DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        System.out.println("Wczytano sterownik baz danych");

        try
        {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        }
        catch (SQLException throwables)
        {
            System.err.println("Problem z otwarciem polaczenia");
            throwables.printStackTrace();
        }

        System.out.println("Połączono z bazą danych");

        stworzTabele();
    }

    public boolean stworzTabele()
    {
        ArrayList<String> sql = new ArrayList<>();
        sql.add("CREATE TABLE IF NOT EXISTS PRACE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS PRACE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS KURSY (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS STANOWISKA (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS PLACOWKA (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS UZYTKOWNIK (ID INTEGER PRIMARY KEY AUTOINCREMENT, LOGIN TEXT, HASLO TEXT)");
        sql.add("CREATE TABLE IF NOT EXISTS MAGAZYN (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_PLACOWKI INTEGER, NAZWA TEXT, ILOSC INTEGER,  FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(id))");
        sql.add("CREATE TABLE IF NOT EXISTS PRACOWNICY (ID INTEGER PRIMARY KEY AUTOINCREMENT,ID_PLACOWKI INTEGER, ID_UZYTKOWNIKA INTEGER, ID_STANOWISKA INTEGER, IMIE TEXT, NAZWISKO TEXT, FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(ID), FOREIGN KEY (ID_UZYTKOWNIKA) REFERENCES UZYTKOWNIK(ID), FOREIGN KEY (ID_STANOWISKA) REFERENCES STANOWISKA(ID))");
        sql.add("CREATE TABLE IF NOT EXISTS USTERKI (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_PRACOWNIKA INTEGER, ID_PLACOWKI INTEGER, TRASC TEXT, FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(id), FOREIGN KEY (ID_PRACOWNIKA) REFERENCES PRACOWNICY(id))");
        sql.add("CREATE TABLE IF NOT EXISTS ZAMOWIENIA (ID INTEGER PRIMARY KEY AUTOINCREMENT,ID_PRZEDMIOTU INTEGER, ID_PLACOWKI INTEGER, ID_PRACOWNIKA INTEGER, ILOSC INTEGER, MAGAZYN BOOLEAN, FOREIGN KEY (ID_PRZEDMIOTU) REFERENCES MAGAZYN(ID), FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(ID), FOREIGN KEY (ID_PRACOWNIKA) REFERENCES PRACOWNICY(ID) )");
        sql.add("CREATE TABLE IF NOT EXISTS WIEZNIOWIE (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_PRACY INTEGER, ID_KURSU INTEGER, ID_PLACOWKI INTEGER, IMIE TEXT, NAZWISKO TEXT, KLASA INTEGER, NUMER_CELI INTEGER, NUMER_IZOLATKI INTEGER, ODSIADKA_START TEXT, ODSIADKA_KONIEC TEXT, FOREIGN KEY (ID_PRACY) REFERENCES PRACE(ID), FOREIGN KEY (ID_KURSU) REFERENCES KURSY(ID), FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(ID) )");
        sql.add("CREATE TABLE IF NOT EXISTS LEKI (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_WIEZNIA INTEGER, NAZWA TEXT, OPIS_ZAZYCIA TEXT, FOREIGN KEY (ID_WIEZNIA) REFERENCES WIEZNIOWIE(ID))");
        sql.add("CREATE TABLE IF NOT EXISTS SKARGI (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_WIEZNIA INTEGER, ID_PRACOWNIKA INTEGER, ID_PLACOWKI INTEGER, TRESC TEXT, FOREIGN KEY (ID_WIEZNIA) REFERENCES WIEZNIOWIE(ID), FOREIGN KEY (ID_PRACOWNIKA) REFERENCES PRACOWNICY(ID), FOREIGN KEY (ID_PLACOWKI) REFERENCES PLACOWKA(ID))");
        sql.add("REPLACE INTO PRACE VALUES (0,'Brak') ");
        sql.add("REPLACE INTO KURSY VALUES (0,'Brak') ");
        sql.add("REPLACE INTO STANOWISKA VALUES (0,'Brak') ");
        sql.add("REPLACE INTO UZYTKOWNIK VALUES (0,'admin','admin') ");
        sql.add("REPLACE INTO PRACOWNICY VALUES (0, 0, 0, 0, 'admin', 'admin') ");
        sql.add("REPLACE INTO PLACOWKA(nazwa) VALUES ('Placowka w Kielcach') ");
        sql.add("REPLACE INTO PLACOWKA(nazwa) VALUES ('Placowka w Katowicach') ");

        try
        {
            for(int i=0; i<sql.size();i++)
            {
                stat.execute(sql.get(i));
            }

        }
        catch (SQLException throwables)
        {
            System.err.println("Błąd przy tworzeniu tabeli");
            throwables.printStackTrace();
            return false;
        }
        System.out.println("Stworzono tabele");

        return true;
    }

    public void test()
    {
        String sql = "";


        try {
            stat.execute(sql);
        } catch (SQLException e) {
            System.out.println("BLAD");
            e.printStackTrace();
        }
    }

    public void dodajPracownika(int idPlacowki)
    {
        /*
        pracownik - id idplacowki iduzytkownika imie nazwisko id stanowiska
        uzytkownik - id login haslo
         */
      //  sql.add("REPLACE INTO PLACOWKA(nazwa) VALUES ('Placowka w Katowicach') ");
        String sql = "INSERT INTO PRACOWNIK(id_placowki,id_uzytkownika,id_stanowiska,imie,nazwisko) VALUES ('"+idPlacowki+"')";
        try {
            stat.execute(sql);
        } catch (SQLException e) {
            System.out.println("BLAD");
            e.printStackTrace();
        }
    }


}
