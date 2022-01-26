package zasobyLudzkie;

public class Uzytkownik
{
    private int id;
    private String login,haslo;

    public Uzytkownik(int id, String login, String haslo)
    {
        this.id = id;
        this.login = login;
        this.haslo = haslo;
    }

    public String toSql()
    {
        return id+",'"+login+"','"+haslo+"'";
    }

}
