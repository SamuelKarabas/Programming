package sk.myProject.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQuery(name = "Score.selectToScores", query = "select s from Score s where s.game=:game order by s.points desc")

public class Score implements Serializable, Comparable<Score> {
    private String game;
    @Id
    @GeneratedValue
    private int ident; //identifikator

    private String player;

    private int points;

    private Date playedOn;

    public Score() {

    }

    public Score(String player, int points, String game, Date playedOn) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedOn = playedOn;

    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }


    @Override
    public int compareTo(Score o) {
        if (o == null) return -1;
        return this.getPoints() - o.getPoints();
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }


}
