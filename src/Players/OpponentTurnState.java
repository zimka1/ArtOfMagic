package Players;

import GameScene.GameManager;
import GameScene.GameScene;
import javafx.scene.control.Button;

public class OpponentTurnState implements GameState {
    public void handleStartOfTurn(GameManager gameManager, Button endTurnButton) {
        gameManager.restoringValues(gameManager.getPlayerBoard(), gameManager.getOpponentBoard());
        gameManager.drawCardForPlayer(gameManager.getOpponent(), gameManager.getOpponentBoard());
        gameManager.getOpponent().plusMana();
        gameManager.getOpponent().setNowMana();
        gameManager.updateMana();
        endTurnButton.setOnAction(e -> {
            gameManager.setPlayerTurn(!gameManager.getPlayerTurn());
            if (gameManager.getSelectedCardForAttack() != null){
                gameManager.getSelectedCardForAttack().getStyleClass().remove("selected");
            }
            gameManager.setSelectedCardForAttack(null);
            changeTurn(gameManager, endTurnButton);
        });
    }

    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new PlayerTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton);
    }
}