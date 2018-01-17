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
        List<String> teamMembers = new ArrayList<String>();
        assertEquals(teamMembers.size(), 0);
    }

    @Test
    public void newMember_addNameToArray_Tom() {
        Team testTeam = new Team("A Team","First time");
        assertEquals("Tom", testTeam.newMembers("Tom"));
    }

    @Test
    public void newMember_getMemberList_listOfMember() {
        Team testTeam = new Team("A Team", "First time");
        testTeam.newMembers("Tom");
        assertEquals("Tom",testTeam.getTeamMembers());
    }
}
