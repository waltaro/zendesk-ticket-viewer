package util.data.ticket;

import java.util.Date;

public class Ticket {

    public int id;
    public String url;
    public String external_id;
    public String type;
    public String subject;
    public String raw_subject;
    public String description;
    public String priority;
    public String status;
    public String recipient;
    public int requester_id;
    public int submitter_id;
    public int assignee_id;
    public int organization_id;
    public int group_id;
    public int collaborator_id[];
    public int forum_topic_id;
    public int problem_id;
    public boolean has_incidents;
    public String due_at;
    public String tags[];
    public Via via;
    public String custom_fields[];
    public SatisfactionRating satisfaction_rating;
    public int sharing_agreement_ids[];
    public int followup_ids[];
    public int ticket_form_id;
    public int brand_id;
    public boolean allow_channelback;
    public String created_at;
    public String updated_at;

    public Ticket(int requester_id) {
        this.requester_id = requester_id;
    }

    public class Via {

        public String channel;
        public Source source;
        public String vel;

        public Via(String channel, Source source, String vel) {
            this.channel = channel;
            this.source = source;
            this.vel = vel;
        }

        public Via() {

        }
    }

    public class Source {
        public int to;
        public From from;

        public Source(int to, From from) {
            this.to = to;
            this.from = from;
        }

        public Source() {

        }
    }

    public class SatisfactionRating {
        public int id;
        public String score;
        public String comment;

        public SatisfactionRating(int id, String score, String comment) {
            this.id = id;
            this.score = score;
            this.comment = comment;
        }

        public SatisfactionRating() {

        }
    }

    public class From {

        public int id;
        public String title;

        public From(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public From() {

        }
    }

}
