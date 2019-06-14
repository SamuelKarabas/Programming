package sk.myProject.gamestudio.game.Clovece.Karabas;

import java.util.Random;

public class Dice {

    private int value;
    private Random rand = new Random();

    public Dice() {
        this.value = 6;
    }

    public int roll() {

        var val = getValue();

        setValue(rand.nextInt((6 - 1) + 1) + 1);

        if (getValue() == val) {
            roll();
        }

        return getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
