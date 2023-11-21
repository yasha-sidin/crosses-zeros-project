package frames;

import frames.menu.Menu;
import frames.panels.ButtonsPanel;
import frames.panels.GamePanel;
import frames.panels.status.PanelState;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;

    public GameWindow() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);

        GamePanel gamePanel = new GamePanel(3);
        Menu playerChoiceMenu = new Menu(gamePanel);

        setJMenuBar(playerChoiceMenu);
        add(gamePanel);
        add(new ButtonsPanel(gamePanel), BorderLayout.SOUTH);

        setVisible(true);
    }
}
