import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

        //get: show all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            List<Member>members = memberDao.getAll();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }

    //get a specific team (and the members it contains)
    // /teams/:team_id

    //get: show a specific member in a specific team
    // /teams/:team_id/members/:member_id

    //get: show a form to create a new team
    //  /teams/new

    //post: process a form to create a new team
    //  /teams

    //get: show a form to update team
    //  /teams/update

    //post: process a form to update team
    //  /teams/update

    //get: show a form to update description in team
    //  /teams/updateDescription

    //post: process a form to update description in team
    //  /teams/updateDescription

    //get: delete a team and members it contains
    //  /teams/:teams_id/delete

    //get: delete all teams and all members

    //get: show all tasks in all categories and show all categories



    /*
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            Team newTeam = new Team(teamName,teamDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team
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

        //post: process member form
        post("/teams/:id/member",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("memberName");
            int idOfTeamToAdd = Integer.parseInt(request.params(":id"));
            Team editTeam = Team.findById(idOfTeamToAdd);
            editTeam.newMembers(memberName);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
    }
    */
}
