package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void newTeam_instantiatesCorrectly() throws Exception {
        Team testTeam = new Team("A Team", "First time");
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void newTeam_getName_ATeam() {
        Team testTeam = new Team("A Team", "First time");
        assertEquals("A Team", testTeam.getName());
    }

    @Test
    public void newTeam_getDescription_Firsttime() {
        Team testTeam = new Team("A Team", "First time");
        assertEquals("First time", testTeam.getDescription());

    }

    @Test
    public void newTeam_emptyArrayList() {
        Team testTeam = new Team("A Team", "First time");
        List<String> members = new ArrayList<String>();
        assertEquals(members.size(), 0);
    }

    @Test
    public void newMember_addNameToArray_Tom() {
        Team testTeam = new Team("A Team","First time");
        List<String> members = new ArrayList<String>();
        members.add("Tom");
        assertEquals(members, testTeam.newMembers("Tom"));
    }

    @Test
    public void getMembers_getTeamMemberList_ArrayList() {
        Team testTeam = new Team("A Team", "First time");
        List<String> members = new ArrayList<String>();
        members.add("Tom");
        testTeam.newMembers("Tom");
        assertEquals(members,testTeam.getMembers());
    }
}