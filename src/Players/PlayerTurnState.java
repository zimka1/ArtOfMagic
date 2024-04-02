package Players;

import GameScene.GameManager;
import GameScene.GameScene;
import javafx.scene.control.Button;

public class PlayerTurnState implements GameState {
    public void handleStartOfTurn(GameManager gameManager, Button endTurnButton) {
        gameManager.restoringValues(gameManager.getPlayerBoard(), gameManager.getOpponentBoard());
        gameManager.drawCardForPlayer(gameManager.getPlayer(), gameManager.getPlayerBoard());
        gameManager.getPlayer().plusMana();
        gameManager.getPlayer().setNowMana();
        gameManager.updateMana();
        endTurnButton.setOnAction(e -> {
            gameManager.setPlayerTurn(!gameManager.getPlayerTurn());
            if (gameManager.getSelectedCardForAttack() != null){
                gameManager.getSelectedCardForAttack().getStyleClass().remove("selected");
            }
            gameManager.setSelectedCardForAttack(null);
            gameManager.getTaskStatus().setOppLostHPInOneTurn();
            changeTurn(gameManager, endTurnButton);
        });

    }

    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new OpponentTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton);
    }
}