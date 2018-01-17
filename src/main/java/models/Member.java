package models;
import java.util.ArrayList;

public class Member {
    private ArrayList<String>memberList = new ArrayList<String>();
    private Team teamInfo;

    public Member(Team team, String firstMember, String secondMember, String thirdMember, String fourthMember) {
        teamInfo = team;
        memberList.add(firstMember);
        memberList.add(secondMember);
        memberList.add(thirdMember);
        memberList.add(fourthMember);
    }

    public Team getTeam(){
        return teamInfo;
    }
    public ArrayList getMemberList(){
        return memberList;
    }
}
