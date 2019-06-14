package sk.myProject.gamestudio.server.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.myProject.gamestudio.game.Clovece.Karabas.Game;
import sk.myProject.gamestudio.game.Clovece.Karabas.webui.WebUI;
import sk.myProject.gamestudio.service.CommentException;
import sk.myProject.gamestudio.service.RatingException;
import sk.myProject.gamestudio.service.ScoreService;

///http://localhost:8080/Clovece-Karabas
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CloveceKarabasController {


    public final WebUI webUI; // = new WebUI();
    public final Game game;
    private final ScoreService scoreService;

    public CloveceKarabasController(ScoreService scoreService) throws CommentException, RatingException {
        this.scoreService = scoreService;
        this.webUI = new WebUI();
        game = new Game();
    }

    @RequestMapping("/Clovece-Karabas")
    public String mines(@RequestParam(value = "command", required = false) String command,
                        @RequestParam(value = "row", required = false) String row,
                        @RequestParam(value = "column", required = false) String column,
                        Model model) {


        model.addAttribute("webUI", webUI);

        model.addAttribute("game", game);

        return "Clovece-Karabas";

    }

}
