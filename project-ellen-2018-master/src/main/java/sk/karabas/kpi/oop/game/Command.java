package sk.karabas.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

public interface Command<T extends Actor> {

    void execute(T actor);
}
