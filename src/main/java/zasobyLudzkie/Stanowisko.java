package zasobyLudzkie;

public class Stanowisko
{
    private int id;
    private String nazwa;

    public Stanowisko(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public String toSql()
    {
        return id+",'"+nazwa+"'";
    }
}
