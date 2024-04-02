package GameScene;

import Cards.Card;
import Cards.CardLibrary;
import Cards.CardView;
import Commands.*;
import GameBoard.Board;
import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import Players.GameState;
import Players.Player;
import Players.PlayerTurnState;
import javafx.scene.control.Button;

import java.util.*;

public class GameManager {
    private GameScene gameScene;
    private Board playerBoard;
    private Board opponentBoard;
    private Player player;
    private Player opponent;
    private boolean isPlayerTurn;

    private CardView selectedCardForAttack = null;

    private GameState currentState;


    private JudgeTaskManager taskManager = new JudgeTaskManager();
    private List<JudgeTask> currentJudgeTasks;

    public GameManager(GameScene gameScene) {
        this.gameScene = gameScene;
        this.playerBoard = new Board(1);
        this.opponentBoard = new Board(2);
        this.player = new Player(1);
        this.opponent = new Player(2);
        this.isPlayerTurn = true;
        this.currentState = new PlayerTurnState();
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
    public boolean getPlayerTurn() {
        return isPlayerTurn;
    }
    public void setState(GameState state) {
        this.currentState = state;
    }
    public GameState getCurrentState() {
        return currentState;
    }
    public CardView getSelectedCardForAttack() {
        return selectedCardForAttack;
    }

    // Setters

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public void setSelectedCardForAttack(CardView selectedCardForAttack) {
        this.selectedCardForAttack = selectedCardForAttack;
    }



    public void startTurn(Button endTurnButton) {
        currentState.handleStartOfTurn(this, endTurnButton);
    }

    public void executeCommand(GameCommand command) {
        command.execute();
    }

    public void drawCardForPlayer(Player toWhom, Board whoseBoard) {
        DrawCardCommand drawCommand = new DrawCardCommand(toWhom, whoseBoard, gameScene);
        executeCommand(drawCommand);
    }

    public void executeAttack(CardView attacker, CardView target) {
        AttackCardCommand attackCardCommand = new AttackCardCommand(attacker, target, this, gameScene);
        executeCommand(attackCardCommand);

    }

    public void updateMana(){
        gameScene.updateManaLabels();
    }
    private void addCardsToDeck(Board board, Card... cards) {
        for (Card card : cards) {
            board.getDeck().addCard(card);
        }
    }

    public void clickedOnHand(CardView cardView, Player whichPlayer, Board whoseBoard, int mana){
        PlayCardCommand playCardCommand = new PlayCardCommand(cardView, whichPlayer, whoseBoard, mana, this, gameScene);
        executeCommand(playCardCommand);
    }

    public void clickedOnPlayer(Player attackingPlayer, Player defendingPlayer, Board attackingBoard){
        if (selectedCardForAttack != null){
            AttackPlayerCommand attackPlayerCommand = new AttackPlayerCommand(attackingPlayer, defendingPlayer, attackingBoard, this, gameScene);
            executeCommand(attackPlayerCommand);
        }
    }


    // Randomly selects unique cards from available cards.
    private Card[] getRandomCards(List<Card> availableCards, int numberOfCards) {
        Collections.shuffle(availableCards); // Shuffle for randomness
        Card[] randomCards = availableCards.stream().limit(numberOfCards).toArray(Card[]::new);
        availableCards.removeAll(Arrays.asList(randomCards)); // Ensure uniqueness
        return randomCards;
    }

    // Combines all card types into one list.
    private List<Card> getAllCards(Card[] minions, Card[] spells, Card[] weapons) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(Arrays.asList(minions));
        cards.addAll(Arrays.asList(spells));
        cards.addAll(Arrays.asList(weapons));
        return cards;
    }

    public void assignTasksToJudges() {
        currentJudgeTasks = taskManager.getRandomTasksForJudges();
    }



    // Prepares decks with random, unique cards for players.
    public void setupGame() {
        // Gather all player and opponent cards.
        List<Card> playerCards = getAllCards(CardLibrary.playerMinions, CardLibrary.playerSpells, CardLibrary.playerWeapons);
        List<Card> opponentCards = getAllCards(CardLibrary.opponentMinions, CardLibrary.opponentSpells, CardLibrary.opponentWeapons);

        // Add unique cards to each deck.
        Card[] playerRandomCards = getRandomCards(playerCards, 30);
        addCardsToDeck(playerBoard, playerRandomCards);

        Card[] opponentRandomCards = getRandomCards(opponentCards, 30);
        addCardsToDeck(opponentBoard, opponentRandomCards);
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

    public void drawInitialCards() {
        for (int i = 0; i < 5; i++) {
            drawCardForPlayer(player, playerBoard);
            drawCardForPlayer(opponent, opponentBoard);
        }
    }

    public void setupCardInteraction(CardView cardView) {
        cardView.setOnMouseClicked(e -> {
            SetupCardInteractionCommand setupCardInteractionCommand = new SetupCardInteractionCommand(cardView,this, gameScene);
            executeCommand(setupCardInteractionCommand);

        });
    }
    public void selectCard(CardView cardView) {
        if (selectedCardForAttack != null) {
            selectedCardForAttack.getStyleClass().remove("selected");
        }
        selectedCardForAttack = cardView;
        selectedCardForAttack.getStyleClass().add("selected");
    }


}