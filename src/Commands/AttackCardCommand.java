package Commands;

import CardView.CardView;
import Cards.TypeOfCard.Card_Spell;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

/**
 * Command to facilitate an attack between two cards on the game board.
 * This command handles the logic for attacking one card with another,
 * applying spell effects, and updating the game state and visuals accordingly.
 */
public class AttackCardCommand implements GameCommand {
    private CardView attacker;
    private CardView target;
    private GameManager gameManager;
    private GameScene gameScene;

    /**
     * Constructs an AttackCardCommand with specified attacker and target cards,
     * along with references to the game manager and game scene to manage game state and UI updates.
     *
     * @param attacker the card view representing the attacking card
     * @param target the card view representing the defending card
     * @param gameManager the game manager to handle game logic and state
     * @param gameScene the game scene to update visual components after the attack
     */
    public AttackCardCommand(CardView attacker, CardView target, GameManager gameManager, GameScene gameScene) {
        this.attacker = attacker;
        this.target = target;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    /**
     * Executes the attack operation between the attacker and target cards.
     * Manages damage calculation, mana costs, and updates the visual and game state aspects
     * such as board, graveyard, hand displays, and mana labels.
     *
     * @param taskStatus status object to update with results of the attack, such as changes in the number of spell cards
     */
    @Override
    public void execute(TaskStatus taskStatus) {
        Board attackerBoard = (attacker.getCard().getWhose() == gameManager.getPlayer().getWhose()) ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard();
        Board targetBoard = (target.getCard().getWhose() == gameManager.getPlayer().getWhose()) ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard();

        attacker.getCard().attackCard(target.getCard(), attackerBoard, targetBoard);

        handleSpellCardEffects(taskStatus, attacker);
        attacker.getStyleClass().remove("selected");
        gameManager.setSelectedCardForAttack(null);

        updateGameSceneViews();
    }

    private void handleSpellCardEffects(TaskStatus taskStatus, CardView card) {
        if (card.getCard() instanceof Card_Spell) {
            Player owner = (card.getCard().getWhose() == gameManager.getPlayer().getWhose()) ? gameManager.getPlayer() : gameManager.getOpponent();
            owner.minusMana(card.getCard().getManaCost());
            owner.getHand().removeCard(card.getCard().getID());
            taskStatus.setNumberSpellCards();
        }
    }

    private void updateGameSceneViews() {
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
