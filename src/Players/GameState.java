package Players;

import GameScene.GameManager;
import GameScene.GameScene;
import javafx.scene.control.Button;

/**
 * Represents the state of the game, providing methods to handle actions at the start of a turn
 * and to change turns between players. This interface allows for a state pattern implementation
 * where different states of the game can manage turn transitions and initial turn actions differently.
 */
public interface GameState {

    /**
     * Handles actions that need to occur at the start of a turn.
     * This method is called when a new turn begins for a player.
     *
     * @param context The GameManager that maintains the current game context and state.
     * @param endTurnButton The button in the UI that a player uses to end their turn.
     */
    void handleStartOfTurn(GameManager context, Button endTurnButton);

    /**
     * Changes the turn from one player to another.
     * This method is responsible for managing the transition between players' turns,
     * including updating any necessary state or UI components.
     *
     * @param context The GameManager that controls the game's state and turn transitions.
     * @param endTurnButton The button that is used to signify the end of a turn.
     */
    void changeTurn(GameManager context, Button endTurnButton);
}
