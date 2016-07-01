package model.ticket;

import util.data.ticket.Ticket;

import java.util.Hashtable;

public class TicketViewer
{

    public void viewAllTickets(Hashtable<Integer, Ticket> tickets)
    {

    }

    public void viewSingleTicket(Hashtable<Integer, Ticket> tickets, int id)
    {
        try
        {
            System.out.println(tickets.get(id).getId());
        }
        catch (NullPointerException e)
        {
            System.out.println("ID: " + id + "not found.");
        }

    }
}
