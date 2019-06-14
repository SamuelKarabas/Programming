package sk.myProject.gamestudio.game.Clovece.Karabas;

import org.springframework.beans.factory.annotation.Autowired;
import sk.myProject.gamestudio.service.*;
import sk.tuke.gamestudio.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public Board board;
    private Scanner kb = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();
    private Input input;
    private Dice dice;
    private int pocet;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;

    private Player currentPlayer;
    private int currentPlayerIndex = 0;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

        if (getCurrentPlayer() != null) {
            getCurrentPlayer().setStatus(status);
        }
    }

    public void setNextPlayerStatus(String status) {
        if (players.size() < 2) {
            return;
        }

        if (currentPlayerIndex == 0) {
            players.get(1).setStatus(status);
        } else {
            players.get(0).setStatus(status);
        }
    }

    public Game() throws CommentException, RatingException, ScoreException {

        this.setStatus("New game ready.");

        this.dice = new Dice();
        this.board = new Board("Hracia plocha");
        this.input = new Input(this);
    }

    public void begin() throws CommentException, RatingException {
        Start();



        Loop();
    }

    public Dice getDice() {
        return dice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void Start() throws CommentException, RatingException, ScoreException {
        setPocet(4);

        for (int i = 0; i < getPocet(); i++) {
            System.out.println("Meno hraca " + (i + 1) + ": ");
            String name = kb.next();


            String symbol = Integer.toString(i + 1);//kb.next();


            players.add(new Player(name, symbol, getPocet()));
        }

        System.out.println();

        nextPlayer(); //nastavi aktualneho hraca na prveho
    }

    public void Loop() {

        dice.setValue(6); //nastaví hodnotu na 6

        while (!hasWon()) {

            //kocka
            dice.roll();

            System.out.println("Aktualny hrac je: " + currentPlayer.getName());
            System.out.println("Kocka: " + dice.getValue());
            System.out.println();

            //zobrazí mapu
            getBoard().render(this);

            //spracovanie vstupu
            input.parseInput();

            //ak sa má nastaviť ťah na dalšieho hráča
            nextPlayer();
        }

        getBoard().render(this);

        System.out.println("Vyhral hrac " + currentPlayer.getName());
    }

    public Player getCurrentPlayer() {

        if (players.size() == 0) {
            return null;
        }

        return players.get(currentPlayerIndex);
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void moveWithFigure(int index) {
/*
        if (index <= 0 || index > getPocet()) {
            return;
        }
        */

        Figure figure = getCurrentPlayer().getFigures().get(index - 1);

        if (dice.getValue() == 6 && figure.getState() == Figure.State.START) {
            figure.start();
            return;
        }

        figure.move(dice);
    }

    public void nextPlayer() {
        currentPlayer = players.get(currentPlayerIndex++);
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }

    public boolean hasWon() {

        for (Player player : players) {

            if (player.hasWon()) {
                getBoard().addCommentAndShowComments(getPlayers().toString());
                getBoard().addScoreAndShowScore(getPlayers().toString(), 100);
                getBoard().addRatingAndShowAverage(getPlayers().toString());
                return true;
            }
        }

        return false;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }
}
/*
scoreService.addScore(new Score("aaa",12,"clovece",new java.util.Date()));
List <Score> a=scoreService.getBestScores("clovece");
        for (Score sss:a
             ) {
            System.out.println(sss);
        }
      Rating rating=new Rating("abc","aaaa",12, new java.util.Date());
        Rating rating2=new Rating("efg","aaaa",10, new java.util.Date());
        Rating rating3=new Rating("ijk","aaaa",8, new java.util.Date());
        Comment comment=new Comment("jozko","hra","dobra hra",new java.util.Date());
        ratingService.setRating(rating);
        ratingService.setRating(rating2);
        ratingService.setRating(rating3);
       // commentService.addComment(comment);
        List comments=commentService.getComments("hra");


        System.out.println(">>>>"+comments.get(0).toString());
int vysledok=ratingService.getAverageRating("aaaa");
      int vysledok2=ratingService.getRating("aaaa","abc");
*/