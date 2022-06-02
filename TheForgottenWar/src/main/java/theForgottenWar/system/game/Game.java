package theForgottenWar.system.game;

import theForgottenWar.characters.Player;
import theForgottenWar.data.SaveFile;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.graphics.Assets;
import theForgottenWar.system.combat.PowerUpSystem;
import theForgottenWar.system.combat.observers.PlayerSystem;
import theForgottenWar.system.manager.LevelingManager;
import theForgottenWar.system.manager.SaveManager;
import theForgottenWar.system.menu.MainMenu;
import theForgottenWar.system.Listener.KeyEventManager;
import theForgottenWar.data.maps.types.Map;
import theForgottenWar.characters.Character;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.NextLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game implements Runnable {
    private static final int numberOfLevels = 24;
    public static Random generateSeed = new Random();
    public static Random rand = new Random();
    public static long seed;
    private static boolean paused = false;
    private static boolean runState;
    private final String title;
    public static GameWindow wnd;
    private Thread gameThread;
    private int frame;
    public static KeyEventManager KeyManager;
    private Graphics g;

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        Game.paused = paused;
    }

    public Game(String title, int type) {
        this.title = title;
        seed = generateSeed.nextLong();
        if (type == 1 || type == 2) {
            SaveFile mySaveFile = SaveManager.findById(1);
            if (mySaveFile != null) {
                if(type == 1)
                {
                    mySaveFile.CopyLevelToPowerUpSystem();
                }
                else
                {
                    mySaveFile.CopyToPowerUpSystem();
                    seed = mySaveFile.getSeed();
                    LevelList.setCurrentLevel(mySaveFile.getCurrentLevel());
                }
            }
            else
            {
                System.out.println("Save failed!");
            }
        }
        if(type == 0)
        {
            SaveFile mySaveFile = new SaveFile(seed,0);
            mySaveFile.CopyFromPowerUpSystem();
            SaveManager.create(mySaveFile);
        }
        rand.setSeed(seed);
        System.out.println(seed);
        runState = false;
    }

    public static Map getMap() {
        return LevelList.getMap();
    }

    public static void setStopGame() {
        runState = false;
    }

    private void InitGame() {
        wnd = new GameWindow(title, MainMenu.screenWidth, MainMenu.screenHeight);
        KeyManager = new KeyEventManager();
        wnd.BuildGameWindow();
        wnd.getWndFrame().addKeyListener(KeyManager);
        Assets.Init();
        Map.screenHeight = wnd.GetWndHeight();
        Map.screenWidth = wnd.GetWndWidth();
        LevelList.Initialize(numberOfLevels);
        Character.SetScreen(wnd.GetWndHeight(), wnd.GetWndWidth());
    }
    public void run() {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond = 60;
        final double timeFrame = 1000000000.0 / framesPerSecond;

        while (runState) {
            curentTime = System.nanoTime();
            if ((curentTime - oldTime) > timeFrame) {
                if(!isPaused()) {
                    if(LevelingManager.letCooldown) {
                        LevelingManager.cooldown = Math.max(LevelingManager.cooldown - 1, 0);
                        LevelingManager.canUpgrade = Math.max(LevelingManager.canUpgrade - 1,0);
                    }
                    if(LevelingManager.open && LevelingManager.canUpgrade == 0)
                    {
                        setPaused(true);
                    }
                    Update();
                }
                Draw();
                oldTime = curentTime;
            }
        }
        JFrame f =  new JFrame();
        if(LevelList.finished) {
            JOptionPane.showMessageDialog(f, "You Win", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(f, "You lost", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        wnd.getWndFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StopGame();
    }

    public synchronized void StartGame() {
        if (!runState) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public synchronized void StopGame() {
        if (runState) {
            runState = false;
            try {
                gameThread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        g.dispose();
        wnd.getWndFrame().dispose();
    }

    private void Update() {
        KeyManager.tick();
        CharacterList.myPlayer.Move(KeyManager);
        frame++;
        LevelList.Update(frame);
        LevelList.AI();
        frame = frame % 60;
        NextLevel.Verify();
    }

    private void Draw() {
        BufferStrategy bs = wnd.GetCanvas().getBufferStrategy();
        if (bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assert bs != null;
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        LevelList.Draw(g);
        displayHUD(g);
        bs.show();
        g.dispose();
    }

    private void displayHUD(Graphics g) {
        StringBuilder myString = new StringBuilder();
        myString.append("Health:").append(CharacterList.myPlayer.getHealth()).append("  ");
        myString.append("MaxHealth:").append(PowerUpSystem.getHealth()).append("  ");
        myString.append("Damage:").append(PowerUpSystem.getDamage()).append("  ");
        myString.append("Speed:").append(PowerUpSystem.getSpeed()).append("  ");
        myString.append("Cooldown:").append(PlayerSystem.cooldown).append("  ");
        myString.append("Duration:").append(PlayerSystem.attackFrames).append("  ");
        myString.append("Enemies:").append(PlayerSystem.currentEnemies).append("  ");
        myString.append("Invulnerability:").append(PlayerSystem.invulnerabilityFrames).append("  ");
        myString.append("Sprint:").append(PlayerSystem.sprintFrames).append("  ");
        myString.append("Money:").append(CharacterList.myPlayer.getMoney()).append("  ");
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        g.drawString(myString.toString(),10,30);
        myString = new StringBuilder("Keys pressed: ");
        for(int index = 0;index < 255;index ++)
        {
            if(KeyEventManager.keys[index])
            {
                myString.append(KeyEvent.getKeyText(index)).append(" ");
            }
        }
        g.setColor(Color.RED);
        g.drawString(myString.toString(),10,60);
    }

}


