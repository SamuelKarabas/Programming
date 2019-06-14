package sk.myProject.gamestudio.game.Clovece.Karabas;

import java.util.Scanner;

public class Input {

    private Game game;

    private Scanner kb = new Scanner(System.in);

    public Input(Game game) {
        this.game = game;
    }

    public void parseInput() {

        int input = kb.nextInt();

        if (input == 9) {
            game.getDice().roll();
            return;
        }

        game.moveWithFigure(input);
    }
}
