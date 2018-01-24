
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

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;


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

        //post: process new member form
        post("/members/news",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeam = teamDao.getAll();
            model.put("teams", allTeam);
            String memberName = request.queryParams("name");
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            Member newMember = new Member(memberName,teamId);
            memberDao.add(newMember);
            model.put("member", newMember);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

/*
        //get: delete all teams
        get("teams/delete", (req, res) -> {
            Map<String, Object> model = HashMap<String, Object>();
            teamDao.clearAllTeams();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(request.params(":id"));
            Team foundTeam = Team.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show update team form
        get("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(request.params(":id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process update team form
        post("/teams/:id/update",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(request.params(":id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(newName);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show member form
        get("/teams/:id/member", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToAdd = Integer.parseInt(request.params(":id"));
            Team editTeam = Team.findById(idOfTeamToAdd);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


         {{#each members}}
                    <a href="/teams/{{teamId}}/members/{{id}}" class="list-group-item list-group-item-action">read more..</a>
                {{/each}}

         */
    }
}