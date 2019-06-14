package sk.karabas.kpi.oop.game.actions;

import sk.karabas.kpi.oop.game.Reactor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int incr;

    public PerpetualReactorHeating(int incr)
    {
        this.setIncr(incr);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        this.getActor().increaseTemperature(getIncr());
    }

    public int getIncr()
    {
        return incr;
    }

    public void setIncr(int incr)
    {
        this.incr = incr;
    }
}
