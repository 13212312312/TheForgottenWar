package theForgottenWar.system.menu;

import theForgottenWar.system.combat.PowerUpSystem;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private int i;
    private int j;
    private int screenWidth;
    private int screenHeight;
    private int type;
    public DrawingPanel(int i, int j, int screenWidth, int screenHeight, int type)
    {
        this.i = i;
        this.j = j;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        this.type = type;
        setPreferredSize(new Dimension(screenWidth,screenHeight));
    }
    private void Update()
    {
        switch (type) {
            case 1 -> i = PowerUpSystem.getHealthLevel();
            case 2 -> i = PowerUpSystem.getDamageLevel();
            case 3 -> i = PowerUpSystem.getCooldownLevel();
            case 4 -> i = PowerUpSystem.getSpeedLevel();
            case 5 -> i = PowerUpSystem.getDurationLevel();
            case 6 -> i = PowerUpSystem.getInvulnerabilityLevel();
            case 7 -> i = PowerUpSystem.getSprintLevel();
        }
        i--;
    }
    public void paint(Graphics g){
        Update();
        int price = PowerUpSystem.getPowerupcost() * ((int) Math.pow(2, i));
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        String myString = "";
        if(i == j) myString = "Max";
        else
        {
            myString = Integer.toString(price);
        }
        g.drawString(myString, 10, 25);
        g.setColor(Color.WHITE);
        g.drawRect(0,0,screenWidth,screenHeight);
        g.setColor(Color.BLACK);
        for(int aux = 1;aux <= j;aux++) {
            if(aux<=i) {
                g.fillOval(aux * (screenWidth/(j+2)), 10, 10, 10);
            }
            else {
                g.drawOval(aux * (screenWidth/(j+2)), 10, 10, 10);
            }
        }
    }
}
