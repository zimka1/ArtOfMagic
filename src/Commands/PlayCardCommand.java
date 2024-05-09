package Commands;

import CardView.CardView;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

/**
 * Command to handle playing a card from the player's hand onto the game board.
 * This command is responsible for managing card selection, placement, and mana cost checking.
 */
public class PlayCardCommand implements GameCommand {
    private CardView cardView;
    private Player player;
    private Board board;
    private int mana;
    private GameManager gameManager;
    private GameScene gameScene;

    /**
     * Constructs a PlayCardCommand with all necessary components to perform the action of playing a card.
     *
     * @param cardView the view of the card being played
     * @param player the player playing the card
     * @param board the game board where the card will be placed
     * @param mana the current mana available to the player
     * @param gameManager the game manager to handle game logic and state
     * @param gameScene the game scene to update visual components after the card is played
     */
    public PlayCardCommand(CardView cardView, Player player, Board board, int mana, GameManager gameManager, GameScene gameScene) {
        this.cardView = cardView;
        this.player = player;
        this.board = board;
        this.mana = mana;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    /**
     * Executes the command to play the card. Checks if the player has enough mana, selects the card for specific actions,
     * and updates the game state and visuals such as board display, mana labels, and hand display.
     *
     * @param taskStatus the task status to update with results of playing the card, such as minion card counts
     */
    @Override
    public void execute(TaskStatus taskStatus) {
        if (cardView.getCard().getManaCost() > mana) {
            System.out.println("You don't have enough mana!");
            return;
        }
        if (cardView.getCard() instanceof Card_Spell || cardView.getCard() instanceof Card_Weapon) {
            gameManager.selectCard(cardView);
        } else {
            if (player.getWhose() == gameManager.getPlayer().getWhose()) {
                taskStatus.setNumberMinionCards();
            }

            gameManager.selectCard(cardView);
            player.putCardOnTable(cardView.getCard().getID(), board);
            gameManager.setSelectedCardForAttack(null);
            gameScene.updateBoardDisplay(board);
            gameScene.updateManaLabels();
            gameScene.updateHandDisplay(player);
            if (player.getWhose() == gameManager.getPlayer().getWhose() && gameManager.getPlayer().getHand().getCards().isEmpty()) {
                taskStatus.setUseAllCardsInOneTurn();
            }
        }
    }
}
