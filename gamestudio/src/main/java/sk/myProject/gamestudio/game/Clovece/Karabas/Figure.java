package sk.myProject.gamestudio.game.Clovece.Karabas;


public class Figure {

    private State state;
    private String symbol;
    private int position;
    private Player player;
    private boolean isHome;
    //used to determine whether figure should go to home
    private int relativePosition;

    public Figure(String symbol, Player player) {
        this.symbol = symbol;
        this.player = player;
        state = State.START;
        this.relativePosition = 0;
    }

    public State getState() {
        return state;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPosition() {
        return position;
    }

    public void start() {

        if (state != State.START) {
            return;
        }


        this.position = player.getStarting_position();
        player.setIs_on_start(true);

        // this.position = (Integer.parseInt(symbol));
        state = State.OUTSIDE;

        System.out.println("hrac polozil figurku");
    }

    public void kick() {
        if (state != State.OUTSIDE) {
            return;
        }

        state = State.START;
    }

    public void home() {
        if (state != State.OUTSIDE) {
            return;
        }

        state = State.HOME;
    }

    public void move(Dice dice) {

        if (state != State.OUTSIDE) {
            return;
        }

//        if (player.isIs_on_start()) {
//            player.setIs_on_start(false);
//        }

        this.position += dice.getValue();
        this.position = this.position >= 52 ? this.position - 52 : this.position;

        this.relativePosition += dice.getValue();


        if (relativePosition > 52) {
            this.home();
        }

        System.out.println("Hrac sa pohol o " + dice.getValue());
    }

    public boolean isHome() {
        return isHome;
    }

    public int getRelativePosition() {
        return relativePosition;
    }

    public enum State {
        HOME,
        START,
        OUTSIDE
    }
}
