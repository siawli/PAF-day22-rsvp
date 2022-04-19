package rsvp.workshop22.rsvp.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Rsvp {
    private String name;
    private String email;
    private String phone;
    private String date;
    private String comment;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Rsvp [comment=" + comment + ", date=" + date + ", email=" + email + ", name=" + name + ", phone="
                + phone + "]";
    }

    public static Rsvp createRsvp(SqlRowSet result) {
        Rsvp rsvp = new Rsvp();
        rsvp.setName(result.getString("name"));
        rsvp.setEmail(result.getString("email"));
        rsvp.setPhone(result.getString("phone"));
        rsvp.setDate(result.getString("confirmation_date"));
        rsvp.setComment(result.getString("comments"));

        return rsvp;
    }

}
