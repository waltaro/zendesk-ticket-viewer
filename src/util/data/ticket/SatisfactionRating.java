package util.data.ticket;

public class SatisfactionRating
{
    private int id;
    private String score;
    private String comment;

    public SatisfactionRating(int id, String score, String comment)
    {
        this.id = id;
        this.score = score;
        this.comment = comment;
    }

    public SatisfactionRating()
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

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
