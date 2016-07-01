package controller;

import model.auth.Authenticator;
import model.ticket.TicketProcessor;
import view.Messages;
import model.ticket.TicketViewer;

import java.io.IOException;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler
{

    // Scanner used for user input;
    private Scanner input = new Scanner(System.in);

    // Used for menu loop
    private boolean quit = false;

    // Used to update the view
    private Messages message = new Messages();

    public InputHandler()
    {

    }

    public boolean isQuit()
    {
        return quit;
    }

    public void setQuit(boolean quit)
    {
        this.quit = quit;
    }

    private void quit()
    {
        message.printGoodbyeMessage();
        setQuit(true);
    }

    private void setAccountDetails(Authenticator authenticator)
    {

        // Requests the user to enter their username
        message.printEnterEmailAddressMessage();
        authenticator.setAccountUsername(input.nextLine());

        // Requests the user to enter their password
        message.printEnterPasswordMessage();
        authenticator.setAccountPassword(input.nextLine());
    }

    private void setDomainDetails(Authenticator authenticator)
    {

        // Requests the user to enter their domain name on Zendesk
        message.printEnterSubdomainMessage();

        // Clear buffer
        input.nextLine();

        // Sets the domain name to be used for authentication
        authenticator.setUserSubdomainName(input.nextLine());

    }

    public void getUserDetails(Authenticator authenticator)
    {

        if (!authenticator.isConnected())
        {
            // Requests the user to enter their domain name on Zendesk
            setDomainDetails(authenticator);

            // Requests the user to getUserDetails to their Zendesk account
            setAccountDetails(authenticator);
        }

    }

    private void startConnectionProcess(Authenticator authenticator, TicketProcessor ticketProcessor) throws IOException
    {
        // Request user to enter their details
        getUserDetails(authenticator);

        // If login was successful, process the data received
        if(authenticator.login(ticketProcessor))
        {
            // Process data received
            ticketProcessor.processData(authenticator);
        }
    }

    // ask user for ticket id, then call ticketviewer to access data to view id.
    private void getTicketID(TicketProcessor ticketProcessor) {

        TicketViewer ticketViewer = new TicketViewer();

        try
        {
            System.out.print("\nPlease enter ticket ID: ");
            ticketViewer.viewSingleTicket(ticketProcessor.getTicketDatabase(), input.nextInt());
        }
        catch (InputMismatchException error)
        {
            input.nextLine();
            message.printInputError();
        }

    }

    public void getMenuChoice(Authenticator authenticator, TicketProcessor ticketProcessor) throws IOException
    {

        TicketViewer ticketViewer = new TicketViewer();

        try
        {
            switch (input.nextInt())
            {
                case 1:
                    startConnectionProcess(authenticator, ticketProcessor);
                    ticketViewer.viewAllTickets(null);
                    break;

                case 2:
                    startConnectionProcess(authenticator, ticketProcessor);
                    getTicketID(ticketProcessor);
                    break;

                case 3:
                    quit();
                    break;

                default:
                    message.printInputError();
                    break;
            }
        }
        catch (InputMismatchException error)
        {
            // Clear input
            input.nextLine();
            message.printInputError();
        }

    }
}
