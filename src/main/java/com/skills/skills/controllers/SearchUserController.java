package com.skills.skills.controllers;

import com.skills.skills.data.SkillsRepository;
import com.skills.skills.data.UserRepository;
import com.skills.skills.models.User;
import com.skills.skills.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("searchUser")
public class SearchUserController {

    static HashMap<String, String> columnChoices = new HashMap<>();
    public SearchUserController(){
        columnChoices.put("all", "All");
       // columnChoices.put("username", "UserName");
        columnChoices.put("firstname", "FirstName");
        columnChoices.put("lastname", "LastName");
        columnChoices.put("skills", "Skills");
//        columnChoices.put("events", "Events");
    }
    @Autowired
    private UserRepository userRepository;

    //add skillRepository and eventRepo..
    // @Autowired
    private SkillsRepository skillsRepository;

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("columns", columnChoices);
        return "searchUser";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<User> users;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            users = userRepository.findAll();
        }else{
            users = UserData.findByColumnAndValue(searchType,searchTerm,userRepository.findAll());
        }
        model.addAttribute("columns",columnChoices);
        model.addAttribute("title","Users with " + columnChoices.get(searchType) + " : " + searchTerm);
        model.addAttribute("users", users);

        return "searchUser";
    }
}