package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private List<String> members = new ArrayList<String>();
    private static ArrayList<Team> instances = new ArrayList<>();
    private int id;

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
        instances.add(this);
        this.id = instances.size();
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (!name.equals(team.name)) return false;
        return description.equals(team.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public List newMembers(String memberName){
        members.add(memberName);
        return members;
   }
    public List<String>getMembers(){
        return members;
    }
    public static ArrayList<Team>getAll(){
        return instances;
    }
    public static void clearAllTeams(){
        instances.clear();
    }
    public int getId(){
        return id;
    }
    public static Team findById(int id){
        return instances.get(id-1);
    }
    public void update(String name){
        this.name = name;
    }
}

