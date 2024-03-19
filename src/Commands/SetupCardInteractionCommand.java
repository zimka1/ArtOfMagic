package Commands;

import Cards.CardView;
import Cards.TypeOfCard.Card_Weapon;
import GameScene.*;

public class SetupCardInteractionCommand implements GameCommand{

    private CardView cardView;

    private GameManager gameManager;
    private GameScene gameScene;

    public SetupCardInteractionCommand(CardView cardView, GameManager gameManager, GameScene gameScene){
        this.cardView = cardView;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    public void execute() {
        CardView selectedCardForAttack = gameManager.getSelectedCardForAttack();
        if (selectedCardForAttack != null && selectedCardForAttack.getCard() instanceof Card_Weapon){
            if (selectedCardForAttack != null && cardView.getCard().getWhose() == selectedCardForAttack.getCard().getWhose()) {
                if (gameManager.getPlayerTurn()){
                    gameManager.getPlayer().minusMana(selectedCardForAttack.getCard().getManaCost());
                }
                else{
                    gameManager.getOpponent().minusMana(selectedCardForAttack.getCard().getManaCost());
                }
                cardView.getCard().setWeapon(selectedCardForAttack.getCard(), gameManager.getPlayerTurn() ? gameManager.getPlayer().getHand() : gameManager.getOpponent().getHand());
                gameScene.updateHandDisplay(gameManager.getPlayerTurn() ? gameManager.getPlayer() : gameManager.getOpponent(), gameManager.getPlayerTurn() ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard());
                gameScene.updateBoardDisplay(gameManager.getPlayerTurn() ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard());
                gameScene.updateManaLabels();
                selectedCardForAttack = null;

            }
        }
        else{
            if (gameManager.getPlayerTurn() && cardView.getCard().getWhose() == gameManager.getPlayer().getWhose() && cardView.getCard().getAlreadyAttacked() == 0) {
                gameManager.selectCard(cardView);
            } else if (!gameManager.getPlayerTurn() && cardView.getCard().getWhose() == gameManager.getOpponentBoard().getWhose() && cardView.getCard().getAlreadyAttacked() == 0) {
                gameManager.selectCard(cardView);
            } else if (selectedCardForAttack != null && cardView.getCard().getWhose() != selectedCardForAttack.getCard().getWhose()) {
                gameManager.executeAttack(selectedCardForAttack, cardView);
            }
        }
    }


}
