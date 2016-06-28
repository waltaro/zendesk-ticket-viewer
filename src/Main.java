import model.auth.Authenticator;
import model.data.TicketProcessor;
import view.InputHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        // Used to authenticate/talk to the Zendesk API for ticket retrieval
        Authenticator authenticator = new Authenticator();
        // Used to process the ticket data
        TicketProcessor ticketProcessor = new TicketProcessor();
        // Used to handle user input
        InputHandler input = new InputHandler();

        // Welcome the user to the program
        input.printWelcomeMessage();

        // Begin of menu loop
        while(!input.isQuit()) {
            input.printMenu();
            input.getMenuChoice(authenticator, ticketProcessor);
        }
    }
}