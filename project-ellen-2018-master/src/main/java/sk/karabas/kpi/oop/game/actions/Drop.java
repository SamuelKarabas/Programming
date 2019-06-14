package sk.karabas.kpi.oop.game.actions;

import sk.karabas.kpi.oop.game.Keeper;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

public class Drop<A extends Actor> extends AbstractAction<Keeper<A>> {

    @Override
    public void execute(float deltaTime)
    {
        if (getActor() == null) {
            setDone(true);
            return;
        }


        try {
            ActorContainer<A> container = getActor().getContainer();
            if (container != null) {
                A actor = container.peek();
                if (actor != null) {
                    container.remove(actor);
                    getActor().getScene().addActor(actor, getActor().getPosX(), getActor().getPosY());
                }
            }
        } catch (Exception ex) {
            setDone(true);
        }

        setDone(true);
    }
}
















