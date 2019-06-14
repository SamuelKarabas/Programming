package sk.myProject.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.myProject.gamestudio.game.Clovece.Karabas.Game;
import sk.myProject.gamestudio.server.webservice.GameService;
import sk.myProject.gamestudio.service.*;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
@EntityScan({"sk.tuke.gamestudio.entity"})
public class GameStudioServer {

    public static Game game;

    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class, args);
    }

    @Bean(name = "scoreServiceServer")
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean(name = "ratingServiceServer")
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }

    @Bean(name = "commentServiceServer")
    public CommentService commentService() {
        return new CommentServiceJPA();
    }

    @Bean(name = "gameServiceServer")
    public GameService gameService()  throws CommentException, RatingException {
        return new GameService();
    }
}
