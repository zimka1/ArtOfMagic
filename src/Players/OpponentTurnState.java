package Players;

import GameScene.GameManager;
import javafx.scene.control.Button;

/**
 * Represents the state of the game during the opponent's turn. This class handles
 * the start of the opponent's turn, including drawing cards, restoring values,
 * updating mana, and setting actions for ending the turn.
 */
public class OpponentTurnState implements GameState {

    /**
     * Handles the start of the opponent's turn, setting up the necessary game elements
     * and UI interactions. This method is called to execute all the actions needed at
     * the beginning of the opponent's turn.
     *
     * @param gameManager The game manager that controls the flow and rules of the game.
     * @param endTurnButton The button used to end the opponent's turn and transition to the player's turn.
     */
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

            gameManager.getTaskStatus().setOppLostHPInOneTurn();

            changeTurn(gameManager, endTurnButton);
        });
    }

    /**
     * Changes the turn from the opponent to the player. This method updates the game state
     * to reflect the transition and triggers the start of the player's turn.
     *
     * @param context The game manager responsible for managing the game state.
     * @param endTurnButton The button that is used to end the turn.
     */
    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new PlayerTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton);
    }
}
