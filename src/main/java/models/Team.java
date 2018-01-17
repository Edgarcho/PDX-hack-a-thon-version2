package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private List<String> Members = new ArrayList<String>();

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public List newMembers(String memberName){
        Members.add(memberName);
        return Members;
   }
   public List<String>getMembers(){
        return null;
    }
}

