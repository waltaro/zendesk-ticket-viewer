import model.auth.Authenticator;

public class Main {

    public static void main(String[] args) {

        // Used to authenticate/talk to the Zendesk API for ticket retrieval
        Authenticator authenticator = new Authenticator();

        // TODO: add menu loop and stuff after I get this working!
        authenticator.getUserDetails();
        authenticator.login();

    }
}