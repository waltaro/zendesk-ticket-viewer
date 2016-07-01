package util.data.ticket;

public class From
{

    private int id;
    private String title;

    public From(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public From()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
