package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;

public class Cooldown extends Item {
    public Cooldown(int x, int y, String name) {
        super(Assets.cooldownPickup, x, y, name);
    }


    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            PowerUpSystem.increaseBonusCooldown();
        super.pickUp();
    }
}
