package models;

public class Member {
    private String name;
    private int id;
    private int teamId;

    public Member(String name, int teamId){
        this.name = name;
        this.teamId = teamId;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getTeamId(){
        return teamId;
    }
    public void setTeamId(){
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        return name.equals(member.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        result = 31 * result + teamId;
        return result;
    }
}
