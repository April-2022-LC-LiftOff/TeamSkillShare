package com.skills.skills.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    public static ArrayList<User> findByColumnAndValue(String column, String value, Iterable<User> allUsers){

        ArrayList<User> userResults = new ArrayList<>();

        if(value.toLowerCase().equals("all")){
            return (ArrayList<User>) allUsers;
        }

        if(column.equals("all")){
            userResults = findByValue(value, allUsers);
            return userResults;
        }

        for(User user : allUsers){
            String aValue = getFieldValue(user, column);

            if(aValue != null && aValue.toLowerCase().contains(value.toLowerCase())){
                userResults.add(user);
            }
        }
        return userResults;
    }
    public static String getFieldValue(User user, String attributeName){
        String theValue;
        if(attributeName.equals("name")) {
            theValue = user.getUsername();
        }else if(attributeName.equals("firstname")){
            theValue = user.getUserProfile().getFirstName();
        }else if(attributeName.equals("lastname")){
            theValue = user.getUserProfile().getLastName();
        } else{
            theValue = user.getSkills().toString();
        }
        return theValue;
    }

    //        }else if(attributeName.equals("skills")){
//            theValue = user.getSkills();
//        }else{
//            theValue = user.getEvents();
//        }
    public static ArrayList<User> findByValue(String value, Iterable<User> allUsers){

        ArrayList<User> userResults = new ArrayList<>();

        for(User user : allUsers){
            if(user.getUsername().toLowerCase().contains(value.toLowerCase())){
                userResults.add(user);
            }else if(user.getUserProfile().getFirstName().toLowerCase().contains(value.toLowerCase())){
                userResults.add(user);
            }else if(user.getUserProfile().getLastName().toLowerCase().contains(value.toLowerCase())){
                userResults.add(user);
            }else if(user.getSkills().toString().toLowerCase().contains(value.toLowerCase())){
                userResults.add(user);
            }
        }
        return userResults;
    }

    private static final Map<Integer, User> users = new HashMap<>();

    //get a single user
    public static User getBYId(Integer userId){
        return users.get(userId);
    }
}

