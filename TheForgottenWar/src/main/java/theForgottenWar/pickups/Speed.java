package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;

public class Speed extends Item {
    public Speed(int x, int y, String name) {
        super(Assets.speedPickup, x, y, name);
    }

    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            PowerUpSystem.increaseBonusSpeed();
        super.pickUp();
    }
}
