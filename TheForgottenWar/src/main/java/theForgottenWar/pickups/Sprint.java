package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;

public class Sprint extends Item {
    public Sprint(int x, int y, String name) {
        super(Assets.sprintPickup, x, y, name);
    }

    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            PowerUpSystem.increaseBonusSprint();
        super.pickUp();
    }
}
