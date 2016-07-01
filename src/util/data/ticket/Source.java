package util.data.ticket;

public class Source
{
    private int to;
    private From from;

    public Source(int to, From from)
    {
        this.to = to;
        this.from = from;
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
}
