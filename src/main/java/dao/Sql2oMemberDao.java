package dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (name) VALUES (:name)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public Team findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE teams SET name = :name WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateDescription(int id, String description) {
        String sql = "Update teams SET description = :description WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM teams WHERE id=:id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTeams() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
