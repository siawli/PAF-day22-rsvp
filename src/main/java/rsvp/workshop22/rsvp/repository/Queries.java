package rsvp.workshop22.rsvp.repository;

public interface Queries {

    public static final String SQL_ALL_RSVP = 
        "select * from rsvp";
    
    public static final String SQL_RSVP_BY_NAME = 
        "select * from rsvp where name = ?";

    public static final String SQL_ADD_RSVP = 
        "insert into rsvp (name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_RSVP = 
        "update rsvp set name = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";

    public static final String SQL_COUNT_RSVP = 
        "select count(email) as countRsvp from rsvp";
}
