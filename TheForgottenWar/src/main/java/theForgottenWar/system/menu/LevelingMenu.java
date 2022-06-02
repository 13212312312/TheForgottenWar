package theForgottenWar.system.menu;

import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.PowerUpSystem;
import theForgottenWar.system.game.Game;
import theForgottenWar.system.manager.LevelingManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class LevelingMenu extends JFrame {
    private int screenWidth;
    private int screenHeight;
    private int imageHeight;
    private int imageWidth;
    JButton Healthup = new JButton("levelup");
    JButton Damageup = new JButton("levelup");
    JButton Cooldownup = new JButton("levelup");
    JButton Speedup = new JButton("levelup");
    JButton Durationup = new JButton("levelup");
    JButton Invulnerabilityup = new JButton("levelup");
    JButton Sprintup = new JButton("levelup");
    JButton Confirm = new JButton("Confirm");

    public LevelingMenu(String title,int screenWidth,int screenHeight)
    {
        super(title);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        imageHeight = screenHeight / 8;
        imageWidth = screenWidth / 2;
        init();
        Healthup.addActionListener(this::levelHealth);
        Damageup.addActionListener(this::levelDamage);
        Cooldownup.addActionListener(this::levelCooldown);
        Speedup.addActionListener(this::levelSpeed);
        Durationup.addActionListener(this::levelDuration);
        Invulnerabilityup.addActionListener(this::levelInvulnerability);
        Sprintup.addActionListener(this::levelSprint);
        Confirm.addActionListener(this::exit);
        LevelingManager.cooldown = 30;
    }
    private void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setMaximumSize(new Dimension(screenWidth, screenHeight));
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        addKeyListener(Game.KeyManager);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                LevelingMenu.confirm();
                dispose();
            }
        });
        int i = 8;
        int j = 2;
        JPanel[][] panelHolder = new JPanel[i][j];
        setLayout(new GridLayout(i,j));

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                if(n == 0 && m < i-1)
                {
                    panelHolder[m][n].setLayout(new GridLayout(2,1));
                }
                add(panelHolder[m][n]);
            }
        }
        addTo(panelHolder,Healthup, Assets.healthUPPickup[0],0,"HEALTH UP",PowerUpSystem.getHealthLevel(),PowerUpSystem.getMaxHealthLevel(),1);
        addTo(panelHolder,Damageup,Assets.damagePickup[0],1,"DAMAGE UP",PowerUpSystem.getDamageLevel(),PowerUpSystem.getMaxDamageLevel(),2);
        addTo(panelHolder,Cooldownup,Assets.cooldownPickup[0],2,"ATTACK COOLDOWN REDUCED",PowerUpSystem.getCooldownLevel(),PowerUpSystem.getMaxCooldownLevel(),3);
        addTo(panelHolder,Speedup,Assets.speedPickup[0],3,"SPEED UP",PowerUpSystem.getSpeedLevel(),PowerUpSystem.getMaxSpeedLevel(),4);
        addTo(panelHolder,Durationup,Assets.durationPickup[0],4,"ATTACK DURATION UP",PowerUpSystem.getDurationLevel(),PowerUpSystem.getMaxSpeedLevel(),5);
        addTo(panelHolder,Invulnerabilityup,Assets.invulnerabilityPickup[0],5,"INVULNERABILITY DURATION UP",PowerUpSystem.getInvulnerabilityLevel(),PowerUpSystem.getMaxInvulnerabilityLevel(),6);
        addTo(panelHolder,Sprintup,Assets.sprintPickup[0],6,"SPRINT DURATION UP",PowerUpSystem.getSprintLevel(),PowerUpSystem.getMaxSprintLevel(),7);
        panelHolder[7][0].add(new JLabel(" "));
        panelHolder[7][0].add(Confirm);
        validate();
        pack();
        repaint();
    }

    private void addTo(JPanel[][] panelHolder, JButton button, BufferedImage myPicture, int poz, String message,int i,int j,int type) {
        try {
            myPicture = addText(myPicture,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelHolder[poz][0].add(button);
        JPanel levels = new DrawingPanel(i-1,j-1,screenWidth/2,screenHeight/8,type);
        panelHolder[poz][0].add(levels);
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setPreferredSize(new Dimension(screenWidth/2,screenHeight/8));
        panelHolder[poz][1].add(picLabel);
    }

    public void exit(ActionEvent actionEvent)
    {
        confirm();
        dispose();
    }

    public static void confirm()
    {
        Game.setPaused(false);
        LevelingManager.open=false;
    }

    public void levelHealth(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getHealthLevel() - 1));
        System.out.println(cost);
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getHealthLevel() < PowerUpSystem.getMaxHealthLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseHealthLevel();
        }
        repaint();
    }
    public void levelDamage(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getDamageLevel() - 1));
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getDamageLevel() < PowerUpSystem.getMaxDamageLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseDamageLevel();
        }
        repaint();
    }
    public void levelCooldown(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getCooldownLevel() - 1) );
        if(CharacterList.myPlayer.getMoney() >=  cost && PowerUpSystem.getCooldownLevel() < PowerUpSystem.getMaxCooldownLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseCooldownLevel();
        }
        repaint();
    }
    public void levelSpeed(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getSpeedLevel() - 1) );
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getSpeedLevel() < PowerUpSystem.getMaxSpeedLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseSpeedLevel();
        }
        repaint();
    }
    public void levelDuration(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getDurationLevel() - 1));
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getDurationLevel() < PowerUpSystem.getMaxDurationLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseDurationLevel();
        }
        repaint();
    }
    public void levelInvulnerability(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getInvulnerabilityLevel() - 1));
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getInvulnerabilityLevel() < PowerUpSystem.getMaxInvulnerabilityLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseInvulnerabilityLevel();
        }
        repaint();
    }
    public void levelSprint(ActionEvent actionEvent)
    {
        int cost = PowerUpSystem.getPowerupcost() * ( (int) Math.pow(2, PowerUpSystem.getSprintLevel() - 1));
        if(CharacterList.myPlayer.getMoney() >=  cost  && PowerUpSystem.getSprintLevel() < PowerUpSystem.getMaxSprintLevel())
        {
            CharacterList.myPlayer.setMoney(CharacterList.myPlayer.getMoney()-cost);
            PowerUpSystem.increaseSprintLevel();
        }
        repaint();
    }
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 5, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    private BufferedImage addText(BufferedImage image,String message) throws IOException {
        image = resizeImage(image,imageWidth,imageHeight);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString(message, 10, 15);
        g.dispose();
        return image;
    }
}
