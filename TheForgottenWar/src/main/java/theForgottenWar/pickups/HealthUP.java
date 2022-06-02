package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.PowerUpSystem;

public class HealthUP extends Item {
    public HealthUP(int x, int y, String name) {
        super(Assets.healthUPPickup, x, y, name);
    }

    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible()) {
            CharacterList.myPlayer.setHealth(CharacterList.myPlayer.getHealth()+1);
            PowerUpSystem.increaseBonusHealth();
        }
        super.pickUp();
    }
}
