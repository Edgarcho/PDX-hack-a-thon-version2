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
}