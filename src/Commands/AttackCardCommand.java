package Commands;

import Cards.CardView;
import Cards.TypeOfCard.Card_Spell;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Judges.TaskStatus;

public class AttackCardCommand implements GameCommand{
    private CardView attacker;
    private CardView target;
    private GameManager gameManager;
    private GameScene gameScene;

    public AttackCardCommand(CardView attacker, CardView target, GameManager gameManager, GameScene gameScene) {
        this.attacker = attacker;
        this.target = target;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    public void execute(TaskStatus taskStatus) {
        Board attackerBoard = (attacker.getCard().getWhose() == gameManager.getPlayer().getWhose()) ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard();
        Board targetBoard = (target.getCard().getWhose() == gameManager.getPlayer().getWhose()) ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard();

        attacker.getCard().attackCard(target.getCard(), attackerBoard, targetBoard);

        if (attacker.getCard() instanceof Card_Spell && attacker.getCard().getWhose() == gameManager.getPlayer().getWhose()){
            gameManager.getPlayer().minusMana(attacker.getCard().getManaCost());
            gameManager.getPlayer().getHand().removeCard(attacker.getCard().getID());
            taskStatus.setNumberSpellCards();
        }
        if (attacker.getCard() instanceof Card_Spell && attacker.getCard().getWhose() == gameManager.getOpponent().getWhose()){
            gameManager.getOpponent().minusMana(attacker.getCard().getManaCost());
            gameManager.getOpponent().getHand().removeCard(attacker.getCard().getID());
        }
        attacker.getStyleClass().remove("selected");
        gameManager.setSelectedCardForAttack(null);

        gameScene.updateManaLabels();
        gameScene.updateBoardDisplay(gameManager.getPlayerBoard());
        gameScene.updateBoardDisplay(gameManager.getOpponentBoard());
        gameScene.updateHandDisplay(gameManager.getPlayer());
        gameScene.updateHandDisplay(gameManager.getOpponent());
        gameScene.updateGraveyardDisplay(gameManager.getPlayerBoard());
        gameScene.updateGraveyardDisplay(gameManager.getOpponentBoard());
        gameScene.updatePlayerViews();
    }
}
