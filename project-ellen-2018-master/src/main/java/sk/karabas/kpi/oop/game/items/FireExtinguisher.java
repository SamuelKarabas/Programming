package sk.karabas.kpi.oop.game.items;

import sk.karabas.kpi.oop.game.Reactor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{

    public FireExtinguisher()
    {

        super(1);

        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    @Override
    public void useWith(Reactor actor)
    {
        if (actor == null || !actor.extinguish()) {
            return;
        }

        super.useWith(actor);
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }


}
