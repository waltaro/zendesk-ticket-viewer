package util.data.ticket;

public class Source
{
    private int to;
    private From from;
    private String rel;

    public Source(int to, From from, String rel)
    {
        this.to = to;
        this.from = from;
        this.rel = rel;
    }

    public Source()
    {

    }

    public int getTo()
    {
        return to;
    }

    public void setTo(int to)
    {
        this.to = to;
    }

    public From getFrom()
    {
        return from;
    }

    public void setFrom(From from)
    {
        this.from = from;
    }

    public String getRel()
    {
        return rel;
    }

    public void setRel(String rel)
    {
        this.rel = rel;
    }
}
