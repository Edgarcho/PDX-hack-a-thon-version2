package models;

import java.util.ArrayList;

public class Member {



    private Team teamInfo;

    public Member(Team team, String firstMember, String secondMember, String thirdMember, String fourthMember) {
        teamInfo = team;
    }
    public Team getTeam(){
        return teamInfo;
    }
    public ArrayList<String>addMember(String firstMember){
    ArrayList<String>result = new ArrayList<String>();
    result.add(firstMember);
    return null;
    }
}
