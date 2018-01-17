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
        List<String> Members = new ArrayList<String>();
        assertEquals(Members.size(), 0);
    }

    @Test
    public void newMember_addNameToArray_Tom() {
        Team testTeam = new Team("A Team","First time");
        List<String> Members = new ArrayList<String>();
        Members.add("Tom");
        assertEquals(Members, testTeam.newMembers("Tom"));
    }

    @Test
    public void getMembers_getTeamMemberList_ArrayList() {
        Team testTeam = new Team("A Team", "First time");
        List<String> Members = new ArrayList<String>();
        Members.add("Tom");
        Members.add("Jim");
        assertEquals(Members,testTeam.getMembers());
    }
}