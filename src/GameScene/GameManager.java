package GameScene;

import Cards.Card;
import Cards.CardLibrary;
import Cards.CardView;
import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;
import Commands.*;
import GameBoard.Board;
import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import Judges.TaskStatus;
import Players.GameState;
import Players.Player;
import Players.PlayerTurnState;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.*;

public class GameManager {
    private final GameScene gameScene;
    private final Board playerBoard;
    private final Board opponentBoard;
    private final Player player;
    private final Player opponent;
    private boolean isPlayerTurn;

    private CardView selectedCardForAttack = null;

    private GameState currentState;

    TaskStatus taskStatus = new TaskStatus();

    private JudgeTaskManager taskManager = new JudgeTaskManager();
    private List<JudgeTask> tasksForThisGame;
    private List<Observer> observers = new ArrayList<>();
    private TextArea playerActionLog;
    private TextArea opponentActionLog;

    public GameManager(GameScene gameScene, TextArea playerActionLog, TextArea opponentActionLog) {
        this.gameScene = gameScene;
        this.playerBoard = new Board(1);
        this.opponentBoard = new Board(2);
        this.player = new Player(1);
        this.opponent = new Player(2);
        this.isPlayerTurn = true;
        this.currentState = new PlayerTurnState();
        this.tasksForThisGame = gameScene.getTasksForThisGame();
        this.playerActionLog = playerActionLog;
        this.opponentActionLog = opponentActionLog;
        Observer gameLogger = new GameLogger(playerActionLog, opponentActionLog);
        registerObserver(gameLogger);

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

    public List<JudgeTask> getTasksForThisGame() {
        return tasksForThisGame;
    }

    public JudgeTaskManager getTaskManager() {
        return taskManager;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    // Setters

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public void setSelectedCardForAttack(CardView selectedCardForAttack) {
        this.selectedCardForAttack = selectedCardForAttack;
    }

    public void registerObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException("Observer cannot be null");
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }


    public void startTurn(Button endTurnButton) {
        currentState.handleStartOfTurn(this, endTurnButton);
    }

    public void notifyObservers(String event, String playerType) {
        for (Observer observer : observers) {
            observer.update(event, playerType);
        }
    }

    public void executeCommand(GameCommand command) {
        command.execute(taskStatus);
    }

    public void drawCardForPlayer(Player toWhom, Board whoseBoard) {
        DrawCardCommand drawCommand = new DrawCardCommand(toWhom, whoseBoard, gameScene);
        executeCommand(drawCommand);
        String playerType = toWhom.getWhose() == 1 ? "Player" : "Opponent";
        notifyObservers(playerType + " drew a card", playerType);
    }

    public void executeAttack(CardView attacker, CardView target) {
        AttackCardCommand attackCardCommand = new AttackCardCommand(attacker, target, this, gameScene);
        executeCommand(attackCardCommand);
        String playerType = this.getPlayerTurn() ? "Player" : "Opponent";
        notifyObservers("<" + attacker.getCard().getName() + "> attacked <" + target.getCard().getName() + ">", playerType);
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
        if (cardView.getCard().getManaCost() > whichPlayer.getNowMana()){
            String playerType = whichPlayer.getWhose() == 1 ? "Player" : "Opponent";
            notifyObservers("You don't have enough mana!", playerType);
        }
        else if (cardView.getCard() instanceof Card_Minion){
            String playerType = whichPlayer.getWhose() == 1 ? "Player" : "Opponent";
            notifyObservers("Put the card <" + cardView.getCard().getName() + "> on the table", playerType);
        }
    }

    public void clickedOnPlayer(Player attackingPlayer, Player defendingPlayer, Board attackingBoard){
        if (selectedCardForAttack != null){
            AttackPlayerCommand attackPlayerCommand = new AttackPlayerCommand(attackingPlayer, defendingPlayer, attackingBoard, this, gameScene);
            executeCommand(attackPlayerCommand);
            if (selectedCardForAttack.getCard() instanceof Card_Spell || selectedCardForAttack.getCard() instanceof Card_Minion) {
                String attackingPlayerType = attackingPlayer.getWhose() == 1 ? "Player" : "Opponent";
                String defendingPlayerType = defendingPlayer.getWhose() == 1 ? "Player" : "Opponent";
                notifyObservers(defendingPlayerType + " was attacked by <" + selectedCardForAttack.getCard().getName() + ">", attackingPlayerType);
            }
        }
    }


    // Randomly selects unique cards from available cards.
    private Card[] getRandomCards(List<Card> availableCards) {
        Collections.shuffle(availableCards); // Shuffle for randomness
        Card[] randomCards = availableCards.stream().limit(30).toArray(Card[]::new);
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




    // Prepares decks with random, unique cards for players.
    public void setupGame() {
        // Gather all player and opponent cards.
        List<Card> playerCards = getAllCards(CardLibrary.playerMinions, CardLibrary.playerSpells, CardLibrary.playerWeapons);
        List<Card> opponentCards = getAllCards(CardLibrary.opponentMinions, CardLibrary.opponentSpells, CardLibrary.opponentWeapons);

        // Add unique cards to each deck.
        Card[] playerRandomCards = getRandomCards(playerCards);
        addCardsToDeck(playerBoard, playerRandomCards);

        Card[] opponentRandomCards = getRandomCards(opponentCards);
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
            SetupCardInteractionCommand setupCardInteractionCommand = new SetupCardInteractionCommand(cardView,this, gameScene);
            executeCommand(setupCardInteractionCommand);
    }
    public void selectCard(CardView cardView) {
        if (selectedCardForAttack != null) {
            selectedCardForAttack.getStyleClass().remove("selected");
        }
        selectedCardForAttack = cardView;
        selectedCardForAttack.getStyleClass().add("selected");
    }


}