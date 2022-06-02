package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;

public class Damage extends Item {
    public Damage(int x, int y, String name) {
        super(Assets.damagePickup, x, y, name);
    }


    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            PowerUpSystem.increaseBonusDamage();
        super.pickUp();
    }
}
