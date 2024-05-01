package Commands;

import GameBoard.Board;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

/**
 * Command to handle the action of drawing a card from the deck and updating the hand display.
 * This class is part of the command pattern and encapsulates all the information needed to
 * execute the drawing card action, including references to player, board, and game scene.
 */
public class DrawCardCommand implements GameCommand {
    private Player player;
    private Board board;
    private GameScene gameScene;

    /**
     * Constructs a new DrawCardCommand with specified player, board, and game scene.
     * This command will enable the player to draw a card from the deck and update the UI accordingly.
     *
     * @param player the player who will draw the card
     * @param board the board from which the card will be drawn
     * @param gameScene the game scene that will be updated after the card is drawn
     */
    public DrawCardCommand(Player player, Board board, GameScene gameScene) {
        this.player = player;
        this.board = board;
        this.gameScene = gameScene;
    }

    /**
     * Executes the command to draw a card from the deck and update the hand display based on the current task status.
     * This method handles the process of drawing a card and updating the visual representation of the player's hand.
     *
     * @param taskStatus the current status of the task which may influence how the action is performed
     */
    @Override
    public void execute(TaskStatus taskStatus) {
        player.putCardInHand(board.getDeck());
        gameScene.updateHandDisplay(player);
    }
}
