package util.data.ticket;

public class Via
{

    private String channel;
    private Source source;
    private String vel;

    public Via(String channel, Source source, String vel)
    {
        this.channel = channel;
        this.source = source;
        this.vel = vel;
    }

    public Via()
    {

    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public Source getSource()
    {
        return source;
    }

    public void setSource(Source source)
    {
        this.source = source;
    }

    public String getVel()
    {
        return vel;
    }

    public void setVel(String vel)
    {
        this.vel = vel;
    }
}
