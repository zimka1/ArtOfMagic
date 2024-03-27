package Commands;

import Cards.CardView;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Players.Player;

public class PlayCardCommand implements GameCommand {
    private CardView cardView;
    private Player player;
    private GameManager gameManager;

    private GameScene gameScene;
    private Board board;
    private int mana;
    public PlayCardCommand(CardView cardView, Player player, Board board, int mana, GameManager gameManager, GameScene gameScene) {
        this.cardView = cardView;
        this.player = player;
        this.board = board;
        this.mana = mana;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    @Override
    public void execute() {
        if (cardView.getCard().getManaCost() > mana) {
            System.out.println("You don't have enough mana!");
            return;
        }
        if (cardView.getCard() instanceof Card_Spell){
            gameManager.selectCard(cardView);
        }
        else if (cardView.getCard() instanceof Card_Weapon){
            gameManager.selectCard(cardView);
        }
        else{
            gameManager.selectCard(cardView);
            player.putCardOnTable(cardView.getCard().getID(), board);
            gameManager.setSelectedCardForAttack(null);
            gameScene.updateBoardDisplay(board);
            gameScene.updateManaLabels();
            gameScene.updateHandDisplay(player);
        }

    }
}

