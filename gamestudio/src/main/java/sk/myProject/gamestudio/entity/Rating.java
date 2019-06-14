package sk.myProject.gamestudio.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;


@Entity

@NamedQuery(name = "Rating.selectToRatings", query = "SELECT AVG(ratings) FROM Rating WHERE game= :game")
@NamedQuery(name = "Rating.getRatings", query = "SELECT r FROM Rating r WHERE game=:game AND player=:player")


public class Rating {
    private String player;
    private String game;
    private int ratings;
    private Date ratedon;

    @Id
    @GeneratedValue
    private int id; //identifikator

    public Rating() {

    }

    public Rating(String player, String game, int ratings, Date ratedon) {
        this.player = player;
        this.game = game;
        this.ratings = ratings;
        this.ratedon = ratedon;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int rating) {
        this.ratings = rating;
    }

    public Date getRatedon() {
        return ratedon;
    }

    public void setRatedon(Date ratedon) {
        this.ratedon = ratedon;
    }

    public int getIdent() {
        return id;
    }

    public void setIdent(int ident) {
        this.id = id;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", rating=").append(ratings);
        sb.append(", ratedon=").append(ratedon);
        sb.append('}');
        return sb.toString();
    }
}
