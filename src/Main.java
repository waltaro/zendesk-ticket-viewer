import controller.InputHandler;
import model.auth.Authenticator;
import model.ticket.TicketProcessor;
import view.Messages;

public class Main
{

    public static void main(String[] args) throws Exception
    {

        // Used to authenticate/talk to the Zendesk API for ticket retrieval
        Authenticator authenticator = new Authenticator();
        // Used to process the ticket ticket
        TicketProcessor ticketProcessor = new TicketProcessor();
        // Used to handle user input
        InputHandler input = new InputHandler();
        // Used to update the view
        Messages message = new Messages();

        // Welcome the user to the program
        message.printWelcomeMessage();

        // Begin of menu loop
        while (!input.isQuit())
        {
            message.printMenu();
            input.getMenuChoice(authenticator, ticketProcessor);
        }
    }
}