

package sk.karabas.kpi.oop.game.behaviours;



import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.function.Predicate;

public class Observing<T, A extends Actor> implements Behaviour<A> {


    private Topic<T>     topic;
    private Predicate<T> predicate;
    private Behaviour<A> delegate;


    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate)
    {
      this.topic=topic;
        this.predicate=predicate;
        this.delegate=delegate;
    }

    @Override
    public void setUp(A actor)
    {
        if (actor == null) {
            return;
        }



        Scene scene = actor.getScene();
       if (scene == null) {
            return;
        }





        scene.getMessageBus().subscribe(topic, (e) -> {
            if (this.predicate.test(e)) {
                this.delegate.setUp(actor);
            }
        });
    }







}
