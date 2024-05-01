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

/**
 * Manages the game state, including player turns, card interactions, task execution, and game setup.
 */
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

    /**
     * Constructs a new GameManager with the specified GameScene and action logs for players.
     *
     * @param gameScene        The GameScene associated with this GameManager.
     * @param playerActionLog  The action log for the player.
     * @param opponentActionLog The action log for the opponent.
     */
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    // Setters

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
        String playerType = isPlayerTurn ? "Player" : "Opponent";
        notifyObservers( "Your turn!", playerType);
    }

    public void setSelectedCardForAttack(CardView selectedCardForAttack) {
        this.selectedCardForAttack = selectedCardForAttack;
    }

    /**
     * Registers an observer to receive notifications of game events.
     *
     * @param o The observer to register.
     * @throws NullPointerException if the observer is null.
     */
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

    /**
     * Notifies all registered observers of a game event.
     *
     * @param event      The event message.
     * @param playerType The type of player associated with the event.
     */
    public void notifyObservers(String event, String playerType) {
        for (Observer observer : observers) {
            observer.update(event, playerType);
        }
    }

    /**
     * Executes a game command.
     *
     * @param command The command to execute.
     */
    public void executeCommand(GameCommand command) {
        command.execute(taskStatus);
    }

    /**
     * Draws a card for a player and notifies observers of the action.
     *
     * @param toWhom     The player to draw a card for.
     * @param whoseBoard The board associated with the player.
     */
    public void drawCardForPlayer(Player toWhom, Board whoseBoard) {
        DrawCardCommand drawCommand = new DrawCardCommand(toWhom, whoseBoard, gameScene);
        if (toWhom.getHand().getCards().size() != 6){
            executeCommand(drawCommand);
            String playerType = toWhom.getWhose() == 1 ? "Player" : "Opponent";
            notifyObservers(playerType + " drew a card", playerType);
        }
        else{
            String playerType = toWhom.getWhose() == 1 ? "Player" : "Opponent";
            notifyObservers("You hava too many cards!", playerType);
        }
    }

    /**
     * Executes an attack between two cards and notifies observers of the action.
     *
     * @param attacker The attacking card.
     * @param target   The target card.
     */
    public void executeAttack(CardView attacker, CardView target) {
        AttackCardCommand attackCardCommand = new AttackCardCommand(attacker, target, this, gameScene);
        executeCommand(attackCardCommand);
        String playerType = this.getPlayerTurn() ? "Player" : "Opponent";
        notifyObservers("<" + attacker.getCard().getName() + "> attacked <" + target.getCard().getName() + ">", playerType);
    }

    /**
     * Updates mana labels in the game scene.
     */
    public void updateMana(){
        gameScene.updateManaLabels();
    }
    /**
     * Adds cards to the specified board's deck.
     *
     * @param board The board to add cards to.
     * @param cards The cards to add to the deck.
     */
    private void addCardsToDeck(Board board, Card... cards) {
        for (Card card : cards) {
            board.getDeck().addCard(card);
        }
    }
    /**
     * Handles the action when a player clicks on a card in their hand.
     * Executes the PlayCardCommand and notifies observers accordingly.
     *
     * @param cardView   The card view clicked by the player.
     * @param whichPlayer The player who clicked on the card.
     * @param whoseBoard  The board associated with the player.
     * @param mana        The current mana of the player.
     */
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
    /**
     * Handles the action when a player clicks on the opponent player.
     * Executes the AttackPlayerCommand if a card is selected for attack,
     * and notifies observers of the action.
     *
     * @param attackingPlayer The player initiating the attack.
     * @param defendingPlayer The player being attacked.
     * @param attackingBoard  The board associated with the attacking player.
     */
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

    /**
     * Randomly selects a specified number of unique cards from the list of available cards.
     *
     * @param availableCards The list of available cards to choose from.
     * @return An array of randomly selected unique cards.
     */
    private Card[] getRandomCards(List<Card> availableCards) {
        Collections.shuffle(availableCards); // Shuffle for randomness
        Card[] randomCards = availableCards.stream().limit(30).toArray(Card[]::new);
        availableCards.removeAll(Arrays.asList(randomCards)); // Ensure uniqueness
        return randomCards;
    }

    /**
     * Combines all types of cards (minions, spells, weapons) into one list.
     *
     * @param minions The array of minion cards.
     * @param spells  The array of spell cards.
     * @param weapons The array of weapon cards.
     * @return A list containing all types of cards.
     */
    private List<Card> getAllCards(Card[] minions, Card[] spells, Card[] weapons) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(Arrays.asList(minions));
        cards.addAll(Arrays.asList(spells));
        cards.addAll(Arrays.asList(weapons));
        return cards;
    }




    /**
     * Sets up the initial game state, including preparing decks and drawing initial cards.
     */
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


    /**
     * Restores values of cards on both player and opponent boards.
     *
     * @param playerBoard   The player's board.
     * @param opponentBoard The opponent's board.
     */

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
    /**
     * Draws initial cards for both players at the start of the game.
     */
    public void drawInitialCards() {
        for (int i = 0; i < 5; i++) {
            drawCardForPlayer(player, playerBoard);
            drawCardForPlayer(opponent, opponentBoard);
        }
    }
    /**
     * Sets up interaction for a selected card.
     *
     * @param cardView The selected card view.
     */
    public void setupCardInteraction(CardView cardView) {
            SetupCardInteractionCommand setupCardInteractionCommand = new SetupCardInteractionCommand(cardView,this, gameScene);
            executeCommand(setupCardInteractionCommand);
    }
    /**
     * Selects a card for an action.
     *
     * @param cardView The selected card view.
     */
    public void selectCard(CardView cardView) {
        if (selectedCardForAttack != null) {
            selectedCardForAttack.getStyleClass().remove("selected");
        }
        selectedCardForAttack = cardView;
        selectedCardForAttack.getStyleClass().add("selected");
    }


}