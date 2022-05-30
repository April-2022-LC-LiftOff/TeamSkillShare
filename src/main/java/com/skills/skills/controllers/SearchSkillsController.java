package com.skills.skills.controllers;


import com.skills.skills.data.SkillsRepository;
import com.skills.skills.models.Skill;
import com.skills.skills.models.SkillData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("searchSkill")
public class SearchSkillsController {

    static HashMap<String, String> columnChoices = new HashMap<>();
    public SearchSkillsController(){
        columnChoices.put("all", "All");
        columnChoices.put("name", "Skill's Name");
        columnChoices.put("categories name", "CatName");
    }

    @Autowired
    private SkillsRepository skillsRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "searchSkill";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Skill> skills;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
          skills= skillsRepository.findAll();
        }else {
           skills = SkillData.findBYColumnAndValue(searchType, searchTerm, skillsRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Skill with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("skills", skills);

        return "searchSkill";
    }

}
