package model.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TicketProcessor {

    // Used for processing
    private InputStream dataStream;

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }

    private String ticketData;

    // Used for checking
    private boolean isDataSuccessfullyRetreived = false;
    private boolean isDataProcessed = false;

    private void getTickets() throws IOException {

        // If data has been retrieved, no need to retrieve it again
        if(isDataSuccessfullyRetreived) {
            return;
        }

        // Inform user that we are retrieving the data
        System.out.println("Retrieving tickets...\n");

        // Get ticket data stream from the Zendesk servers
        BufferedReader ticketDataStream = new BufferedReader(new InputStreamReader(this.dataStream));

        String input;
        StringBuffer data = new StringBuffer();

        // Get all ticket data from the servers
        while((input = ticketDataStream.readLine()) != null) {
            data.append(input);
        }

        // Close string buffer
        ticketDataStream.close();

        setTicketData(data.toString());
        setIsDataSuccessfullyRetreived(true);
    }

    public void processData() throws IOException {

        // If the data has already been processed, no need to do it again
        if(isDataProcessed) {
            return;
        }

        // Get JSON from the server
        getTickets();

        // Create new JSON
        JSONObject receivedData = new JSONObject(this.ticketData);

        // Get tickets from the received data
        JSONArray tickets = receivedData.getJSONArray("tickets");

        // TODO: actual processing instead of printing out the IDs..
        for(int i = 0; i < tickets.length(); i++) {
            JSONObject ticket = tickets.getJSONObject(i);
            int id = ticket.getInt("id");
            System.out.println(id);
        }

        setDataProcessed(true);
    }

    public void setDataStream(InputStream dataStream) {
        this.dataStream = dataStream;
    }

    public void setIsDataSuccessfullyRetreived(boolean dataSuccessfullyRetreived) {
        this.isDataSuccessfullyRetreived = dataSuccessfullyRetreived;
    }

    public void setDataProcessed(boolean dataProcessed) {
        isDataProcessed = dataProcessed;
    }
}
