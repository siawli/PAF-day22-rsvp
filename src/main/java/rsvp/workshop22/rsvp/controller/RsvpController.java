package rsvp.workshop22.rsvp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rsvp.workshop22.rsvp.model.Rsvp;

@Controller
@RequestMapping("/api")
public class RsvpController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("rsvp", new Rsvp());
        return "index";
    }
    
}
