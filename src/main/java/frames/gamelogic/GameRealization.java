package frames.gamelogic;

import frames.gamelogic.players.Human;
import frames.gamelogic.players.Robot;
import frames.panels.GamePanel;
import frames.panels.status.PanelState;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Logic of the game
 */
public class GameRealization {

    private final Pair<Human, Robot> players;
    private final GamePanel gamePanel;

    /**
     *
     * @param gamePanel main GUI panel of the game
     */
    public GameRealization(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        players = new ImmutablePair<>(new Human(gamePanel), new Robot(gamePanel));
    }

    /**
     * Changing of playing units between two players(for example: robot - X; human - O -> robot - O; human - X)
     */
    public void changeChoice() {
        PanelState panelState = players.getLeft().getMyChoice();
        players.getLeft().changeMyChoice(players.getRight().getMyChoice());
        players.getRight().changeMyChoice(panelState);
    }

    /**
     * Start of robot's moving (with all checking instances from GameCheckerFactory)
     */
    public void next() {
        if(GameCheckerFactory.isWinner(gamePanel.getPanelsArray(), players.getLeft().getMyChoice(), gamePanel.getAreaSize())) {
            gamePanel.winnerInform();
            return;
        }
        if(GameCheckerFactory.isDraw(gamePanel.getPanelsArray(), gamePanel.getAreaSize())) {
            gamePanel.drawInform();
            return;
        }
        players.getRight().makeMove();
        if(GameCheckerFactory.isWinner(gamePanel.getPanelsArray(), players.getRight().getMyChoice(), gamePanel.getAreaSize())) {
            gamePanel.loseInform();
            return;
        }
        if(GameCheckerFactory.isDraw(gamePanel.getPanelsArray(), gamePanel.getAreaSize())) {
            gamePanel.drawInform();
        }
    }
}
