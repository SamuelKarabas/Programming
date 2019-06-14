package sk.myProject.gamestudio.game.Clovece.Karabas;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private String symbol;
    private int Pocet_figurok;
    private List<Figure> figures = new ArrayList<>();
    private int figure_home;
    private int starting_position;

    private boolean is_on_start;

    private String status;


    public Player(String name, String symbol, int pocet_figurok) {

        this.status = "Ready to play!";

        this.symbol = symbol;
        this.name = name;

        this.Pocet_figurok = pocet_figurok;

        figure_home = pocet_figurok;
        for (int i = 0; i < pocet_figurok; i++) {
            figures.add(new Figure(symbol, this));
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public boolean hasFigureStart(int index) {
        return figures.get(index).getState() == Figure.State.START;
    }

    public boolean hasFigureHome(int index) {
        return figures.get(index).getState() == Figure.State.HOME;
    }

    public boolean hasWon() {

        for (Figure figure : figures) {
            if (figure.getState() != Figure.State.HOME) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getStarting_position() {
        return starting_position;
    }

    public void setStarting_position(int starting_position) {
        this.starting_position = starting_position;
    }

    public boolean isIs_on_start() {
        return is_on_start;
    }

    public void setIs_on_start(boolean is_on_start) {
        this.is_on_start = is_on_start;
    }
}
