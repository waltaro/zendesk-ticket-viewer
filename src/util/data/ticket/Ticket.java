package util.data.ticket;

import java.util.ArrayList;

public class Ticket
{

    private int id;
    private String url;
    private String external_id;
    private String type;
    private String subject;
    private String raw_subject;
    private String description;
    private String priority;
    private String status;
    private String recipient;
    private int requester_id;
    private int submitter_id;
    private int assignee_id;
    private int organization_id;
    private int group_id;
    private ArrayList<Integer> collaborator_id;
    private int forum_topic_id;
    private int problem_id;
    private boolean has_incidents;
    private String due_at;
    private ArrayList<String> tags;
    private Via via;
    private ArrayList<String> custom_fields;
    private SatisfactionRating satisfaction_rating;
    private ArrayList<Integer> sharing_agreement_ids;
    private ArrayList<Integer> followup_ids;
    private int ticket_form_id;
    private int brand_id;
    private boolean allow_channelback;
    private String created_at;
    private String updated_at;

    public Ticket(int requester_id)
    {
        this.requester_id = requester_id;
    }

    public Ticket()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getExternal_id()
    {
        return external_id;
    }

    public void setExternal_id(String external_id)
    {
        this.external_id = external_id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getRaw_subject()
    {
        return raw_subject;
    }

    public void setRaw_subject(String raw_subject)
    {
        this.raw_subject = raw_subject;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRecipient()
    {
        return recipient;
    }

    public void setRecipient(String recipient)
    {
        this.recipient = recipient;
    }

    public int getRequester_id()
    {
        return requester_id;
    }

    public void setRequester_id(int requester_id)
    {
        this.requester_id = requester_id;
    }

    public int getSubmitter_id()
    {
        return submitter_id;
    }

    public void setSubmitter_id(int submitter_id)
    {
        this.submitter_id = submitter_id;
    }

    public int getAssignee_id()
    {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id)
    {
        this.assignee_id = assignee_id;
    }

    public int getOrganization_id()
    {
        return organization_id;
    }

    public void setOrganization_id(int organization_id)
    {
        this.organization_id = organization_id;
    }

    public int getGroup_id()
    {
        return group_id;
    }

    public void setGroup_id(int group_id)
    {
        this.group_id = group_id;
    }

    public ArrayList<Integer> getCollaborator_id()
    {
        return collaborator_id;
    }

    public void setCollaborator_id(ArrayList<Integer> collaborator_id)
    {
        this.collaborator_id = collaborator_id;
    }

    public int getForum_topic_id()
    {
        return forum_topic_id;
    }

    public void setForum_topic_id(int forum_topic_id)
    {
        this.forum_topic_id = forum_topic_id;
    }

    public int getProblem_id()
    {
        return problem_id;
    }

    public void setProblem_id(int problem_id)
    {
        this.problem_id = problem_id;
    }

    public boolean isHas_incidents()
    {
        return has_incidents;
    }

    public void setHas_incidents(boolean has_incidents)
    {
        this.has_incidents = has_incidents;
    }

    public String getDue_at()
    {
        return due_at;
    }

    public void setDue_at(String due_at)
    {
        this.due_at = due_at;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }

    public Via getVia()
    {
        return via;
    }

    public void setVia(Via via)
    {
        this.via = via;
    }

    public ArrayList<String> getCustom_fields()
    {
        return custom_fields;
    }

    public void setCustom_fields(ArrayList<String> custom_fields)
    {
        this.custom_fields = custom_fields;
    }

    public SatisfactionRating getSatisfaction_rating()
    {
        return satisfaction_rating;
    }

    public void setSatisfaction_rating(SatisfactionRating satisfaction_rating)
    {
        this.satisfaction_rating = satisfaction_rating;
    }

    public ArrayList<Integer> getSharing_agreement_ids()
    {
        return sharing_agreement_ids;
    }

    public void setSharing_agreement_ids(ArrayList sharing_agreement_ids)
    {
        this.sharing_agreement_ids = sharing_agreement_ids;
    }

    public ArrayList<Integer> getFollowup_ids()
    {
        return followup_ids;
    }

    public void setFollowup_ids(ArrayList<Integer> followup_ids)
    {
        this.followup_ids = followup_ids;
    }

    public int getTicket_form_id()
    {
        return ticket_form_id;
    }

    public void setTicket_form_id(int ticket_form_id)
    {
        this.ticket_form_id = ticket_form_id;
    }

    public int getBrand_id()
    {
        return brand_id;
    }

    public void setBrand_id(int brand_id)
    {
        this.brand_id = brand_id;
    }

    public boolean isAllow_channelback()
    {
        return allow_channelback;
    }

    public void setAllow_channelback(boolean allow_channelback)
    {
        this.allow_channelback = allow_channelback;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    public String getUpdated_at()
    {
        return updated_at;
    }

    public void setUpdated_at(String updated_at)
    {
        this.updated_at = updated_at;
    }
}
