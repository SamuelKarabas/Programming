package sk.karabas.kpi.oop.game.controllers;


import org.jetbrains.annotations.NotNull;

import sk.karabas.kpi.oop.game.Keeper;
import sk.karabas.kpi.oop.game.actions.Drop;
import sk.karabas.kpi.oop.game.actions.Shift;
import sk.karabas.kpi.oop.game.actions.Take;
import sk.karabas.kpi.oop.game.actions.Use;
import sk.karabas.kpi.oop.game.items.Collectible;
import sk.karabas.kpi.oop.game.items.Usable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;

import java.util.Optional;



public class CollectorController implements KeyboardListener {
    private Keeper<Collectible> actor;

    public CollectorController(Keeper<Collectible> actor) {

        this.actor = actor;

    }




    @Override
    public void keyPressed(@NotNull Input.Key key) {


       if (key.equals(Input.Key.ENTER)) {
new Take<>(Collectible.class).scheduleOn(actor);

       }

        else if (key.equals(Input.Key.BACKSPACE))
           new Drop<Collectible>().scheduleOn(actor);


       else if (key.equals(Input.Key.S)) {
            new Shift().scheduleOn(actor);
        }
       else if(key.equals(Input.Key.B)){
           if (!(actor.getContainer().peek() instanceof Usable)) {
               return;
           }
           if (actor.getContainer().getSize() <= 0) {
               return;
           }
           new Use<>((Usable<?>) actor.getContainer().peek()).scheduleOnIntersectingWith(actor);
       }
      else  if (key.equals(Input.Key.U)) {

            Optional<Actor> usable = actor.getScene().getActors().stream().filter(Usable.class::isInstance).filter(actor::intersects).findFirst();
            usable.ifPresent(actor1 -> new Use<>((Usable<?>) actor1).scheduleOnIntersectingWith(actor));

        }


    }

}


















