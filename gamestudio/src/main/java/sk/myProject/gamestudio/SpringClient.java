package sk.myProject.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import sk.myProject.gamestudio.service.*;
import sk.myProject.gamestudio.game.Clovece.Karabas.Game;
import sk.tuke.gamestudio.service.*;

/*
@SpringBootApplication
@Configuration*/
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"sk.tuke.gamestudio"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {


        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);

        //SpringApplication.run(SpringClient.class, args);

    }


    @Bean
    public CommandLineRunner runner(Game game) {

        return args -> game.begin();

    }

    @Bean
    public CommentService commentService() {

        return new CommentServiceRestClient();
    }


    @Bean
    public ScoreService scoreService() {

        return new ScoreServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceRestClient();

    }

    @Bean
    public Game game() throws CommentException, RatingException {
        return new Game();
    }
}























/*
@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class, args);
    }


    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public Game game() throws CommentException, RatingException {
        return new Game();
    }

    @Bean
    public CommandLineRunner runner(Main main) {
        return args -> main.play();
    }

    @Bean
    public Main main(){
        return new Main();
    }

    //  Game game=new Game();

}*/
