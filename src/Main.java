import model.auth.Authenticator;
import view.InputHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        // Used to authenticate/talk to the Zendesk API for ticket retrieval
        Authenticator authenticator = new Authenticator();
        InputHandler input = new InputHandler();

        // Welcome the user to the program
        input.printWelcomeMessage();

        // Begin of menu loop
        while(!input.isQuit()) {
            input.printMenu();
            input.getMenuChoice(authenticator);
        }
    }
}