package theForgottenWar.system.Listener;

import theForgottenWar.system.manager.LevelingManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyEventManager implements KeyListener {
    public static boolean up, down, left, right, sprint, attack, upgrade;
    public static boolean firstTime = true;
    public static boolean[] keys;

    public KeyEventManager() {
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        sprint = keys[KeyEvent.VK_SHIFT];
        attack = keys[KeyEvent.VK_SPACE];
        upgrade = keys[KeyEvent.VK_E];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(e.getKeyCode() == KeyEvent.VK_E)
        {
            LevelingManager.letCooldown = false;
            firstTime = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        if(e.getKeyCode() == KeyEvent.VK_E) {
            LevelingManager.letCooldown = true;
            if(!firstTime) {
                LevelingManager.canUpgrade = 20;
            }
        }
    }
}
