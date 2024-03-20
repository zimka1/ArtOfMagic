package Players;

import GameScene.GameManager;
import GameScene.GameScene;
import javafx.scene.control.Button;

public interface GameState {
    void handleStartOfTurn(GameManager context, Button endTurnButton);
    void changeTurn(GameManager context, Button endTurnButton);
}