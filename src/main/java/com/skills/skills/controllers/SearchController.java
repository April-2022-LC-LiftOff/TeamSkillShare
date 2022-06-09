package com.skills.skills.controllers;


import com.skills.skills.data.EventRepository;
import com.skills.skills.data.SkillsRepository;
import com.skills.skills.data.UserRepository;
import com.skills.skills.models.EventData;
import com.skills.skills.models.SkillData;
import com.skills.skills.models.UserData;
import com.skills.skills.models.event.Event;
import com.skills.skills.models.skill.Skill;
import com.skills.skills.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchController {

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchController() {

        columnChoices.put("all", "All");
        columnChoices.put("username", "UserName");
        //columnChoices.put("firstname", "FirstName");
        //columnChoices.put("lastname", "LastName");
        columnChoices.put("skillname", "Skill");
//        columnChoices.put("categories name", "CatName");
        columnChoices.put("eventname", "Event");
//        columnChoices.put("event description", "EventDescription");
    }

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SkillsRepository skillsRepository;

        @Autowired
        private EventRepository eventRepository;

    @GetMapping("")
    public String search(Model model){
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<User> users;
        Iterable<Skill> skills;
        Iterable<Event> events;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            users = userRepository.findAll();
            skills = skillsRepository.findAll();
            events = eventRepository.findAll();

        }else{
            users = UserData.findByColumnAndValue(searchType,searchTerm,userRepository.findAll());
            skills = SkillData.findBYColumnAndValue(searchType,searchTerm,skillsRepository.findAll());
            events = EventData.findBYColumnAndValue(searchType,searchTerm,eventRepository.findAll());
        }
//        if(searchTerm.toLowerCase().equals("all"))
        model.addAttribute("columns",columnChoices);
        model.addAttribute("title","Users with " + columnChoices.get(searchType) + " : " + searchTerm);
        model.addAttribute("users", users);
        model.addAttribute("title", "Skill with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("skills", skills);
        model.addAttribute("title", "Event with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("events", events);

        return "search";
    }


}

