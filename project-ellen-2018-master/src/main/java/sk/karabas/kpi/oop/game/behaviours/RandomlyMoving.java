

package sk.karabas.kpi.oop.game.behaviours;

    import org.jetbrains.annotations.Contract;

    import sk.karabas.kpi.oop.game.Direction;
    import sk.karabas.kpi.oop.game.Movable;
    import sk.karabas.kpi.oop.game.actions.Move;
    import sk.tuke.kpi.gamelib.actions.ActionSequence;
    import sk.tuke.kpi.gamelib.actions.Invoke;
    import sk.tuke.kpi.gamelib.actions.Wait;
    import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class RandomlyMoving implements Behaviour<Movable> {

    private Movable movable;

    private void randomness()
    {
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() -> new Move<>((Direction.random()), 0.5f).scheduleOn(this.getMovable())),
                new Wait<>(0.5f)
            )
        ).scheduleOn(this.getMovable());
    }

    @Override
    public void setUp(Movable actor)
    {
        this.setMovable(actor);
        this.randomness();
    }

    @Contract(pure = true)
    private Movable getMovable()
    {
        return movable;
    }

    private void setMovable(Movable movable)
    {
        this.movable = movable;
    }
}
