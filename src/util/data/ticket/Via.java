package util.data.ticket;

public class Via
{

    private String channel;
    private Source source;

    public Via(String channel, Source source)
    {
        this.channel = channel;
        this.source = source;
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
}
