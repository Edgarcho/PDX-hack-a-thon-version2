package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
        Member member = new Member("Edgar",1);
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = new Member("Edgar",1);
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void addedMembersAreReturnedFromgetAll() throws Exception {
        Member member = new Member("Edgar",1);
        memberDao.add(member);
        assertEquals(1,memberDao.getAll().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception{
        assertEquals(0,memberDao.getAll().size());
    }

    @Test
    public void UpdateChangesMemberName() throws Exception{
        String initialName = "Edgar";
        Member member = new Member(initialName,1);
        memberDao.add(member);
        memberDao.update(member.getId(),"Bob",1);
        Member updatedMember = memberDao.findById(member.getId());
        assertNotEquals(initialName, updatedMember.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectMember() throws Exception {
        Member member = new Member("Edgar",1);
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Member member = new Member("Edgar",1);
        Member otherMember = new Member("Edgar",1);
        memberDao.add(member);
        memberDao.add(otherMember);
        int daoSize = memberDao.getAll().size();
        memberDao.clearAllMembers();
        assertTrue(daoSize > 0 && daoSize > memberDao.getAll().size());
    }

    @Test
    public void teamIdIsReturnedCorrectly() throws Exception {
        Member member = new Member("Edgar",1);
        int originalTeamId = member.getTeamId();
        memberDao.add(member);
        assertEquals(originalTeamId, memberDao.findById(member.getId()).getTeamId());
    }
}