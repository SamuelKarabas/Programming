
package sk.karabas.kpi.oop.game.characters;

import sk.karabas.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.gamelib.Actor;


public interface Armed extends Actor {

    Firearm getFirearm();

    void setFirearm(Firearm weapon);
}
