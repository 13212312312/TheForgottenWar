package theForgottenWar.system.menu;

import theForgottenWar.system.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainMenu extends JFrame {
    private static final String title = "TheForgottenWar";
    public static int screenWidth;
    public static int screenHeight;
    public static int ControlPannelX = 400;
    public static int ControlPannelY = 400;
    ControlPanel controlPanel;

    public MainMenu(int screenWidth, int screenHeight) {
        super(title);
        MainMenu.screenWidth = screenWidth;
        MainMenu.screenHeight = screenHeight;
        init();
    }

    public static void StartGame(int gametype) {
        Game paooGame = new Game(title, gametype);
        paooGame.StartGame();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controlPanel = new ControlPanel(this, ControlPannelX, ControlPannelY);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setMaximumSize(new Dimension(screenWidth, screenHeight));
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        int emptyX, emptyY;
        emptyX = screenWidth / 2 - ControlPannelX / 2;
        emptyY = screenHeight / 2 - ControlPannelY / 2;
        JPanel contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                Image img = null;
                try {
                    img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Button.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        contentPane.add(new EmptySpace(emptyX,emptyY),TOP_ALIGNMENT);
        contentPane.add(new EmptySpace(emptyX,emptyY),LEFT_ALIGNMENT);
        contentPane.add(controlPanel,CENTER_ALIGNMENT);
        setContentPane(contentPane);
        pack();
    }
}
