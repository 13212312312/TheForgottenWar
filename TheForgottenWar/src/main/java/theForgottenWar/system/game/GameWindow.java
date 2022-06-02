package theForgottenWar.system.game;

import theForgottenWar.system.manager.SaveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    public static WindowAdapter myAdapter;
    private JFrame wndFrame;
    private final String wndTitle;
    private final int wndWidth;
    private final int wndHeight;

    private Canvas canvas;

    public GameWindow(String title, int width, int height) {
        wndTitle = title;
        wndWidth = width;
        wndHeight = height;
        wndFrame = null;
    }

    public void BuildGameWindow() {
        if (wndFrame != null) {
            return;
        }
        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        canvas.setFocusable(false);
        wndFrame.add(canvas);
        wndFrame.pack();
        myAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Game.setPaused(true);
                int resp = JOptionPane.showConfirmDialog(wndFrame, "You still didn't win, are you sure you want to close?",
                        "Exit?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    wndFrame.removeKeyListener(Game.KeyManager);
                } else {
                    wndFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                Game.setPaused(false);
            }};
        wndFrame.addWindowListener(myAdapter);
    }

    public int GetWndWidth() {
        return wndWidth;
    }

    public int GetWndHeight() {
        return wndHeight;
    }

    public Canvas GetCanvas() {
        return canvas;
    }

    public JFrame getWndFrame() {
        return wndFrame;
    }

    public void dispose() {
        wndFrame.dispose();
    }
}
