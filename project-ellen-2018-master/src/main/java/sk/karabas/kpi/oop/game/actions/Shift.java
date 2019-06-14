package sk.karabas.kpi.oop.game.actions;

import sk.karabas.kpi.oop.game.Keeper;
import sk.karabas.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

public class Shift extends AbstractAction<Keeper<Collectible>> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() != null) {
            getActor().getContainer().shift();
        }

        setDone(true);
    }
}
