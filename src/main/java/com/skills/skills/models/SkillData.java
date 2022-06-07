package com.skills.skills.models;

import com.skills.skills.models.skill.Skill;

import java.util.ArrayList;

public class SkillData {

    public static ArrayList<Skill> findBYColumnAndValue(String column, String value, Iterable<Skill> allSkills){

        ArrayList<Skill>skillResults = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Skill>) allSkills;
        }

        if (column.equals("all")){
            skillResults = findByValue(value, allSkills);
            return skillResults;
        }

        for (Skill skill : allSkills) {
            String aValue = getFieldValue(skill, column);

            if(aValue != null && aValue.toLowerCase().contains(value.toLowerCase())){
                skillResults.add(skill);
            }
        }
        return skillResults;
    }

    public static String getFieldValue(Skill skill, String attributeName) {
        String theValue;

        if(attributeName.equals("Name")){
            theValue = skill.getName();
        }else{
            theValue = skill.getCatName().toString();
        }

        return theValue;

    }

    public static ArrayList<Skill> findByValue(String value, Iterable<Skill> allSkills){

        ArrayList<Skill>skillResults = new ArrayList<>();

        for(Skill skill :allSkills){
            if(skill.getName().toLowerCase().contains(value.toLowerCase())){
                skillResults.add(skill);

            }else if(skill.getCatName().toString().toLowerCase().contains(value.toLowerCase())){
                skillResults.add(skill);
            }
        }

        return skillResults;

    }

}