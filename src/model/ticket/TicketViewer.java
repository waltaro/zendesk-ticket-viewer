package model.ticket;

import util.data.ticket.Ticket;
import view.Messages;

import java.util.Hashtable;

public class TicketViewer
{
    private Messages message = new Messages();

    public void viewAllTickets(Hashtable<Integer, Ticket> tickets)
    {
        message.printTicketTableHeader();

        for(int ticketID : tickets.keySet())
        {
            message.printTicket(tickets.get(ticketID));
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
