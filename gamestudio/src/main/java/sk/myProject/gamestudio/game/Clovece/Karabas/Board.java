package sk.myProject.gamestudio.game.Clovece.Karabas;

import sk.myProject.gamestudio.entity.Comment;
import sk.myProject.gamestudio.entity.Score;
import sk.myProject.gamestudio.service.CommentException;
import sk.myProject.gamestudio.service.CommentServiceJPA;
import sk.myProject.gamestudio.service.RatingServiceJPA;
import sk.myProject.gamestudio.service.ScoreServiceJPA;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {

    public List<Object> map;
    CommentServiceJPA commentService = new CommentServiceJPA();
    RatingServiceJPA ratingService = new RatingServiceJPA();
    ScoreServiceJPA scoreService = new ScoreServiceJPA();
    String name;

    public Board(String name) {
        setName(name);/*
        map = List.of(
                '-', '-', '-', '-', 9, 10, 11, '-', '-', '-', '-',
                '-', "P11", "P12", '-', 8, "H31", 12, '-', "P31", "P32", '-',
                '-', "P14", "P13", '-', 7, "H32", 13, '-', "P34", "P33", '-',
                '-', '-', '-', '-', 6, "H33", 14, '-', '-', '-', '-',
                1, 2, 3, 4, 5, "H34", 15, 16, 17, 18, 19,
                40, "H11", "H12", "H13", "H14", '-', "H44", "H43", "H42", "H41", 20,
                39, 38, 37, 36, 35, "H21", 25, 24, 23, 22, 21,
                '-', '-', '-', '-', 34, "H21", 26, '-', '-', '-', 'S',
                '-', "P21", "P22", '-', 33, "H21", 27, '-', "P41", "P42", '-',
                '-', "P23", "P24", '-', 32, "H21", 28, '-', "P43", "P44", '-',
                '-', '-', '-', '-', 31, 30, 29, '-', '-', '-', '-'
        );*/
        map = List.of(
                '-', '-', '-', '-', '-', '-', 23, 24, 25, '-', '-', '-', '-', "P21", "P22",
                '-', '-', '-', '-', '-', '-', 22, "H21", 26, '-', '-', '-', '-', "P23", "P24",
                '-', '-', '-', '-', '-', '-', 21, "H22", 27, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 20, "H23", 28, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 19, "H24", 29, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 18, '-', 30, '-', '-', '-', '-', '-', '-',
                12, 13, 14, 15, 16, 17, '-', '-', '-', 31, 32, 33, 34, 35, 36,
                11, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 37,
                10, 9, 8, 7, 6, 5, '-', '-', '-', 43, 42, 41, 40, 39, 38,
                '-', '-', '-', '-', '-', '-', 4, '-', 44, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 3, "H14", 45, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 2, "H13", 46, '-', '-', '-', '-', '-', '-',
                '-', '-', '-', '-', '-', '-', 1, "H12", 47, '-', '-', '-', '-', '-', '-',
                "P11", "P12", '-', '-', '-', '-', 0, "H11", 48, '-', '-', '-', '-', '-', '-',
                "P13", "P14", '-', '-', '-', '-', 51, 50, 49, '-', '-', '-', '-', '-', '-'
        );
    }

    public List<Object> getMap() {
        return map;
    }

    public void setMap(List<Object> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return map.size();
    }

    // ADD COMMENT TO DATABASE AND SHOW ALL COMMENTS
    public void addCommentAndShowComments(String playerName) {
        System.out.println("Please give us your feedback and write a comment: ");
        Scanner input = new Scanner(System.in);
        String comment = input.nextLine();
        Date date = new Date();

        Comment newComment = new Comment(playerName, "Clovece", comment, date);
        try {
            commentService.addComment(newComment);


            System.out.println("_______Comments_______");
            for (Comment item : commentService.getComments("Clovece")) {
                System.out.println("Name: " + item.getPlayer() + " Commented on:  " + item.getComment());
                System.out.println(item.getComment());
                System.out.println();
            }

        } catch (CommentException e) {
            e.printStackTrace();
        }

    }

    // ADD SCORE TO DATABASE AND SHOW ALL SCORES
    public void addScoreAndShowScore(String playerName, int score) {
        Date date = new Date();

        Score newScore = new Score(playerName, score, "Clovece", date);
        scoreService.addScore(newScore);

        System.out.println("_______Scores_______");
        for (Score item : scoreService.getBestScores("PegSolitaire")) {
            System.out.println("Name: " + item.getPlayer() + " Played on:  " + item.getPlayedOn());
            System.out.println(item.getPoints());
            System.out.println();
        }
    }

    // ADD RATING TO DATABASE AND SHOW ALL RATINGS
    public void addRatingAndShowAverage(String playerName) {
        System.out.println("Rate the game:  ");
        Scanner scan = new Scanner(System.in);
        int rating = 8;

        while (rating != 1 && rating != 2 && rating != 3 && rating != 4 && rating != 5) {
            try {
                rating = scan.nextInt();
                if (rating != 1 && rating != 2 && rating != 3 && rating != 4 && rating != 5) {
                    System.out.println("Rate from 1 to 5");
                }
            } catch (Exception e) {
                System.out.println("Rate from 1 to 5");
                scan.next();
            }
        }

    }

    public Figure getFigureByIndex(Game game, int index) {
        for (Player player : game.getPlayers()) {
            for (Figure figure : player.getFigures()) {

                if (figure.getState() != Figure.State.OUTSIDE) {
                    continue;
                }

                if (figure.getPosition() == index) {
                    return figure;
                }
            }
        }

        return null;
    }


    public void render(Game game) {

        for (int i = 0, j = 0; i < map.size(); i++) {
            // System.out.print(map.get(i) + " ");

            String x = map.get(i).toString();

            if (x.matches("-?(0|[1-9]\\d*)")) {
                // vytvorim premennu na ulozenie cisla,
                //
                Figure figure = getFigureByIndex(game, Integer.parseInt(x));

                System.out.print("[" + (figure == null ? " " : figure.getSymbol()) + "]");
            } else if (x.contains("P")) {

                System.out.print("(");

                int first_digit = Integer.parseInt(Character.toString(x.charAt(1))) - 1;
                int second_digit = Integer.parseInt(Character.toString(x.charAt(2))) - 1;


                //Simplified
                game.getPlayers().get(first_digit).setStarting_position((first_digit * 10) + 1);


                System.out.print(
                        game.getPlayers().get(first_digit).hasFigureStart(second_digit)
                                ? game.getPlayers().get(first_digit).getSymbol()
                                : " "
                );


                System.out.print(")");
            } else if (x.contains("H")) {
                System.out.print(" ");

                int first_digit = Integer.parseInt(Character.toString(x.charAt(1))) - 1;
                int second_digit = Integer.parseInt(Character.toString(x.charAt(2))) - 1;

                System.out.print(
                        game.getPlayers().get(first_digit).hasFigureHome(second_digit)
                                ? game.getPlayers().get(first_digit).getSymbol()
                                : "#"
                );

                System.out.print(" ");
            } else {
                System.out.print("   ");
            }

            if (++j == 11) {
                j = 0;
                System.out.println();
            }
        }
    }
}
