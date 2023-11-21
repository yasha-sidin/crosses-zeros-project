package frames.gamelogic.players;

import frames.panels.GamePanel;
import frames.panels.status.PanelState;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

public class Robot extends Player {
    private static final Random random = new Random();
    public Robot(GamePanel playingCard) {
        super(playingCard);
        switch (playingCard.getPlayerChoice()) {
            case CROSS:
                this.myChoice = PanelState.CIRCLE;
                break;
            case CIRCLE:
                this.myChoice = PanelState.CROSS;
                break;
        }
    }

    public void makeMove() {
        int x = random.nextInt(playingCard.getAreaSize());
        int y = random.nextInt(playingCard.getAreaSize());
        while (true) {
            if (playingCard.getPanelsArray()[x][y].isActive()) {
                playingCard.getPanelsArray()[x][y].changeState(myChoice);
                break;
            }
            x = random.nextInt(playingCard.getAreaSize());
            y = random.nextInt(playingCard.getAreaSize());
        }
    }
}
