package Players;

import GameScene.GameManager;
import GameScene.GameScene;
import javafx.scene.control.Button;

/**
 * Implements the game state during a player's turn, managing turn initialization
 * and transition to the opponent's turn.
 */
public class PlayerTurnState implements GameState {

    /**
     * Handles the beginning of the player's turn, setting up necessary actions such
     * as drawing a card, restoring values, and managing mana.
     *
     * @param gameManager The game manager that maintains the game state and executes game logic.
     * @param endTurnButton The button that players use to end their turn.
     */
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
    /**
     * Changes the game state to reflect the end of the player's turn and the beginning
     * of the opponent's turn.
     *
     * @param context The game manager that controls the state transitions and turn management.
     * @param endTurnButton The button that is used to trigger the turn change.
     */
    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new OpponentTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton);
    }
}