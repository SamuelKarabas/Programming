package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Rating;

public class RatingServiceJDBC implements RatingService {
    @Override
    public void setRating(Rating rating) throws RatingException {

    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        return 0;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return 0;
    }

 /*   public static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "piatok123";
    private static final String SET_RATING = " INSERT INTO rating (player, game, ratings, ratedon)  VALUES (?, ?, ?,?) ON CONFLICT (player,game)  DO NOTHING ";
    public static final String SELECT_AVERAGE =
            "SELECT AVG(ratings) FROM rating WHERE game=?";
    public static final String SELECT_RATING =
            "SELECT ratings FROM rating WHERE game=? AND player=?";


    @Override
    public void setRating(Rating rating) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SET_RATING)) {
                ps.setString(1,rating.getPlayer());
                ps.setString(2,rating.getGame());
                ps.setInt(3,rating.getRating());
                ps.setDate(4,new Date(rating.getRatedon().getTime()));
                ps.executeUpdate();

            }
        }catch (SQLException e) {
            throw new RatingException("Error to set rating",e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int result = 0;
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_AVERAGE)) {
                ps.setString(1,game);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getInt(1);

                }
            }
        }catch (SQLException e) {
            throw new RatingException("Error to get avarage rating",e);
        }
        return result;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        int result = 0;
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_RATING)) {
                ps.setString(1,game);
                ps.setString(2,player);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getInt(1);

                }
            }
        }catch (SQLException e) {
            throw new RatingException("Error to get rating",e);
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Rating rating = new Rating("Natalka", "plumber", 1, new java.util.Date());
        Rating rating4 = new Rating("Natalka", "plumber", 4, new java.util.Date());
        RatingService ratingService = new RatingServiceJDBC();
        ratingService.setRating(rating);
        ratingService.setRating(rating4);
        System.out.println(ratingService.getRating("plumber","Natalka"));
        System.out.println(ratingService.getAverageRating("plumber"));

    }*/
}
