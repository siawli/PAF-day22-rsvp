package rsvp.workshop22.rsvp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import rsvp.workshop22.rsvp.model.Rsvp;

import static rsvp.workshop22.rsvp.repository.Queries.*;

import java.sql.ResultSet;
import java.util.Optional;

import com.mysql.cj.result.Row;

@Repository
public class RsvpRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<SqlRowSet> allRsvp() {
        SqlRowSet result = template.queryForRowSet(SQL_ALL_RSVP);

        if (!result.next()) {
            return Optional.empty();
        } else {
            return Optional.of(result);
        }
    }

    public Optional<SqlRowSet> getRsvpByName(String name) {
        SqlRowSet result = template.queryForRowSet(SQL_RSVP_BY_NAME, name);
        
        if (!result.next()) {
            return Optional.empty();
        } else {
            return Optional.of(result);
        }
    }

    public JsonObject addRsvp(Rsvp rsvp) {
        try {
            int added = template.update(SQL_ADD_RSVP, 
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getComment());
            JsonObject message = Json.createObjectBuilder()
                                .add("message", "rsvp has been added into the database")
                                .build();
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
            int added = template.update(SQL_UPDATE_RSVP,
                rsvp.getName(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getComment(),
                rsvp.getEmail());
            JsonObject message = Json.createObjectBuilder()
                                .add("message", "rsvp exists and has been updated")
                                .build();
            return message;
        }
    }

    public Integer countRsvp() {
        SqlRowSet result = template.queryForRowSet(SQL_COUNT_RSVP);
        Integer countOfRsvp = 0;
        
        if (result.next()) {
            countOfRsvp = result.getInt("countRsvp");
        }

        return countOfRsvp;
    }
}
