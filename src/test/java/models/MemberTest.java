package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
    @Test
    public void newMember_instantiatesCorrectly() throws Exception {
        Team testTeam = new Team("A Team","First time");
        Member testMember = new Member(testTeam,"Tom","Bob","Mike","Jerry");
        assertEquals(true, testMember instanceof Member);
    }

    @Test
    public void newMember_savesTeamInfo_Team() {
        Team testTeam = new Team("A Team","First time");
        Member testMember = new Member(testTeam,"Tom","Bob","Mike","Jerry");
        assertEquals(testTeam, testMember.getTeam());

    }
}