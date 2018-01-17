package models;

public class Member {

    private Team teamInfo;

    public Member(Team team, String firstMember, String secondMember, String thirdMember, String fourthMember) {
        teamInfo = team;
    }
    public Team getTeam(){
        return teamInfo;
    }
}
