package theForgottenWar.system.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ControlPanel extends JPanel {
    final MainMenu frame;
    public int screenWidth, screenHeight;
    private int nrButtons = 0;
    JButton exitBtn = new JButton("Exit");
    JButton startNewRunBtn = new JButton("Start new Run");
    JButton loadBtn = new JButton("Continue");
    JButton startNewGameBtn = new JButton("Start New Game");
    JButton optionBtn = new JButton("Option");

    public ControlPanel(MainMenu frame, int screenWidth, int screenHeight) {
        this.frame = frame;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setMaximumSize(new Dimension(screenWidth, screenHeight));
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        addButton(startNewGameBtn);
        addButton(startNewRunBtn);
        addButton(loadBtn);
        addButton(exitBtn);
        //addButton(optionBtn);

        setLayout(new GridLayout(nrButtons,1));

        try {
            addImageToButton(startNewRunBtn);
            addImageToButton(startNewGameBtn);
            addImageToButton(loadBtn);
            addImageToButton(exitBtn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startNewGameBtn.addActionListener(this::restartGame);
        exitBtn.addActionListener(this::exitGame);
        startNewRunBtn.addActionListener(this::StartGame);
        loadBtn.addActionListener(this::loadGame);
        optionBtn.addActionListener(this::optionGame);
    }
    private void addImageToButton(JButton Button) throws IOException {
        Image image = addText(Button);
        Button.setIcon(new ImageIcon(image));

    }
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 5, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    private BufferedImage addText(JButton Button) throws IOException {
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Button.png")));
        image = resizeImage(image,screenWidth + 20,screenHeight/nrButtons);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString(Button.getText(), 50, screenHeight/nrButtons/2+10);
        g.dispose();
        return image;
    }

    private void addButton(JButton o)
    {
        add(o);
        nrButtons++;
    }

    private void restartGame(ActionEvent actionEvent) {
        int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if(input == 0) {
            MainMenu.StartGame(0);
            frame.dispose();
        }
    }

    private void StartGame(ActionEvent actionEvent) {
        MainMenu.StartGame(1);
        frame.dispose();
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void loadGame(ActionEvent e) {
        MainMenu.StartGame(2);
        frame.dispose();
    }

    private void optionGame(ActionEvent e) {
        frame.dispose();
    }
}