package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TicketProcessor {

    private String getTickets(InputStream data) throws IOException {

        // Get ticket data stream from the Zendesk servers
        BufferedReader ticketDataStream = new BufferedReader(new InputStreamReader(data));

        String input;
        StringBuffer ticketData = new StringBuffer();

        // Get all ticket data from the servers
        while((input = ticketDataStream.readLine()) != null) {
            ticketData.append(input);
        }

        // Close string buffer
        ticketDataStream.close();

        return ticketData.toString();
    }

    public void processData(InputStream data) throws IOException {

        // Get JSON from the server
        String JSON = getTickets(data);

        // TODO: save data into respective data structure
    }
}
