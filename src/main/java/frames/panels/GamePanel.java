package frames.panels;

import frames.factories.ColorFactory;
import frames.gamelogic.GameRealization;
import frames.panels.status.PanelState;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel {
    private final GameRealization gameRealization;
    private int areaSize;
    private static PanelState playerChoice = PanelState.CROSS;
    OneElementPanel[][] panelsArray;

    public GamePanel(int areaSize) {
        this.gameRealization = new GameRealization(this);
        this.areaSize = areaSize;
        setBackground(ColorFactory.getMain());
        setLayout(new GridLayout(this.areaSize, this.areaSize, 10, 10));

        startNewGame();

        setBorder(BorderFactory.createLineBorder(ColorFactory.getMain(), 10));
    }

    public void changePlayerChoice(PanelState playerChoice) {
        GamePanel.playerChoice = playerChoice;
        gameRealization.changeChoice();
        startNewGame();
    }

    public void changeAreaSize(int areaSize) {
        this.areaSize = areaSize;
        startNewGame();
    }

    public void winnerInform() {
        JOptionPane.showMessageDialog(this, "I win!");
        startNewGame();
    }

    public void loseInform() {
        JOptionPane.showMessageDialog(this, "I lost!");
        startNewGame();
    }

    public void drawInform() {
        JOptionPane.showMessageDialog(this, "Draw!");
        startNewGame();
    }

    public PanelState getPlayerChoice() {
        return playerChoice;
    }

    public int getAreaSize() {
        return this.areaSize;
    }

    public OneElementPanel[][] getPanelsArray() {
        return panelsArray;
    }

    public void startNewGame() {
        panelsArray = new OneElementPanel[areaSize][areaSize];
        this.removeAll();
        revalidate();
        setLayout(new GridLayout(areaSize, areaSize, 10, 10));
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                OneElementPanel oneElementPanel = new OneElementPanel(playerChoice, gameRealization, areaSize);
                panelsArray[i][j] = oneElementPanel;
                add(oneElementPanel);
            }
        }
    }

    public static class OneElementPanel extends JPanel {
        private static final BufferedImage crossImage;
        private static final BufferedImage circleImage;
        static {
            try {
                crossImage = ImageIO.read(new File("src/main/resources/artworks-000497536014-pv4c6g-large.jpg"));
                circleImage = ImageIO.read(new File("src/main/resources/11385708799.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private final int areaSize;
        private static final Map<Integer, int[]> xyValuesForImages = Map.of(3, new int[] {33, 34}, 4, new int[] {12, 15}, 5, new int[] {0, 1});
        private boolean isActive = true;
        private PanelState state = PanelState.EMPTY;
        public OneElementPanel(PanelState playerChoice, GameRealization gameRealization, int areaSize) {
            this.areaSize = areaSize;
            setBackground(ColorFactory.getOnePanelColor());
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isActive) {
                        switch (playerChoice) {
                            case CROSS:
                                state = PanelState.CROSS;
                                update(e);
                                isActive = false;
                                gameRealization.next();
                                break;
                            case CIRCLE:
                                state = PanelState.CIRCLE;
                                update(e);
                                isActive = false;
                                gameRealization.next();
                                break;
                            default:
                                throw new RuntimeException("You can't use empty state for playing.");
                        }
                    }
                }
            });
        }

        public void changeState(PanelState panelState) {
            if (panelState == PanelState.EMPTY) throw new RuntimeException("You can't use empty state for playing.");
            this.state = panelState;
            repaint();
            isActive = false;
        }

        public PanelState getState() {
            return state;
        }

        public boolean isActive() {
            return isActive;
        }

        private void update(MouseEvent e) {
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(g);
        }

        private void render(Graphics g) {
            switch (state) {
                case CROSS:
                    g.drawImage(crossImage, xyValuesForImages.get(areaSize)[0], xyValuesForImages.get(areaSize)[1], null);
                    break;
                case CIRCLE:
                    g.drawImage(circleImage, xyValuesForImages.get(areaSize)[0], xyValuesForImages.get(areaSize)[1], null);
                    break;
            }
        }
    }
}
