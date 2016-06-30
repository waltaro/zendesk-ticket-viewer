package view;

public class Messages
{

    public Messages()
    {
    }

    public void printWelcomeMessage()
    {
        System.out.println("Welcome to the Zendesk Ticket Viewer!");
    }

    public void printGoodbyeMessage()
    {
        System.out.println("\nThank you for using the Zendesk Ticket Viewer");
    }

    public void printMenu()
    {
        System.out.println("\nPlease select from the following choices below: \n");
        System.out.println("\t1 - View all tickets");
        System.out.println("\t2 - View single ticket");
        System.out.println("\t3 - Quit\n");
        System.out.print("Input: ");
    }

    public void printInputError()
    {
        System.out.println("Your input was not recognised.");
    }

    public void printEnterEmailAddressMessage()
    {
        System.out.print("\nPlease enter your email address: ");
    }

    public void printEnterPasswordMessage()
    {
        System.out.print("Please enter your password: ");
    }

    public void printEnterSubdomainMessage()
    {
        System.out.print("\nPlease enter your subdomain name on Zendesk (https://subdomain.zendesk.com/): ");
    }

    public boolean printIncorrectSubdomainMessage()
    {
        System.out.println("\nYour subdomain name was incorrect, please try again.");
        return false;
    }

    public boolean printConnectError()
    {
        System.out.println("\nThe API is down or your user details were incorrect");
        return false;
    }

    public void printLoginMessage()
    {
        System.out.println("\nLogging in...");
    }

    public void printSavingTicketDataMessage()
    {
        System.out.println("Saving ticket data...");
    }

    public void printRetrievingTicketDataMessage()
    {
        System.out.println("Retrieving tickets...");
    }

    public void printNoTicketsFound()
    {
        System.out.println("\nNo tickets have been found!");
    }
}
