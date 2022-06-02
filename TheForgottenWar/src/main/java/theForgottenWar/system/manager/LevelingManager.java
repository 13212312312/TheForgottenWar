package theForgottenWar.system.manager;

import theForgottenWar.system.Listener.KeyEventManager;
import theForgottenWar.system.game.Game;
import theForgottenWar.system.menu.LevelingMenu;

public class LevelingManager {
    public static boolean inRange = false;
    public static boolean open = false;
    public static int cooldown = 0;
    public static boolean letCooldown = true;
    public static int canUpgrade = 30;

    public static void open() {
        if(!open) {
            open = true;
            KeyEventManager.upgrade = false;
            new LevelingMenu("Upgrade",500,700);
        }
    }
}
