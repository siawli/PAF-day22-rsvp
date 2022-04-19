package rsvp.workshop22.rsvp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import rsvp.workshop22.rsvp.model.Rsvp;
import rsvp.workshop22.rsvp.repository.RsvpRepository;

@Service
public class RsvpService {

    @Autowired
    private RsvpRepository rsvpRepo;

    public JsonArray allRsvpJsonArray() {
        Optional<SqlRowSet> listRsvpOpt = rsvpRepo.allRsvp();

        if (listRsvpOpt.isEmpty()) {
            JsonArray emptyJsonArr = Json.createArrayBuilder().build();
            return emptyJsonArr;
        }

        SqlRowSet result = listRsvpOpt.get();
        JsonArrayBuilder jsonArrB = Json.createArrayBuilder();

        jsonArrB.add(createJsonObj(Rsvp.createRsvp(result)));

        while(result.next()) {
            JsonObject rsvpJsonObj = createJsonObj(Rsvp.createRsvp(result));
            jsonArrB.add(rsvpJsonObj);
        }
        
        return jsonArrB.build();
    }

    public JsonArray getRsvpName(String name) {
        Optional<SqlRowSet> rsvpNameOpt = rsvpRepo.getRsvpByName(name);

        if (rsvpNameOpt.isEmpty()) {
            JsonArray empthJsonArray = Json.createArrayBuilder().build();
            return empthJsonArray;
        }

        SqlRowSet result = rsvpNameOpt.get();
        JsonArrayBuilder jsonArrB = Json.createArrayBuilder();
        jsonArrB.add(createJsonObj(Rsvp.createRsvp(result)));

        while (result.next()) {
            JsonObject rsvpJsonObj = createJsonObj(Rsvp.createRsvp(result));
            jsonArrB.add(rsvpJsonObj);
        }

        return jsonArrB.build();
        
    }
    
    public JsonObject createJsonObj(Rsvp rsvp) {
        JsonObject rsvpJsonObj = Json.createObjectBuilder()
                                    .add("name", rsvp.getName())
                                    .add("email", rsvp.getEmail())
                                    .add("phone", rsvp.getPhone())
                                    .add("confirmation date", rsvp.getDate())
                                    .add("comment", rsvp.getComment())
                                    .build();
        return rsvpJsonObj;
    }

    public JsonObject addOrUpdateRsvp(Rsvp rsvp) {
        return rsvpRepo.addRsvp(rsvp);
    }

    public JsonObject countingRsvp() {
        Integer rsvpCount = rsvpRepo.countRsvp();
        
        return Json.createObjectBuilder()
                    .add("number of people attending", rsvpCount)
                    .build();
    }
}
