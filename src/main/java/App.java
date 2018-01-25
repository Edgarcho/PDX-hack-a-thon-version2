import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/hackatron.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        //get: show all members in all teams and show all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all teams and all members
        get("/teams/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            memberDao.clearAllMembers();
            teamDao.clearAllTeams();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team(name, description);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show member form
        get("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new member form
        post("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            String memberName = request.queryParams("name");
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            Member newMember = new Member(memberName, teamId);
            memberDao.add(newMember);
            model.put("member", newMember);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a team
        get("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("editTeam", true);
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post; process a form to update a team and members it contains
        post("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfTeamToEdit = Integer.parseInt(request.queryParams("editTeamId"));
            String newName = request.queryParams("newTeamName");
            teamDao.update(teamDao.findById(idOfTeamToEdit).getId(), newName);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/members/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            List<Member> allmember = memberDao.getAll();
            model.put("members", allmember);
            model.put("editMember", true);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a member
        post("/members/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            String newName = request.queryParams("name");
            int newTeamId = Integer.parseInt(request.queryParams("teamId"));
            int memberToEditId = Integer.parseInt(request.queryParams("memberToEditId"));
            Member editMember = memberDao.findById(memberToEditId);
            memberDao.update(memberToEditId, newName, newTeamId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team and members it contains
        get("/teams/:teamId", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfTeamToFind = Integer.parseInt(request.params("teamId"));
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> allMembersByTeam = teamDao.getAllMembersByTeam(idOfTeamToFind);
            model.put("members", allMembersByTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a team and members it contains
        get("/teams/:team_id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfTeamToDelete = Integer.parseInt(request.params("team_id"));
            Team deleteTeam = teamDao.findById(idOfTeamToDelete);
            teamDao.deleteById(idOfTeamToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual member
        get("/teams/:team_id/members/:member_id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfMemberToFind = Integer.parseInt(request.params("member_id"));
            Member deleteMemebr = memberDao.findById(idOfMemberToFind);
            memberDao.deleteById(idOfMemberToFind);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}




