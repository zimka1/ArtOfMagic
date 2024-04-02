package Commands;

import Cards.CardView;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

public class AttackPlayerCommand implements GameCommand{
    private Player attackingPlayer;
    private Player defendingPlayer;
    private Board attackingBoard;
    private GameScene gameScene;
    private GameManager gameManager;


    public AttackPlayerCommand(Player attackingPlayer, Player defendingPlayer, Board attackingBoard, GameManager gameManager, GameScene gameScene) {
        this.attackingPlayer = attackingPlayer;
        this.defendingPlayer = defendingPlayer;
        this.attackingBoard = attackingBoard;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    public void execute(TaskStatus taskStatus) {
        CardView selectedCardForAttack = gameManager.getSelectedCardForAttack();
        if (selectedCardForAttack.getCard().getWeapon() != null){
            int damage = selectedCardForAttack.getCard().getPower() + selectedCardForAttack.getCard().getWeapon().getPower();
            defendingPlayer.takingDamage(damage);
            if (defendingPlayer.getWhose() == gameManager.getPlayer().getWhose()){
                taskStatus.setHPlose(damage);
            }
            else{
                taskStatus.setCurrentOppLostHPInOneTurn(damage);
            }
        }
        else if (!(selectedCardForAttack.getCard() instanceof Card_Weapon)){
            int damage = selectedCardForAttack.getCard().getPower();
            defendingPlayer.takingDamage(damage);
            if (defendingPlayer.getWhose() == gameManager.getPlayer().getWhose()){
                taskStatus.setHPlose(damage);
            }
            else{
                taskStatus.setCurrentOppLostHPInOneTurn(damage);
            }
        }
        selectedCardForAttack.getCard().setAlreadyAttacked(1);
        if (selectedCardForAttack.getCard() instanceof Card_Spell){
            attackingPlayer.minusMana(selectedCardForAttack.getCard().getManaCost());
            attackingPlayer.getHand().removeCard(selectedCardForAttack.getCard().getID());
            selectedCardForAttack.getCard().death(attackingBoard);
            selectedCardForAttack = null;
            taskStatus.setNumberSpellCards();
            gameScene.updateManaLabels();
            gameScene.updateHandDisplay(attackingPlayer);
            gameScene.updateGraveyardDisplay(attackingBoard);
        }
        else{
            selectedCardForAttack = null;
        }
    }
}

