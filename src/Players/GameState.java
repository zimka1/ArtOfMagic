package Players;

import GameScene.GameManager;
import javafx.scene.control.Button;

public interface GameState {
    void handleStartOfTurn(GameManager context, Button endTurnButton); // Метод для обработки начала хода
    void changeTurn(GameManager context, Button endTurnButton); // Метод для смены хода
}