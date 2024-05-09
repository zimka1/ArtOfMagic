package GameScene;
import CardView.*;
import Cards.*;
import GameBoard.*;
import Judges.JudgeTask;
import Players.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.List;


/**
 * The {@code GameScene} class represents the main gameplay scene of the application.
 * This class sets up the user interface for the game, including areas for player hands, boards,
 * decks, and graveyards. It also manages user interactions and updates the game state accordingly.
 */
public class GameScene extends Application {
    private PlayerCardView playerCardView;
    private PlayerCardView opponentCardView;

    // UI Components: Buttons
    private Button playerDeckButton;
    private Button opponentDeckButton;
    private Button endTurnButton;

    // UI Components: Containers
    private HBox playerHandContainer;
    private HBox opponentHandContainer;
    private HBox playerBoardContainer;
    private HBox opponentBoardContainer;
    private VBox graveyardsContainer;
    private HBox playerGraveyardContainer;
    private HBox opponentGraveyardContainer;
    private VBox decksContainer;
    private VBox leftSideContainer;
    private VBox rightSideContainer;
    private VBox topSideContainer;
    private VBox bottomSideContainer;
    private VBox centerContainer;

    // UI Components: Labels
    private Label playerManaLabel;
    private Label opponentManaLabel;
    private Label playerDeckCountLabel;
    private Label opponentDeckCountLabel;


    private List<JudgeTask> tasksForThisGame;

    /**
     * Constructs a new GameScene with the specified tasks.
     * @param tasksForThisGame a list of tasks to be displayed or interacted with during the game
     */

    public GameScene(List<JudgeTask> tasksForThisGame) {
        this.tasksForThisGame = tasksForThisGame;
    }

    /**
     * Retrieves the list of judge tasks associated with this game.
     * @return a list of JudgeTask objects
     */
    public List<JudgeTask> getTasksForThisGame() {
        return tasksForThisGame;
    }

    private TextArea playerActionLog;
    private TextArea opponentActionLog;


    private GameManager gameManager;


    /**
     * Initializes components, configures layout, sets styles, and starts the game.
     * This method is called as the entry point for launching the JavaFX application.
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        initializeComponents();
        configureLayout();
        setStyles();
        startGame(primaryStage);
    }

    /**
     * Initializes all visual components, sets up action handlers and links components to the game manager.
     */
    private void initializeComponents() {
        initializeActionLog();
        gameManager = new GameManager(this, playerActionLog, opponentActionLog);
        initializeViews();
        initializeContainers();
        initializeButtons();
        initializeLabels();
    }

    private void initializeViews() {
        playerCardView = new PlayerCardView("Player 1", gameManager.getPlayer().getNowHP());
        opponentCardView = new PlayerCardView("Player 2", gameManager.getOpponent().getNowHP());
    }

    private void initializeContainers() {
        playerHandContainer = new HBox(100);
        opponentHandContainer = new HBox(10);
        playerBoardContainer = new HBox(10);
        opponentBoardContainer = new HBox(10);
        graveyardsContainer = new VBox(5);
        playerGraveyardContainer = new HBox(10);
        opponentGraveyardContainer = new HBox(10);
        decksContainer = new VBox(20);
        leftSideContainer = new VBox(50);
        rightSideContainer = new VBox(20);
        topSideContainer = new VBox(20);
        bottomSideContainer = new VBox(20);
        centerContainer = new VBox(5);
    }

    private void initializeButtons() {
        playerDeckButton = new Button("Player's deck");
        opponentDeckButton = new Button("Opponent's deck");
        endTurnButton = new Button("End Turn");

        playerDeckButton.setOnAction(e -> {
            int count = gameManager.getPlayerBoard().getDeck().getCards().size();
            playerDeckCountLabel.setText("Cards in deck: " + count);
            playerDeckCountLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> playerDeckCountLabel.setVisible(false));
            pause.play();
        });
        opponentDeckButton.setOnAction(e -> {
            int count = gameManager.getOpponentBoard().getDeck().getCards().size();
            opponentDeckCountLabel.setText("Cards in deck: " + count);
            opponentDeckCountLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> opponentDeckCountLabel.setVisible(false));
            pause.play();
        });    }

    private void initializeLabels() {
        playerManaLabel = new Label();
        opponentManaLabel = new Label();
        playerDeckCountLabel = new Label("Cards in deck: " + gameManager.getPlayerBoard().getDeck().getCards().size());
        opponentDeckCountLabel = new Label("Cards in deck: " + gameManager.getOpponentBoard().getDeck().getCards().size());
        playerDeckCountLabel.setVisible(false);
        opponentDeckCountLabel.setVisible(false);
    }

    /**
     * Initializes and displays action logs for player and opponent.
     */
    private void initializeActionLog() {
        playerActionLog = new TextArea();
        playerActionLog.setEditable(false);
        playerActionLog.setWrapText(true);
        playerActionLog.setMaxHeight(130);
        playerActionLog.setMaxWidth(400);

        opponentActionLog = new TextArea();
        opponentActionLog.setEditable(false);
        opponentActionLog.setWrapText(true);

        opponentActionLog.setMaxHeight(130);
        opponentActionLog.setMaxWidth(400);
    }

    /**
     * Sets up containers and layouts for different sections of the game UI.
     */
    private void configureLayout() {


        // Configure alignments
        playerHandContainer.setAlignment(Pos.CENTER);
        opponentHandContainer.setAlignment(Pos.CENTER);
        playerBoardContainer.setAlignment(Pos.CENTER);
        opponentBoardContainer.setAlignment(Pos.CENTER);
        centerContainer.setAlignment(Pos.CENTER);
        rightSideContainer.setAlignment(Pos.CENTER_RIGHT);
        leftSideContainer.setAlignment(Pos.CENTER_LEFT);

        // Configure container relationships
        decksContainer.getChildren().addAll(opponentDeckButton, endTurnButton, playerDeckButton);
        rightSideContainer.getChildren().addAll(opponentCardView, opponentDeckCountLabel,opponentManaLabel, decksContainer, playerManaLabel, playerDeckCountLabel, playerCardView);
        leftSideContainer.getChildren().add(opponentActionLog);
        graveyardsContainer.getChildren().addAll(opponentGraveyardContainer, playerGraveyardContainer);
        leftSideContainer.getChildren().add(graveyardsContainer);
        leftSideContainer.getChildren().add(playerActionLog);
        topSideContainer.getChildren().addAll(opponentHandContainer);
        bottomSideContainer.getChildren().addAll(playerHandContainer);
        centerContainer.getChildren().addAll(opponentBoardContainer, playerBoardContainer);

        // Set additional spacing and alignment
        playerHandContainer.setSpacing(5);
        opponentHandContainer.setSpacing(5);
        centerContainer.setSpacing(20);
    }

    /**
     * Applies CSS styles to components.
     */
    private void setStyles() {
        // Apply CSS and class styles
        String css = this.getClass().getResource("/style.css").toExternalForm();
        playerBoardContainer.getStyleClass().add("borderStyle");
        opponentBoardContainer.getStyleClass().add("borderStyle");
        playerBoardContainer.setMaxWidth(700);
        opponentBoardContainer.setMaxWidth(700);
    }

    /**
     * Starts the game logic and displays the primary stage with all UI components.
     * @param primaryStage the primary stage to be set and shown.
     */

    private void startGame(Stage primaryStage) {

        // Begin the game logic
        beginGame();

        // Set up the scene and primary stage
        BorderPane root = new BorderPane();
        root.setRight(rightSideContainer);
        root.setLeft(leftSideContainer);
        root.setBottom(bottomSideContainer);
        root.setTop(topSideContainer);
        root.setCenter(centerContainer);

        Scene scene = new Scene(root, 1200, 1000);
        String css = this.getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Art Of Magic");
        primaryStage.setFullScreen(true);
        primaryStage.show();


    }


    /**
     * Begins the game by setting up the initial state, including drawing initial cards,
     * setting mana levels, and restoring game values. It also updates the UI to reflect the
     * current game state and starts the player's turn.
     */
    private void beginGame() {
        gameManager.setupGame();
        gameManager.drawInitialCards();
        gameManager.getPlayer().setNowMana();
        gameManager.getOpponent().setNowMana();
        gameManager.restoringValues(gameManager.getPlayerBoard(), gameManager.getOpponentBoard());
        allUpdatesForBeginning();
        gameManager.startTurn(endTurnButton);
    }

    /**
     * Updates all UI components relevant at the beginning of the game. This includes mana levels,
     * player views, and hands for both the player and the opponent.
     */
    public void allUpdatesForBeginning() {
        updateManaLabels();
        updatePlayerViews();
        updateHandDisplay(gameManager.getPlayer());
        updateHandDisplay(gameManager.getOpponent());
    }

    /**
     * Updates the player views with interaction capabilities and checks for game-over conditions.
     * It refreshes the display based on interactions and changes in player health.
     */
    public void updatePlayerViews() {
        playerCardView.setOnMouseClicked(e -> {
            gameManager.clickedOnPlayer(gameManager.getOpponent(), gameManager.getPlayer(), gameManager.getOpponentBoard());
            updatePlayerViews();
            updateBoardDisplay(gameManager.getOpponentBoard());
        });
        opponentCardView.setOnMouseClicked(e -> {
            gameManager.clickedOnPlayer(gameManager.getPlayer(), gameManager.getOpponent(), gameManager.getPlayerBoard());
            updatePlayerViews();
            updateBoardDisplay(gameManager.getPlayerBoard());
        });
        playerCardView.updateHP(gameManager.getPlayer().getNowHP());
        opponentCardView.updateHP(gameManager.getOpponent().getNowHP());

        if (gameManager.getPlayer().getNowHP() <= 0 || gameManager.getOpponent().getNowHP() <= 0) {
            moveToGameOverScene();
        }
    }

    /**
     * Transitions the application to the game over scene if one of the players' health reaches zero.
     */
    private void moveToGameOverScene() {
        try {
            Stage stage = (Stage) centerContainer.getScene().getWindow();
            GameOverScene gameOverScene = new GameOverScene(gameManager, gameManager.taskStatus, tasksForThisGame);
            stage.setFullScreen(false);
            gameOverScene.start(new Stage());
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the display of cards in a player's hand, enabling card interaction based on the current turn.
     *
     * @param player the player whose hand is being displayed.
     */
    public void updateHandDisplay(Player player) {
        HBox currentContainer = player == gameManager.getPlayer() ? playerHandContainer : opponentHandContainer;
        currentContainer.getChildren().clear();
        for (Card card : player.getHand().getCards()) {
            CardView cardView = new CardView(card);
            currentContainer.getChildren().add(cardView);
            cardView.setOnMouseClicked(e -> {
                if (player == gameManager.getPlayer() && gameManager.getPlayerTurn() || player != gameManager.getPlayer() && !gameManager.getPlayerTurn()) {
                    gameManager.clickedOnHand(cardView, player, player == gameManager.getPlayer() ? gameManager.getPlayerBoard() : gameManager.getOpponentBoard(), player.getNowMana());
                } else {
                    showTemporaryTooltip(cardView, "You cannot use the opponent's cards during your turn!");
                }
            });
        }
    }

    /**
     * Displays a temporary tooltip above a given UI node.
     *
     * @param anchor  the node above which the tooltip will be shown.
     * @param message the message to display in the tooltip.
     */
    private void showTemporaryTooltip(Node anchor, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);
        tooltip.show(anchor.getScene().getWindow(),
                anchor.localToScreen(anchor.getBoundsInLocal()).getMinX(),
                anchor.localToScreen(anchor.getBoundsInLocal()).getMaxY());

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> tooltip.hide());
        delay.play();
    }


    /**
     * Updates the mana display labels for both the player and the opponent.
     */
    public void updateManaLabels() {
        playerManaLabel.setText(String.format("%d/%d", gameManager.getPlayer().getNowMana(), gameManager.getPlayer().getMana()));
        opponentManaLabel.setText(String.format("%d/%d", gameManager.getOpponent().getNowMana(), gameManager.getOpponent().getMana()));
        playerManaLabel.setFont(new Font("Arial", 14));
        opponentManaLabel.setFont(new Font("Arial", 14));
    }

    /**
     * Updates the display of cards on the board for a specified board.
     *
     * @param board the board whose display is to be updated.
     */

    public void updateBoardDisplay(Board board) {
        HBox currentContainer = board == gameManager.getPlayerBoard() ? playerBoardContainer : opponentBoardContainer;
        currentContainer.getChildren().clear();
        for (Card card : board.getBoard()) {
            CardView cardView;
            if (card.getWeapon() != null){
                System.out.println(card.getWeapon().getName());
                cardView = new CardView(card, card.getWeapon());
            }
            else{
                cardView = new CardView(card);
            }
            currentContainer.getChildren().add(cardView);
            cardView.setOnMouseClicked(e -> {
                System.out.println(cardView.getCard().getWhose());
                System.out.println(gameManager.getPlayerTurn());
                if (((cardView.getCard().getWhose() == 1 && !gameManager.getPlayerTurn()) ||
                        (cardView.getCard().getWhose() == 2 && gameManager.getPlayerTurn()))
                            && gameManager.getSelectedCardForAttack() == null){
                    showTemporaryTooltip(cardView, "You cannot use the opponent's cards during your turn!");
                }else{
                    gameManager.setupCardInteraction(cardView);
                }
            });
        }
    }
    /**
     * Updates the display of the graveyard for a given board. This method clears the current graveyard display
     * and then shows the top card in the graveyard if it is not empty.
     *
     * @param board The game board whose graveyard display is to be updated. This can be either the player's or the opponent's board.
     */
    public void updateGraveyardDisplay(Board board) {
        // Determine the appropriate container for the graveyard based on whether it's the player's or the opponent's board.
        HBox currentContainer = board == gameManager.getPlayerBoard() ? playerGraveyardContainer : opponentGraveyardContainer;

        // Clear any existing content in the graveyard display.
        currentContainer.getChildren().clear();

        // Check if the graveyard has any cards. If it does, display the last card added (top card of the graveyard).
        if (!board.getGraveyard().getCards().isEmpty()) {
            Card topCard = board.getGraveyard().getCards().getLast(); // Get the last card in the graveyard list, which is the top card.
            CardView cardView = new CardView(topCard); // Create a view for the top card.
            currentContainer.getChildren().add(cardView); // Add the card view to the container for display.
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}