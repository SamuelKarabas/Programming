package sk.karabas.kpi.oop.game.Builder;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.karabas.kpi.oop.game.characters.Ripley;

public class RipleyBuilder extends AbstractActor implements Builder {

    private Ripley ripley;



    public RipleyBuilder() {
        if(this.ripley==null)return;
if(getScene()==null)return;
        this.ripley =  ripley.getScene().getFirstActorByType(Ripley.class);
    }


    @Override
    public void stopMove() {
        if(this.ripley==null)return;
        this.ripley.stoppedMoving();
    }

    @Override
    public Ripley getname() {
        return ripley;
    }


}
