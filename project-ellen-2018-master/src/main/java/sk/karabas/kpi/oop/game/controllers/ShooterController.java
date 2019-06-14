


package sk.karabas.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;

import sk.karabas.kpi.oop.game.actions.Fire;
import sk.karabas.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;


public class ShooterController implements KeyboardListener {

    private Armed armed;
    public ShooterController(Armed armed)
    {
        this.armed=armed;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (key != Input.Key.SPACE) {
            return;
        }
        if (this.armed == null) {
            return;
        }


        new Fire<>().scheduleOn(this.armed);
    }



}
