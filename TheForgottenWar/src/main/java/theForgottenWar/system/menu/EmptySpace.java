package theForgottenWar.system.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class EmptySpace extends JPanel {
    public EmptySpace(int screenWidth, int screenHeight) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setMaximumSize(new Dimension(screenWidth, screenHeight));
        setMinimumSize(new Dimension(screenWidth, screenHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/empty.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
