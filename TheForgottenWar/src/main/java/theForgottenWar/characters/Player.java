package theForgottenWar.characters;

import theForgottenWar.data.Level;
import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.observers.PlayerSystem;
import theForgottenWar.system.game.Game;
import theForgottenWar.system.manager.LevelingManager;
import theForgottenWar.system.Listener.KeyEventManager;

public class Player extends Character {
    private static Player instance = null;

    private Player(int x, int y, String player) {
        super(Assets.player, x, y, player, -1, 0);
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(0, 0, "Player");
            instance.attach(new PlayerSystem(instance));
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    @Override
    public void Update(int frame) {
        super.Update(frame);
        if(this.getHealth() <= 0)
        {
            Game.setStopGame();
        }
    }

    public void Move(KeyEventManager KeyManager) {
        if (KeyEventManager.up || KeyEventManager.down || KeyEventManager.left || KeyEventManager.right) {
            setState(1);
        } else {
            setState(0);
        }
        if (KeyEventManager.up) {
            moveUp();
        }
        if (KeyEventManager.down) {
            moveDown();
        }
        if (KeyEventManager.left) {
            moveLeft();
        }
        if (KeyEventManager.right) {
            moveRight();
        }
        if(LevelingManager.canUpgrade != 0 && LevelingManager.inRange)
        {
            LevelingManager.canUpgrade = 0;
            KeyEventManager.upgrade = false;
            LevelingManager.open();
        }
    }

    @Override
    public int getCharacterWidth() {
        return Character.PLAYER_WIDTH;
    }

    @Override
    public int getCharacterHeight() {
        return Character.PLAYER_HEIGHT;
    }

}