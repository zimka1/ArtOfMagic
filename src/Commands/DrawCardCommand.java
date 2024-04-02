package Commands;

import GameBoard.Board;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

public class DrawCardCommand implements GameCommand {
    private Player player;
    private Board board;
    private GameScene gameScene;

    public DrawCardCommand(Player player, Board board, GameScene gameScene) {
        this.player = player;
        this.board = board;
        this.gameScene = gameScene;
    }

    public void execute(TaskStatus taskStatus) {
        player.putCardInHand(board.getDeck());
        gameScene.updateHandDisplay(player);
    }
}
