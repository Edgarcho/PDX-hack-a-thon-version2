package dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.rmi.server.ExportException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao  memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Member member = new Member("Edgar");
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId,member.getId());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = new Member("Edgar");
        memberDao.add(member);
        Team foundTeam = memberDao.findById(memberDao.getId());
        assertEquals(member, foundTeam);
    }

    @Test
    public void addedTeamsAreReturnedFromgetAll() throws Exception{
        Team team = new Team("A Team","Group of one");
        teamDao.add(team);
        assertEquals(1,teamDao.getAll().size());
    }

    @Test
    public void noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateChangesTeamName() throws Exception{
        String initialName = "A Team";
        Team team = new Team(initialName,"Group of one");
        teamDao.add(team);
        teamDao.update(team.getId(),"B Team");
        Team updateTeam = teamDao.findById(team.getId());
        assertNotEquals(initialName,updateTeam.getName());
    }

    @Test
    public void updateChangesTeamDescription() throws Exception {
        String initialDescription = "Group of one";
        Team team = new Team("A Team", initialDescription);
        teamDao.add(team);
        teamDao.updateDescription(team.getId(),"Group of Two");
        Team updateTeam = teamDao.findById(team.getId());
        assertNotEquals(initialDescription,updateTeam.getDescription());
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team = new Team("A team","Group of one");
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0,teamDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Team team = new Team("A Team","Group of one");
        Team otherTeam = new Team("B Team","Group of two");
        teamDao.add(team);
        teamDao.add(otherTeam);
        int daoSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(daoSize > 0 && daoSize > teamDao.getAll().size());
    }
}
}