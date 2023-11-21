package frames.gamelogic.players;

import frames.panels.GamePanel;
import frames.panels.status.PanelState;

/**
 * Abstract class for players
 */
public abstract class Player {
    protected PanelState myChoice;
    protected GamePanel playingCard;
    public Player(GamePanel playingCard) {
        this.playingCard = playingCard;
    }

    public PanelState getMyChoice() {
        return this.myChoice;
    }
    
    public void changeMyChoice(PanelState panelState) {
        this.myChoice = panelState;
    }
}
