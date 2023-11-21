package frames.menu;

import frames.panels.GamePanel;
import frames.panels.status.PanelState;

import javax.swing.*;

public class Menu extends JMenuBar {
    GamePanel gamePanel;
    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        add(createChooseItemMenu());
        add(createSizeAreaMenu());
    }

    private JMenu createChooseItemMenu() {
        JMenu chooseItemForPlaying = new JMenu("Playing item");

        JRadioButtonMenuItem circle = new JRadioButtonMenuItem("zero");
        circle.addActionListener(e -> {
            gamePanel.changePlayerChoice(PanelState.CIRCLE);
        });

        JRadioButtonMenuItem cross = new JRadioButtonMenuItem("cross");
        cross.addActionListener(e -> gamePanel.changePlayerChoice(PanelState.CROSS));
        cross.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(circle);
        bg.add(cross);

        chooseItemForPlaying.add(cross);
        chooseItemForPlaying.add(circle);

        return chooseItemForPlaying;
    }

    private JMenu createSizeAreaMenu() {
        JMenu chooseAreaSize = new JMenu("Area size");

        JRadioButtonMenuItem threeToThree = new JRadioButtonMenuItem("3x3");
        threeToThree.addActionListener(e -> gamePanel.changeAreaSize(3));
        threeToThree.setSelected(true);

        JRadioButtonMenuItem fourToFour = new JRadioButtonMenuItem("4x4");
        fourToFour.addActionListener(e -> gamePanel.changeAreaSize(4));

        JRadioButtonMenuItem fiveToFive = new JRadioButtonMenuItem("5x5");
        fiveToFive.addActionListener(e -> gamePanel.changeAreaSize(5));

        ButtonGroup bg = new ButtonGroup();
        bg.add(threeToThree);
        bg.add(fourToFour);
        bg.add(fiveToFive);

        chooseAreaSize.add(threeToThree);
        chooseAreaSize.add(fourToFour);
        chooseAreaSize.add(fiveToFive);

        return chooseAreaSize;
    }
}
