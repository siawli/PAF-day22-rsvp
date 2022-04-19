package rsvp.workshop22.rsvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import rsvp.workshop22.rsvp.model.Rsvp;
import rsvp.workshop22.rsvp.service.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRestController {

    @Autowired
    private RsvpService rsvpSvc;

    @GetMapping(path="/rsvps", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvp() {
        
        JsonArray listRsvp = rsvpSvc.allRsvpJsonArray();

        if (listRsvp.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listRsvp.toString());
        }
        
        return ResponseEntity.ok(listRsvp.toString());
    }

    @GetMapping(path="/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvpByName(@RequestParam String name) {

        JsonArray listRsvpName = rsvpSvc.getRsvpName(name);

        if (listRsvpName.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listRsvpName.toString());
        } else {
            return ResponseEntity.ok(listRsvpName.toString());
        }
    }

    @PostMapping(path="/rsvp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRsvp(@ModelAttribute Rsvp rsvp) {

        JsonObject message = rsvpSvc.addOrUpdateRsvp(rsvp);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(message.toString());

    }

    @GetMapping(path="/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> countRsvp() {

        JsonObject countRsvp = rsvpSvc.countingRsvp();

        return ResponseEntity.status(HttpStatus.CREATED).body(countRsvp.toString());
    }

}
