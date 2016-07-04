package model.ticket;

import model.auth.Authenticator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.data.ticket.From;
import util.data.ticket.Source;
import util.data.ticket.Ticket;
import util.data.ticket.Via;
import view.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TicketProcessor
{

    // Used for processing
    private InputStream receivedJsonDataStream;
    private String receivedJsonData;
    private Hashtable<Integer, Ticket> ticketDatabase = new Hashtable<>();

    // Used for checking
    private boolean isDataProcessed = false;
    private Messages messages = new Messages();

    public void setReceivedJsonData(String receivedJsonData)
    {
        this.receivedJsonData = receivedJsonData;
    }

    private void getTicketData() throws IOException
    {
        if (isDataProcessed)
        {
            return;
        }

        try
        {
            // Inform user that we are retrieving the ticket
            messages.printRetrievingTicketDataMessage();

            // Get ticket ticket stream from the Zendesk servers
            BufferedReader ticketDataStream = new BufferedReader(new InputStreamReader(this.receivedJsonDataStream));

            String input;
            StringBuilder data = new StringBuilder();

            // Get all ticket ticket from the servers
            while ((input = ticketDataStream.readLine()) != null)
            {
                data.append(input);
            }

            // Close string buffer
            ticketDataStream.close();

            setReceivedJsonData(data.toString());
            setDataProcessed(true);
        }
        catch (NullPointerException e)
        {

        }
    }

    private ArrayList<String> getJSONArrayStringData(JSONObject ticketData, String key)
    {
        JSONArray arrayData = ticketData.getJSONArray(key);
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < arrayData.length(); i++)
        {
            list.add(arrayData.get(i).toString());
        }

        return list;
    }

    private ArrayList<Integer> getJSONArrayIntData(JSONObject ticketData, String key)
    {
        JSONArray arrayData = ticketData.getJSONArray(key);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arrayData.length(); i++)
        {
            list.add(Integer.parseInt(arrayData.get(i).toString()));
        }

        return list;
    }

    private Via getJSONViaData(JSONObject ticketData)
    {
        JSONObject via_data = ticketData.getJSONObject("via");
        JSONObject via_source = via_data.getJSONObject("source");
        JSONObject via_source_from = via_source.getJSONObject("from");

        String via_channel = via_data.getString("channel");
        int via_source_to = via_source.optInt("to");
        int via_source_from_id = via_source_from.optInt("id");
        String via_source_from_title = via_source_from.optString("title");
        String via_source_rel = via_source.optString("rel");

        From from = new From(via_source_from_id, via_source_from_title);
        Source source = new Source(via_source_to, from, via_source_rel);

        return new Via(via_channel, source);
    }

    private Ticket getTicket(JSONObject ticketData)
    {
        // Create new empty ticket
        Ticket ticket = new Ticket();

        // Populate empty ticket with ticket data from the server

        // Retrieving all string data
        String url = ticketData.optString("url");
        String external_id = ticketData.optString("external_id");
        String type = ticketData.optString("type");
        String subject = ticketData.optString("subject");
        String raw_subject = ticketData.optString("raw_subject");
        String description = ticketData.optString("description");
        String priority = ticketData.optString("priority");
        String status = ticketData.optString("status");
        String satisfaction_rating = ticketData.optString("satisfaction_rating");
        String recipient = ticketData.optString("recipient");
        String due_at = ticketData.optString("due_at");
        String created_at = ticketData.optString("created_at");
        String updated_at = ticketData.optString("updated_at");

        // Retrieving all integer data
        long id = ticketData.optLong("id");
        long requester_id = ticketData.optLong("requester_id");
        long submitter_id = ticketData.optLong("submitter_id");
        long assignee_id = ticketData.optLong("assignee_id");
        long organization_id = ticketData.optLong("organization_id");
        long group_id = ticketData.optLong("group_id");
        long forum_topic_id = ticketData.optLong("forum_topic_id");
        long problem_id = ticketData.optLong("problem_id");
        long ticket_form_id = ticketData.optLong("ticket_form_id");
        long brand_id = ticketData.optLong("brand_id");

        // Retrieving all boolean data
        boolean has_incidents = ticketData.getBoolean("has_incidents");
        boolean allow_channelback = ticketData.optBoolean("allow_channelback");

        // Retrieving all array data
        ArrayList<Integer> collaborator_ids = getJSONArrayIntData(ticketData, "collaborator_ids");
        ArrayList<String> tags = getJSONArrayStringData(ticketData, "tags");
        ArrayList<String> custom_fields = getJSONArrayStringData(ticketData, "tags");
        ArrayList<Integer> sharing_agreement_ids = getJSONArrayIntData(ticketData, "sharing_agreement_ids");
        ArrayList<Integer> followup_ids = new ArrayList<>();

        // If the ticket is closed, retrieve the followup ids
        if(status.equalsIgnoreCase("closed"))
        {
            followup_ids = getJSONArrayIntData(ticketData, "followup_ids");
        }

        // Retrieving the Via object
        Via via = getJSONViaData(ticketData);

        // Populates the ticket with retrieved data
        ticket.setId(id);
        ticket.setUrl(url);
        ticket.setExternal_id(external_id);
        ticket.setType(ticketData.optString(type));
        ticket.setSubject(subject);
        ticket.setRaw_subject(raw_subject);
        ticket.setDescription(description);
        ticket.setPriority(priority);
        ticket.setStatus(status);
        ticket.setRecipient(recipient);
        ticket.setRequester_id(requester_id);
        ticket.setSubmitter_id(submitter_id);
        ticket.setAssignee_id(assignee_id);
        ticket.setOrganization_id(organization_id);
        ticket.setGroup_id(group_id);
        ticket.setCollaborator_id(collaborator_ids);
        ticket.setForum_topic_id(forum_topic_id);
        ticket.setProblem_id(problem_id);
        ticket.setHas_incidents(has_incidents);
        ticket.setDue_at(due_at);
        ticket.setTags(tags);
        ticket.setVia(via);
        ticket.setCustom_fields(custom_fields);
        ticket.setSatisfaction_rating(satisfaction_rating);
        ticket.setSharing_agreement_ids(sharing_agreement_ids);
        ticket.setFollowup_ids(followup_ids);
        ticket.setTicket_form_id(ticket_form_id);
        ticket.setBrand_id(brand_id);
        ticket.setAllow_channelback(allow_channelback);
        ticket.setCreated_at(created_at);
        ticket.setUpdated_at(updated_at);

        // Return the populated ticket
        return ticket;
    }

    private void saveTicketData(Authenticator authenticator)
    {
        // Create new JSON Object
        JSONObject receivedData = new JSONObject(receivedJsonData);

        // Retrieve all ticketDatabase
        try
        {
            // Get ticketDatabase from the received ticket
            JSONArray tickets = receivedData.getJSONArray("tickets");

            // Inform the user that we are saving the ticket data
            messages.printSavingTicketDataMessage();

            // Get all ticketDatabase and store it into the hashtable
            for (int i = 0; i < tickets.length(); i++)
            {

                // Get the current ticket
                JSONObject ticketData = tickets.getJSONObject(i);
                int id = ticketData.optInt("id");
                Ticket fuck = getTicket(ticketData);

                // Add the current ticket to the Hashtable
                this.ticketDatabase.put(id, fuck);
            }

        }
        catch (JSONException e)
        {

        }
        catch (NullPointerException e)
        {

        }

        // Checks to see if there is another page of ticketDatabase
        try
        {
            // Try to get the next string
            String nextPageURL = receivedData.optString("next_page");

            // If there exists a next page of ticketDatabase
            if (!nextPageURL.isEmpty())
            {
                // Set all checks to false so we can process data again
                setDataProcessed(false);

                // Connect to the Zendesk API to retrieve the next page of ticketDatabase
                authenticator.connect(authenticator.getUserAccount().getEncryptedAccountDetails(), nextPageURL, this);

                // Process the data
                processData(authenticator);
            }
        }
        // If there is no next page, catch the exception and set the data processed check flag to true
        catch (JSONException e)
        {
            setDataProcessed(true);
        }
        catch (NullPointerException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void processData(Authenticator authenticator) throws IOException
    {
        // If the ticket has already been processed, no need to do it again
        if (isDataProcessed)
        {
            return;
        }

        // Get JSON from the server
        getTicketData();

        // Save data so it can be retrieved
        saveTicketData(authenticator);
    }

    public void setReceivedJsonDataStream(InputStream receivedJsonDataStream)
    {
        this.receivedJsonDataStream = receivedJsonDataStream;
    }

    public void setDataProcessed(boolean dataProcessed)
    {
        isDataProcessed = dataProcessed;
    }

    public Hashtable<Integer, Ticket> getTicketDatabase()
    {
        return ticketDatabase;
    }
}
