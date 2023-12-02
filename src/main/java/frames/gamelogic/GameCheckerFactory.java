package frames.gamelogic;

import frames.panels.GamePanel;
import frames.panels.status.PanelState;
/**
 * Factory which consists of helpful functions for checking playing area.
 */
public class GameCheckerFactory {

    /**
     *
     * @param oneElementPanelArray matrix of playing elements
     * @param panelState state of one playing unit which is being used by one of players
     * @param areaSize size of playing area (for example 3x3 -> areaSize = 3)
     * @return true or false in dependence on wining or not wining of player which choose @panelState for playing
     */
    public static boolean isWinner(GamePanel.OneElementPanel[][] oneElementPanelArray, PanelState panelState, int areaSize, int timesToWin) {
        if (timesToWin > areaSize) throw new RuntimeException("Times to win can't be more than area size.");
        int amountOfDiagonals = (areaSize - 2) * 4 + 2;
        int[] checkArray = new int[areaSize * 2 + amountOfDiagonals];
        int currentIndex = 0;
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (oneElementPanelArray[i][j].getState() != panelState) checkArray[currentIndex] = 0;
                if (oneElementPanelArray[i][j].getState()  == panelState) checkArray[currentIndex] += 1;
                if (checkArray[currentIndex] == timesToWin) return true;
            }
            currentIndex++;
        }
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (oneElementPanelArray[j][i].getState() == panelState) checkArray[currentIndex] += 1;
                if (oneElementPanelArray[j][i].getState() != panelState) checkArray[currentIndex] = 0;
                if (checkArray[currentIndex] == timesToWin) return true;
            }
            currentIndex++;
        }
        int index = areaSize - 1;
        for (int i = 0; i < areaSize; i++) {
            if (oneElementPanelArray[i][i].getState() == panelState) checkArray[currentIndex] += 1;
            if (oneElementPanelArray[i][index].getState() == panelState) checkArray[currentIndex + 1] += 1;
            if (oneElementPanelArray[i][i].getState() != panelState) checkArray[currentIndex] = 0;
            if (oneElementPanelArray[i][index].getState() != panelState) checkArray[currentIndex + 1] = 0;
            if (checkArray[currentIndex] == timesToWin) return true;
            if (checkArray[currentIndex + 1] == timesToWin) return true;
            index--;
        }
        return false;
    }

    /**
     *
     * @param oneElementPanelArray matrix of playing elements
     * @param areaSize size of playing area (for example 3x3 -> areaSize = 3)
     * @return true or false if result of playing is draw
     */
    public static boolean isDraw(GamePanel.OneElementPanel[][] oneElementPanelArray, int areaSize) {
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (oneElementPanelArray[i][j].isActive()) return false;
            }
        }
        return true;
    }
}
