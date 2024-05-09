package Commands;

import CardView.*;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameScene.GameManager;
import GameScene.GameScene;
import Judges.TaskStatus;
import Players.Player;

/**
 * Command for executing an attack against another player using a selected card.
 * This command integrates game mechanics such as calculating damage, updating player states,
 * and managing card statuses after an attack.
 */
public class AttackPlayerCommand implements GameCommand {
    private Player attackingPlayer;
    private Player defendingPlayer;
    private Board attackingBoard;
    private GameScene gameScene;
    private GameManager gameManager;

    /**
     * Constructs an AttackPlayerCommand with all necessary components to perform an attack.
     *
     * @param attackingPlayer the player who is initiating the attack
     * @param defendingPlayer the player who is defending against the attack
     * @param attackingBoard the game board where the attack takes place
     * @param gameManager the manager that handles current game state and interactions
     * @param gameScene the scene to update visual components of the game after the attack
     */
    public AttackPlayerCommand(Player attackingPlayer, Player defendingPlayer, Board attackingBoard, GameManager gameManager, GameScene gameScene) {
        this.attackingPlayer = attackingPlayer;
        this.defendingPlayer = defendingPlayer;
        this.attackingBoard = attackingBoard;
        this.gameManager = gameManager;
        this.gameScene = gameScene;
    }

    /**
     * Executes the attack command, applying damage calculations based on the selected card,
     * updating player health, and handling card lifecycle events such as spell usage or weapon attacks.
     *
     * @param taskStatus status object to update with results of the attack such as damage dealt
     */
    @Override
    public void execute(TaskStatus taskStatus) {
        CardView selectedCardForAttack = gameManager.getSelectedCardForAttack();
        if (selectedCardForAttack.getCard().getWeapon() != null) {
            int damage = selectedCardForAttack.getCard().getPower() + selectedCardForAttack.getCard().getWeapon().getPower();
            defendingPlayer.takingDamage(damage);
            updateTaskStatus(taskStatus, defendingPlayer, damage);
        } else if (!(selectedCardForAttack.getCard() instanceof Card_Weapon)) {
            int damage = selectedCardForAttack.getCard().getPower();
            defendingPlayer.takingDamage(damage);
            updateTaskStatus(taskStatus, defendingPlayer, damage);
        }
        selectedCardForAttack.getCard().setAlreadyAttacked(1);
        if (selectedCardForAttack.getCard() instanceof Card_Spell) {
            handleSpellCard(taskStatus,selectedCardForAttack);
        } else {
            selectedCardForAttack = null;
        }
    }

    private void updateTaskStatus(TaskStatus taskStatus, Player player, int damage) {
        if (player.getWhose() == gameManager.getPlayer().getWhose()) {
            taskStatus.setHPlose(damage);
        } else {
            taskStatus.setCurrentOppLostHPInOneTurn(damage);
        }
    }

    private void handleSpellCard(TaskStatus taskStatus, CardView selectedCardForAttack) {
        attackingPlayer.minusMana(selectedCardForAttack.getCard().getManaCost());
        attackingPlayer.getHand().removeCard(selectedCardForAttack.getCard().getID());
        selectedCardForAttack.getCard().death(attackingBoard);
        selectedCardForAttack = null;
        taskStatus.setNumberSpellCards();
        gameScene.updateManaLabels();
        gameScene.updateHandDisplay(attackingPlayer);
        gameScene.updateGraveyardDisplay(attackingBoard);
    }
}
