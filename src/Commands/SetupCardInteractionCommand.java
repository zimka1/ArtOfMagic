package Commands;

import Cards.CardView;
import Cards.TypeOfCard.Card_Weapon;
import GameScene.*;
import Judges.TaskStatus;

public class SetupCardInteractionCommand implements GameCommand{

    private CardView cardView;

    private GameManager gameManager;
    private GameScene gameScene;

    public SetupCardInteractionCommand(CardView cardView, GameManager gameManager, GameScene gameScene){
        this.cardView = cardView;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    public void execute(TaskStatus taskStatus) {
        CardView selectedCardForAttack = gameManager.getSelectedCardForAttack();

        if (selectedCardForAttack != null && selectedCardForAttack.getCard() instanceof Card_Weapon){
            if (selectedCardForAttack != null && cardView.getCard().getWhose() == selectedCardForAttack.getCard().getWhose()) {
                if (gameManager.getPlayerTurn()){
                    if (gameManager.getPlayer().getNowMana() >= selectedCardForAttack.getCard().getManaCost())
                        gameManager.getPlayer().minusMana(selectedCardForAttack.getCard().getManaCost());
                }
                else{
                    if (gameManager.getOpponent().getNowMana() >= selectedCardForAttack.getCard().getManaCost())
                        gameManager.getOpponent().minusMana(selectedCardForAttack.getCard().getManaCost());
                }
                taskStatus.setNumberWeaponCards();
                cardView.getCard().setWeapon(selectedCardForAttack.getCard(), gameManager.getPlayerTurn() ? gameManager.getPlayer().getHand() : gameManager.getOpponent().getHand());
                gameManager.setSelectedCardForAttack(null);
                gameScene.updateHandDisplay(gameManager.getPlayerTurn() ? gameManager.getPlayer() : gameManager.getOpponent());
                gameScene.updateBoardDisplay(gameManager.getPlayerTurn() ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard());
                gameScene.updateManaLabels();


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
