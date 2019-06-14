package sk.myProject.gamestudio.service;


import sk.myProject.gamestudio.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE score (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        points INTEGER NOT NULL,
        playedon TIMESTAMP NOT NULL
    );
     */

//INSERT INTO score (player, game, points, playedon) VALUES ('jaro', 'mines', 200, '2017-03-02 14:30')

//SELECT player, game, points, playedon FROM score WHERE game = 'mines' ORDER BY points DESC LIMIT 10;

public class ScoreServiceJDBC implements ScoreService {


    public static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "piatok123";

    private static final String CREATE_COMMAND = "CREATE TABLE score (id SERIAL,player VARCHAR(64) NOT NULL, points INTEGER NOT NULL, game VARCHAR(64) NOT NULL, played_on TIMESTAMP NOT NULL)";

    private static final String INSERT_COMMAND = "INSERT INTO score (player, points, game, played_on) VALUES (?, ?, ?, ?)";

    private static final String SELECT_COMMAND = "SELECT player, points, game, played_on FROM score WHERE game = ? ORDER BY points DESC";

    //"SELECT game, player, points, playedon FROM score WHERE game = ? AVG(points) DESC LIMIT 10;";


    @Override
    public void addScore(Score score) {

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(INSERT_COMMAND);
        ) {
            stmt.setString(1, score.getPlayer());
            stmt.setInt(2, score.getPoints());
            stmt.setString(3, score.getGame());
            stmt.setTimestamp(4, new Timestamp(score.getPlayedOn().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> getBestScores(String gameName) {
        List<Score> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(SELECT_COMMAND);
        ) {
            stmt.setString(1, gameName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Score score = new Score(
                            rs.getString(1), rs.getInt(2),
                            rs.getString(3), rs.getTimestamp(4));
                    results.add(score);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
