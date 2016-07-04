package model.ticket;

import util.data.ticket.Ticket;
import view.Messages;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketViewer
{
    private static int PAGE_LIMIT = 25;
    private Messages message = new Messages();
    private int pageNumber = 1;

    private int checkIfCurrentTicketHasReachedPageLimit(int currentTicketIndex)
    {
        int ticketIndex = currentTicketIndex;

        // Used for user input
        Scanner input = new Scanner(System.in);
        boolean validInputChoice = false;

        // Checks the current ticket number, if it has reached the 25 page limit
        // asks the user if they want to go forward a page, back a page or quit.
        if ((currentTicketIndex % PAGE_LIMIT == 0))
        {
            try
            {
                while (!validInputChoice)
                {
                    message.printTicketPageMenu(pageNumber);
                    switch (input.nextInt())
                    {
                        // If the user selects to go to the next page, increase the page number
                        // and get the next page of data
                        case 1:
                            // Increase page number
                            pageNumber++;

                            // Reprint the table header for the next page of data
                            message.printTicketTableHeader();

                            validInputChoice = true;
                            break;

                        // If the user selects to view the previous page, decrease the page number
                        // and return the correct index number
                        case 2:
                            // If the user is on the first page of data and wants to return
                            // to a non-existent page, print and error message and ask again
                            if (pageNumber == 1)
                            {
                                message.printTicketPageNonExistentError();
                                break;
                            }

                            // Otherwise, decrease the page number and retrieve the proper page of data
                            else
                            {
                                pageNumber--;
                                ticketIndex -= (ticketIndex / pageNumber);

                                // Reprint the table header for the next page of data
                                message.printTicketTableHeader();

                                validInputChoice = true;
                                break;
                            }

                            // Returns the user to the main menu by returning a random number
                            // that violates the for loop condition
                        case 3:
                            ticketIndex *= ticketIndex;
                            validInputChoice = true;
                            break;

                        default:
                            message.printInputError();
                            break;
                    }
                }

            }
            catch (InputMismatchException e)
            {
                input.nextLine();
                message.printInputError();
            }
        }

        return ticketIndex;
    }

    public void viewAllTickets(Hashtable<Integer, Ticket> tickets)
    {
        // Convert the hashtable into an arraylist used for iteration
        ArrayList<Ticket> listOfTickets = new ArrayList<Ticket>(tickets.values());

        // If the number of tickets is less than the page limit (25)
        // print all tickets in a single page
        if (tickets.size() < PAGE_LIMIT)
        {
            message.printTicketTableHeader();

            for (int ticketID : tickets.keySet())
            {
                message.printTicket(tickets.get(ticketID));
            }
        }

        // If the number of tickets is greater than the page limit
        // limits printing the ticket to 25 tickets per page
        else
        {
            message.printTicketTableHeader();

            for (int currentTicket = 1; currentTicket <= tickets.size(); currentTicket++)
            {
                message.printTicket(listOfTickets.get(currentTicket - 1));
                currentTicket = checkIfCurrentTicketHasReachedPageLimit(currentTicket);
            }
        }

    }

    public void viewSingleTicket(Hashtable<Integer, Ticket> tickets, int id)
    {
        try
        {
            message.printTicketTableHeader();
            message.printTicket(tickets.get(id));
        }
        catch (NullPointerException e)
        {
            message.printNoTicketFound(id);
        }

    }
}
