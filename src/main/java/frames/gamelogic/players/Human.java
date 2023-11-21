package frames.gamelogic.players;

import frames.panels.GamePanel;

/**
 * Class which presents you as player
 */
public class Human extends Player {
    public Human(GamePanel playingCard) {
        super(playingCard);
        this.myChoice = playingCard.getPlayerChoice();
    }
}
