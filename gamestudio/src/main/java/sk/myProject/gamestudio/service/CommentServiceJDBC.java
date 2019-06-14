package sk.myProject.gamestudio.service;


import sk.myProject.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService {

    private static final String URL = "jdbc:postgresql://localhost/gamestudio";
    private static final String USER = "postgres";
    private static final String PASSWORD = "piatok123";
    private static final String INSERT_COMMENT = "INSERT INTO comment (game, player, comment, commentedon) VALUES (?, ?, ?, ?)";
    private static final String SELECT_COMMENT =
            "SELECT  player, game,comment, commentedon FROM comment WHERE game = ?";

    public static void main(String[] args) throws Exception {
        Comment comment = new Comment("Marek", "plumber", "si to uzastne dal", new java.util.Date());
        CommentService commentService = new CommentServiceJDBC();
        commentService.addComment(comment);
        System.out.println(commentService.getComments("plumber"));


    }

    @Override
    public void addComment(Comment comment) throws CommentException {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT)) {

                ps.setString(1, comment.getGame());
                ps.setString(2, comment.getPlayer());
                ps.setString(3, comment.getComment());
                ps.setDate(4, new Date(comment.getCommented_on().getTime()));
                ps.executeUpdate();

            }
        } catch (SQLException e) {
            throw new CommentException("Error to save comment", e);
        }

    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_COMMENT)) {
                ps.setString(1, game);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Comment comment = new Comment(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getTimestamp(4)
                        );
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading score", e);
        }
        return comments;
    }
}
