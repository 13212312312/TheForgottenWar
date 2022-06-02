package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;

public class Duration extends Item {
    public Duration(int x, int y, String name) {
        super(Assets.durationPickup, x, y, name);
    }

    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            PowerUpSystem.increaseBonusDuration();
        super.pickUp();
    }
}
