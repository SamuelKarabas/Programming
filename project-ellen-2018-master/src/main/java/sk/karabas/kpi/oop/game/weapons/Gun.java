

package sk.karabas.kpi.oop.game.weapons;


public class Gun extends Firearm {


    public Gun(int ammo)
    {
        super(ammo);
    }


    public Gun(int ammo, int maxAmmo)
    {
        super(ammo, maxAmmo);
    }

    @Override
    protected Fireable createBullet()
    {
        return new Bullet();
    }
}
