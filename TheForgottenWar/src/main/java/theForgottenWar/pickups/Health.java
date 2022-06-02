package theForgottenWar.pickups;

import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.PowerUpSystem;

public class Health extends Item {
    public Health(int x, int y, String name) {
        super(Assets.healthPickup, x, y, name);
    }

    @Override
    public void pickUp() {
        if(!isPickedUp() && isVisible())
            CharacterList.myPlayer.setHealth(Math.min(PowerUpSystem.getHealth(),CharacterList.myPlayer.getHealth() + 1));
        super.pickUp();
    }
}
