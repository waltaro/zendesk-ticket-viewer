package model.ticket;

import model.auth.Authenticator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        if(isDataProcessed)
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
        catch(NullPointerException e)
        {

        }
    }

    private ArrayList getJSONArrayStringData(JSONObject data, String key)
    {
        JSONArray arrayData = data.getJSONArray(key);
        ArrayList list = new ArrayList<>();

        for (int i = 0; i < arrayData.length(); i++)
        {
            list.add(arrayData.get(i).toString());
        }

        return list;
    }

    private ArrayList getJSONArrayIntData(JSONObject data, String key)
    {
        JSONArray arrayData = data.getJSONArray(key);
        ArrayList list = new ArrayList<>();

        for (int i = 0; i < arrayData.length(); i++)
        {
            list.add(Integer.parseInt(arrayData.get(i).toString()));
        }

        return list;
    }

    // TODO: refactor this somehow.. it's disgusting
    private Ticket getTicket(JSONObject ticketData)
    {

        // Create new empty ticket
        Ticket ticket = new Ticket();

        try
        {
            // Populate empty ticket with ticket data from the server
            int id = ticketData.optInt("id");
            String url = ticketData.optString("url");
            String external_id = ticketData.optString("external_id");
            String type = ticketData.optString("type");
            String subject = ticketData.optString("subject");
            String raw_subject = ticketData.optString("raw_subject");
            String description = ticketData.optString("description");
            String priority = ticketData.optString("priority");
            String status = ticketData.optString("status");
            String recipient = ticketData.optString("recipient");
            int requester_id = ticketData.optInt("requester_id");
            int submitter_id = ticketData.optInt("submitter_id");
            int assignee_id = ticketData.optInt("assignee_id");
            int organization_id = ticketData.optInt("organization_id");
            int group_id = ticketData.optInt("group_id");
            ArrayList collaborator_ids = getJSONArrayIntData(ticketData, "collaborator_ids");
            int forum_topic_id = ticketData.optInt("forum_topic_id");
            int problem_id = ticketData.optInt("problem_id");
            boolean has_incidents = ticketData.getBoolean("has_incidents");
            String due_at = ticketData.optString("due_at");
            ArrayList tags = getJSONArrayStringData(ticketData, "tags");
            // todo via

            ArrayList custom_fields = getJSONArrayStringData(ticketData, "tags");
            // todo satisfaction_rating
            ArrayList sharing_agreement_ids = getJSONArrayIntData(ticketData, "sharing_agreement_ids");
            //Only for closed ticketDatabase
            ArrayList followup_ids = getJSONArrayIntData(ticketData, "followup_ids");

            int ticket_form_id = ticketData.optInt("ticket_form_id");
            int brand_id = ticketData.optInt("brand_id");
            boolean allow_channelback = ticketData.optBoolean("allow_channelback");
            String created_at = ticketData.optString("created_at");
            String updated_at = ticketData.optString("updated_at");

            // Populates the ticket
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
            //ticket.setVia(via);
            ticket.setCustom_fields(custom_fields);
            //ticket.setSatisfaction_rating(sat);
            ticket.setSharing_agreement_ids(sharing_agreement_ids);
            ticket.setFollowup_ids(followup_ids);
            ticket.setTicket_form_id(ticket_form_id);
            ticket.setBrand_id(brand_id);
            ticket.setAllow_channelback(allow_channelback);
            ticket.setCreated_at(created_at);
            ticket.setUpdated_at(updated_at);
        }
        catch (JSONException e)
        {
            // If there are no closed tickets, catch the exception for followup_ids not being found
        }

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

                // Add the current ticket to the Hashtable
                ticketDatabase.put(ticketData.optInt("id"), getTicket(ticketData));

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
            if(!nextPageURL.isEmpty()) {
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
}
