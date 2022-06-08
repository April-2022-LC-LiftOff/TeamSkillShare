package com.skills.skills.models;

import com.skills.skills.models.event.Event;
import com.skills.skills.models.skill.Skill;

import java.util.ArrayList;

public class EventData {

    public static ArrayList<Event> findBYColumnAndValue(String column, String value, Iterable<Event> allEvents){

        ArrayList<Event>eventResults = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Event>) allEvents;
        }


        if (column.equals("all")){
            eventResults = findBYValue(value, allEvents);
            return eventResults;
        }

        for( Event event : allEvents){
            String aValue = getFieldValue(event, column);

            if(aValue != null && aValue.toLowerCase().contains(value.toLowerCase())){
                eventResults.add(event);
            }
        }
        return eventResults;
    }

    public static String getFieldValue(Event event, String attributeName){
        String theValue;

        if(attributeName.equals("Description")){
            theValue = event.getDescription();
        }else{
            theValue = event.getName();
        }
        return theValue;
    }

    public static ArrayList<Event> findBYValue(String value, Iterable<Event> allEvents){

        ArrayList<Event>eventResults = new ArrayList<>();

        for(Event event : allEvents){
            if(event.getName().toLowerCase().contains(value.toLowerCase())){
                eventResults.add(event);
            }else if(event.getDescription().toLowerCase().contains(value.toLowerCase())){
                eventResults.add(event);

            }
        }
        return eventResults;
    }
}
