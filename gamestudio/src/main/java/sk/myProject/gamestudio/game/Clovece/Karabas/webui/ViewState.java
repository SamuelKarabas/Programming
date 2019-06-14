package sk.myProject.gamestudio.game.Clovece.Karabas.webui;

public class ViewState {

    public State state;

    public ViewState(State state) {
        this.state = state;

    }

    public boolean isColor() {
        if (state == State.home || state == State.blue || state == State.green || state == State.purple) {
            return true;
        }
        return false;
    }

    public boolean isBlue() {
        if (state == State.blue) {
            return true;
        }
        return false;
    }

    public State myState() {

        return State.blue;

    }


    public boolean isPurple() {
        if (state == State.purple) {
            return true;
        }
        return false;
    }

    public boolean isGreen() {
        if (state == State.green) {
            return true;
        }
        return false;
    }

    public boolean isRed() {
        if (state == State.home) {
            return true;
        }
        return false;
    }
}
