package models;

import org.junit.Test;

import java.util.ArrayList;

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
        Team testTeam = new Team("A Team", "First time");
        Member testMember = new Member(testTeam, "Tom", "Bob", "Mike", "Jerry");
        assertEquals(testTeam, testMember.getTeam());

    }

    @Test
    public void newMember_getMember_ArrayList() {
        Team testTeam = new Team("A Team", "First time");
        Member testMember = new Member(testTeam, "Tom", "Bob", "Mike", "Jerry");
        ArrayList<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("Tom");
        expectedOutput.add("Bob");
        expectedOutput.add("Mike");
        expectedOutput.add("Tim");
        assertEquals(expectedOutput,testMember.getMemberList());
    }

}