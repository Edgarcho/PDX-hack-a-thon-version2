package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Team testTeam = new Team("A Team","First time");
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void newTeam_getName_ATeam() {
        Team testTeam = new Team("A Team","First time");
        assertEquals("A Team", testTeam.getName());
    }

    @Test
    public void newTeam_getDescription_Firsttime() {
        Team testTeam = new Team("A Team","First time");
        assertEquals("First time",testTeam.getDescription());

    }

    @Test
    public void newMember_addMemberToTeam_Tom() {
        Team testTeam = new Team("A Team","First time");
        assertEquals("Tom",testTeam.newMember("Tom"));
    }
}