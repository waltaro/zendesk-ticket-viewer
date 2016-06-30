package util.data.user;

public class Account
{
    private String username, password, encryptedAccountDetails;

    // Empty constructor
    public Account()
    {

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEncryptedAccountDetails()
    {
        return encryptedAccountDetails;
    }

    public void setEncryptedAccountDetails(String encryptedAccountDetails)
    {
        this.encryptedAccountDetails = encryptedAccountDetails;
    }
}
