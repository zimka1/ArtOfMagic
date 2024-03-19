package GameScene;

import Cards.Card;
import Cards.CardLibrary;
import Cards.CardView;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameScene.GameScene;
import Players.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Iterator;

public class GameManager {
    private GameScene gameScene;
    private Board playerBoard;
    private Board opponentBoard;
    private Player player;
    private Player opponent;
    private boolean isPlayerTurn;

    private CardView selectedCardForAttack = null;


    public GameManager(GameScene gameScene) {
        this.gameScene = gameScene;
        this.playerBoard = new Board(1);
        this.opponentBoard = new Board(2);
        this.player = new Player(1);
        this.opponent = new Player(2);
        this.isPlayerTurn = true;
    }

    // getters
    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public CardView getSelectedCardForAttack() {
        return selectedCardForAttack;
    }

    // Setters
    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public void setSelectedCardForAttack(CardView selectedCardForAttack) {
        this.selectedCardForAttack = selectedCardForAttack;
    }




    public void setupGame() {
        playerBoard.getDeck().addCard(CardLibrary.players_BattleAxe);
        playerBoard.getDeck().addCard(CardLibrary.players_DarkLance);
        playerBoard.getDeck().addCard(CardLibrary.players_EarthGiant);
        playerBoard.getDeck().addCard(CardLibrary.players_GuardianOfTheForest);
        playerBoard.getDeck().addCard(CardLibrary.players_ArcaneBlast);
        playerBoard.getDeck().addCard(CardLibrary.players_CrystalSword);
        playerBoard.getDeck().addCard(CardLibrary.players_EarthStaff);
        playerBoard.getDeck().addCard(CardLibrary.players_Earthquake);

        opponentBoard.getDeck().addCard(CardLibrary.opponents_BattleAxe);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_DarkLance);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_EarthGiant);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_GuardianOfTheForest);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_ArcaneBlast);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_CrystalSword);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_EarthStaff);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_Earthquake);
    }

    public void restoringValues(Board playerBoard, Board opponentBoard){
        for (Iterator<Card> iterator = playerBoard.getBoard().iterator(); iterator.hasNext();){
            Card card = iterator.next();
            card.setAlreadyAttacked(0);
        }
        for (Iterator<Card> iterator = opponentBoard.getBoard().iterator(); iterator.hasNext();){
            Card card = iterator.next();
            card.setAlreadyAttacked(0);
        }
    }
    public void players_move(Button endTurnButton){
        restoringValues(playerBoard, opponentBoard);
        drawCard(player, playerBoard);
        player.plusMana();
        player.setNowMana();
        endTurnButton.setOnAction(e -> {
            isPlayerTurn = !isPlayerTurn;
            System.out.println(isPlayerTurn);
            if (selectedCardForAttack != null){
                selectedCardForAttack.getStyleClass().remove("selected");
            }
            selectedCardForAttack = null;
            opponents_move(endTurnButton);
        });


    }
    public void opponents_move(Button endTurnButton){
        restoringValues(playerBoard, opponentBoard);
        drawCard(opponent, opponentBoard);
        opponent.plusMana();
        opponent.setNowMana();
        endTurnButton.setOnAction(e -> {
            isPlayerTurn = !isPlayerTurn;
            System.out.println(isPlayerTurn);
            if (selectedCardForAttack != null){
                selectedCardForAttack.getStyleClass().remove("selected");
            }
            selectedCardForAttack = null;
            players_move(endTurnButton);
        });
    }

    public void drawInitialCards() {
        for (int i = 0; i < 5; i++) {
            drawCard(player, playerBoard);
            drawCard(opponent, opponentBoard);
        }
    }


    public void drawCard(Player player, Board board) {
        player.putCardInHand(board.getDeck());
        gameScene.updateHandDisplay(player, board);
    }

    public void setupCardInteraction(CardView cardView, Card card) {
        cardView.setOnMouseClicked(e -> {
            if (selectedCardForAttack != null && selectedCardForAttack.getCard() instanceof Card_Weapon){
                if (selectedCardForAttack != null && card.getWhose() == selectedCardForAttack.getCard().getWhose()) {
                    if (isPlayerTurn){
                        player.minusMana(selectedCardForAttack.getCard().getManaCost());
                    }
                    else{
                        opponent.minusMana(selectedCardForAttack.getCard().getManaCost());
                    }
                    card.setWeapon(selectedCardForAttack.getCard(), isPlayerTurn ? player.getHand() : opponent.getHand());
                    gameScene.updateHandDisplay(isPlayerTurn ? player : opponent, isPlayerTurn ? playerBoard : opponentBoard);
                    gameScene.updateBoardDisplay(isPlayerTurn ? playerBoard : opponentBoard);
                    gameScene.updateManaLabels();
                    selectedCardForAttack = null;

                }
            }
            else{
                if (isPlayerTurn && card.getWhose() == player.getWhose() && card.getAlreadyAttacked() == 0) {
                    selectCard(cardView, player.getNowMana());
                } else if (!isPlayerTurn && card.getWhose() == opponent.getWhose() && card.getAlreadyAttacked() == 0) {
                    selectCard(cardView, opponent.getNowMana());
                } else if (selectedCardForAttack != null && card.getWhose() != selectedCardForAttack.getCard().getWhose()) {
                    executeAttack(selectedCardForAttack, cardView);
                }
            }

        });
    }
    public void selectCard(CardView cardView, int mana) {
        if (selectedCardForAttack != null) {
            selectedCardForAttack.getStyleClass().remove("selected");
            selectedCardForAttack = null;
        }
        if (cardView.getCard().getManaCost() > mana) {
            System.out.println("You don't have enough mana!");
            return;
        }
        System.out.println(selectedCardForAttack != null ? "vybrano" : "nevybrano");
        selectedCardForAttack = cardView;
        selectedCardForAttack.getStyleClass().add("selected");
        System.out.println(selectedCardForAttack != null ? "vybrano" : "nevybrano");
    }
    public void executeAttack(CardView attacker, CardView target) {
        Board attackerBoard = (attacker.getCard().getWhose() == player.getWhose()) ? this.playerBoard : this.opponentBoard;
        Board targetBoard = (target.getCard().getWhose() == player.getWhose()) ? this.playerBoard : this.opponentBoard;

        attacker.getCard().attackCard(target.getCard(), attackerBoard, targetBoard);

        if (attacker.getCard() instanceof Card_Spell && attacker.getCard().getWhose() == player.getWhose()){
            player.minusMana(attacker.getCard().getManaCost());
            player.getHand().removeCard(attacker.getCard().getID());
        }
        if (attacker.getCard() instanceof Card_Spell && attacker.getCard().getWhose() == opponent.getWhose()){
            opponent.minusMana(attacker.getCard().getManaCost());
            opponent.getHand().removeCard(attacker.getCard().getID());
        }
        attacker.getStyleClass().remove("selected");
        selectedCardForAttack = null;

        gameScene.updateManaLabels();
        gameScene.updateBoardDisplay(this.playerBoard);
        gameScene.updateBoardDisplay(this.opponentBoard);
        gameScene.updateHandDisplay(this.player, this.playerBoard);
        gameScene.updateHandDisplay(this.opponent, this.opponentBoard);
        gameScene.updateGraveyardDisplay(this.playerBoard);
        gameScene.updateGraveyardDisplay(this.opponentBoard);
        gameScene.updatePlayerViews();
    }





    public void clickedOnPlayer(Player attackingPlayer, Player defendingPlayer, Board attackingBoard){
        if (selectedCardForAttack != null){
            if (selectedCardForAttack.getCard().getWeapon() != null){
                defendingPlayer.takingDamage(selectedCardForAttack.getCard().getPower() + selectedCardForAttack.getCard().getWeapon().getPower());
            }
            else{
                defendingPlayer.takingDamage(selectedCardForAttack.getCard().getPower());
            }
            selectedCardForAttack.getCard().setAlreadyAttacked(1);
            if (selectedCardForAttack.getCard() instanceof Card_Spell){
                attackingPlayer.minusMana(selectedCardForAttack.getCard().getManaCost());
                attackingPlayer.getHand().removeCard(selectedCardForAttack.getCard().getID());
                selectedCardForAttack.getCard().death(attackingBoard);
                selectedCardForAttack = null;
                gameScene.updateManaLabels();
                gameScene.updateHandDisplay(attackingPlayer, attackingBoard);
                gameScene.updateGraveyardDisplay(attackingBoard);
            }
            else{
                selectedCardForAttack = null;
            }
        }
    }


    public void clickedOnHand(CardView cardView, Player whichPlayer, Board whoseBoard){
        if (cardView.getCard() instanceof Card_Spell){
            selectCard(cardView, whichPlayer.getNowMana());
        }
        else if (cardView.getCard() instanceof Card_Weapon){
            selectCard(cardView, whichPlayer.getNowMana());
        }
        else{
            whichPlayer.putCardOnTable(cardView.getCard().getID(), whoseBoard);
            gameScene.updateBoardDisplay(whoseBoard);
            gameScene.updateManaLabels();
            gameScene.updateHandDisplay(whichPlayer, whoseBoard);
        }
    }



}

