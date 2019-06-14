package sk.myProject.gamestudio.server.webservice;

import sk.myProject.gamestudio.server.GameStudioServer;
import sk.myProject.gamestudio.service.CommentException;
import sk.myProject.gamestudio.service.RatingException;
import sk.myProject.gamestudio.game.Clovece.Karabas.Figure;
import sk.myProject.gamestudio.game.Clovece.Karabas.Game;
import sk.myProject.gamestudio.game.Clovece.Karabas.Player;
import sk.myProject.gamestudio.server.JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("game")
public class GameService {

    private Game instance;

    private void newGame() throws CommentException, RatingException {
        GameStudioServer.game = new Game();

        GameStudioServer.game.setPocet(2);

        GameStudioServer.game.getPlayers().add(new Player("A", "A", 4));
        GameStudioServer.game.getPlayers().add(new Player("B", "B", 4));

        GameStudioServer.game.nextPlayer();
        GameStudioServer.game.nextPlayer();

        GameStudioServer.game.setNextPlayerStatus("It's not your turn.");
    }

    public GameService() throws CommentException, RatingException {
        if (GameStudioServer.game == null) {
            this.newGame();
        }
        this.instance = GameStudioServer.game;
    }

    @GET
    @Path("/init")
    @Produces("application/json")
    public String init() throws CommentException, RatingException {
        this.newGame();
        return GameStudioServer.game.getStatus();
    }

    @GET
    @Path("/roll/{player}")
    @Produces("application/json")
    public String roll(@PathParam("player") int player) {

        if (instance.getCurrentPlayerIndex() != player) {
            instance.setNextPlayerStatus("It's not your turn..");
            return JSON
                    .start("state", false)
                    .toString();

        }

        var dice = instance.getDice().roll();

        instance.setStatus("You rolled " + dice);

        JSON data = JSON
                .start("dice", dice);
        //.parse("game", game);

        return JSON
                .start("state", true)
                .parse("data", data)
                .toString();
    }

    @GET
    @Path("/move/{player}/{figure}")
    @Produces("application/json")
    public String move(@PathParam("player") int player, @PathParam("figure") int figure) {

        if (instance.getCurrentPlayerIndex() != player) {
            System.out.println(instance.getCurrentPlayerIndex() + "|" + player);
            instance.setNextPlayerStatus("It's not your turn.. ");
            return JSON
                    .start("state", false)
                    .toString();
        }

        var dice = instance.getDice().getValue();

        Figure f = instance.getCurrentPlayer().getFigures().get(figure);
        if (dice != 6 && f.getState() == Figure.State.START) {
            System.out.println(instance.getCurrentPlayerIndex() + "|" + player);
            instance.setStatus("You need to roll 6! ");
            return JSON
                    .start("state", false)
                    .toString();
        }

        instance.setStatus("You moved with a figure.");

        instance.moveWithFigure(figure + 1);
        instance.nextPlayer();
        instance.setStatus("Roll the dice.");

        JSON data = JSON
                .start("dice", dice);
        //.parse("game", game);

        return JSON
                .start("state", true)
                .parse("data", data)
                .toString();
    }

    @GET
    @Path("/sync")
    @Produces("application/json")
    public String sync() {

        JSON players = JSON.array();
        JSON sync = JSON.array();

        for (var i = 0; i < instance.getPlayers().size(); i++) {
            players.parse(JSON
                    .start("player", i)
                    .parse("status", instance.getPlayers().get(i).getStatus())
            );

            for (var j = 0; j < instance.getPlayers().get(i).getFigures().size(); j++) {
                var f = instance.getPlayers().get(i).getFigures().get(j);
                sync.parse(JSON
                        .start("player", i)
                        .parse("index", j)
                        .parse("position", f.getState() != Figure.State.START ? f.getRelativePosition() + 1 : 0)
                        .parse("realPosition", f.getPosition())
                        .parse("state", f.getState().toString())
                );
            }
        }


        JSON game = JSON
                .start("dice", instance.getDice().getValue())
                .parse("currentPlayer", instance.getCurrentPlayerIndex())
                .parse("players", players);

        JSON data = JSON
                .start("sync", sync)
                .parse("game", game);

        return JSON
                .start("state", true)
                .parse("message", instance.getStatus())
                .parse("data", data)
                .toString();
    }
}
