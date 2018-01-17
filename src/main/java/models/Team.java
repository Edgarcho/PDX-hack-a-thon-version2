package models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String description;

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
    public ArrayList newMember(String newMemberName){
        return null ;
    }
}
