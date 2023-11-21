package frames.panels;

import frames.factories.ColorFactory;
import frames.factories.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel(GamePanel gamePanel) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createLineBorder(ColorFactory.getSecond(), 10));
        setFont(FontFactory.getMain());
        setBackground(ColorFactory.getSecond());

        JButton button1 = new JButton("New game");
        button1.setBackground(ColorFactory.getButtonsColor());
        button1.setFocusPainted(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startNewGame();
            }
        });

        JButton button2 = new JButton("Exit");
        button2.setBackground(ColorFactory.getButtonsColor());
        button2.setFocusPainted(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

        add(button1);
        add(button2);
    }
}
