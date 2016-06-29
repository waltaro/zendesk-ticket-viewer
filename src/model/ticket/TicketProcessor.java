package model.ticket;

import org.json.JSONArray;
import org.json.JSONObject;
import util.data.ticket.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TicketProcessor {

    // Used for processing
    private InputStream dataStream;

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }

    private String ticketData;
    private Hashtable<Integer, Ticket> tickets = new Hashtable<>();

    // Used for checking
    private boolean isDataSuccessfullyRetreived = false;
    private boolean isDataProcessed = false;

    private void getTicketData() throws IOException {

        // If ticket has been retrieved, no need to retrieve it again
        if(isDataSuccessfullyRetreived) {
            return;
        }

        // Inform user that we are retrieving the ticket
        System.out.println("Retrieving tickets...\n");

        // Get ticket ticket stream from the Zendesk servers
        BufferedReader ticketDataStream = new BufferedReader(new InputStreamReader(this.dataStream));

        String input;
        StringBuilder data = new StringBuilder();

        // Get all ticket ticket from the servers
        while((input = ticketDataStream.readLine()) != null) {
            data.append(input);
        }

        // Close string buffer
        ticketDataStream.close();

        setTicketData(data.toString());
        setIsDataSuccessfullyRetreived(true);
    }

    private ArrayList getJSONArrayStringData(JSONObject data, String key) {
        JSONArray arrayData = data.getJSONArray(key);
        ArrayList list = new ArrayList<>();

        for(int i = 0; i < arrayData.length(); i++) {
            list.add(arrayData.get(i).toString());
        }

        return list;
    }

    private ArrayList getJSONArrayIntData(JSONObject data, String key) {
        JSONArray arrayData = data.getJSONArray(key);
        ArrayList list = new ArrayList<>();

        for(int i = 0; i < arrayData.length(); i++) {
            list.add(Integer.parseInt(arrayData.get(i).toString()));
        }

        return list;
    }

    // TODO: refactor this somehow.. it's disgusting
    private Ticket getTicket(JSONObject ticketData) {

        // Create new empty ticket
        Ticket ticket = new Ticket();

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
        //Via via = ticketData.get("via");
        ArrayList custom_fields = getJSONArrayStringData(ticketData, "tags");
        // todo satisfaction_rating
        ArrayList sharing_agreement_ids = getJSONArrayIntData(ticketData, "sharing_agreement_ids");
        ArrayList followup_ids = getJSONArrayIntData(ticketData, "followup_ids");
        int ticket_form_id = ticketData.optInt("ticket_form_id");
        int brand_id = ticketData.optInt("brand_id");
        boolean allow_channelback = ticketData.optBoolean("allow_channelback");
        String created_at = ticketData.optString("created_at");
        String updated_at = ticketData.optString("updated_at");

        // Popu
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

        return ticket;
    }


    private void saveTicketData() {
        // Create new JSON
        JSONObject receivedData = new JSONObject(this.ticketData);

        // Get tickets from the received ticket
        JSONArray tickets = receivedData.getJSONArray("tickets");

        // Get all tickets and store it into the hashtable
        for(int i = 0; i < tickets.length(); i++) {

            // Get the current ticket
            JSONObject ticketData = tickets.getJSONObject(i);

            this.tickets.put(ticketData.optInt("id"), getTicket(ticketData));
        }
    }

    public void processData() throws IOException {

        // If the ticket has already been processed, no need to do it again
        if(isDataProcessed) {
            return;
        }

        // Get JSON from the server
        getTicketData();

        // Save data so it can be retrieved
        saveTicketData();

        // Set data processed to true so we don't have to process data again
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
