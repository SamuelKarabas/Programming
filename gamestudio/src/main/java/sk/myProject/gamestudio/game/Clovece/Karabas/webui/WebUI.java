package sk.myProject.gamestudio.game.Clovece.Karabas.webui;

import sk.myProject.gamestudio.service.CommentException;
import sk.myProject.gamestudio.service.RatingException;
import sk.myProject.gamestudio.service.ScoreService;
import sk.myProject.gamestudio.game.Clovece.Karabas.Board;
import sk.myProject.gamestudio.game.Clovece.Karabas.Game;

public class WebUI {


    public int counter = 0;
    private Board field;
    private ScoreService scoreService;


    public WebUI() {

        createField();
    }

    public void createField() {

        field = new Board("board");
    }

    public Board getField() {
        return field;
    }

    public int count() {
        counter = counter++;
        return counter;
    }


    public ViewState getViewTile(int row) throws CommentException, RatingException {

        String x = field.map.get(row).toString();
        Game game = new Game();

        State state;
        if (x.matches("-?(0|[1-9]\\d*)")) {

            state = State.closed;
            return new ViewState(state);
        }
        if (x.contains("H")) {
            state = State.notclosed;
            return new ViewState(state);
        }

        if (x.contains("P")) {
            int first_digit = Integer.parseInt(Character.toString(x.charAt(1))) - 1;
            int second_digit = Integer.parseInt(Character.toString(x.charAt(2))) - 1;
            if (first_digit == 1) {

                state = State.blue;
                return new ViewState(state);
            }

            if (first_digit == 2) {

                state = State.purple;
                return new ViewState(state);
            }
            if (first_digit == 3) {

                state = State.green;
                return new ViewState(state);
            }

            if (first_digit == 4) {


                state = State.home;

                return new ViewState(state);
            }
        }


        state = State.opened;
        return new ViewState(state);
    }


}
