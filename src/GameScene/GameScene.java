package GameScene;
import Cards.*;
import GameBoard.*;
import Players.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GameScene extends Application {
    private GameManager gameManager = new GameManager(this);

    private Button playerDeckButton;
    private Button opponentDeckButton;

    private Button endTurnButton;

    private PlayerCardView playerCardView = new PlayerCardView("Player 1", gameManager.getPlayer().getNowHP());
    private PlayerCardView opponentCardView = new PlayerCardView("Player 2", gameManager.getOpponent().getNowHP());

    private HBox playerHandContainer = new HBox(10);
    private HBox opponentHandContainer = new HBox(10);

    private HBox playerBoardContainer = new HBox(10);
    private HBox opponentBoardContainer = new HBox(10);

    private HBox playerGraveyardContainer = new HBox(10);
    private HBox opponentGraveyardContainer = new HBox(10);

    private VBox decksContainer = new VBox(20);

    private VBox leftSideContainer = new VBox(20);
    private VBox rightSideContainer = new VBox(20);

    private VBox topSideContainer = new VBox(20);
    private VBox bottomSideContainer = new VBox(20);


    private Label playerManaLabel = new Label();
    private Label opponentManaLabel = new Label();


    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        playerHandContainer.setAlignment(Pos.CENTER);
        opponentHandContainer.setAlignment(Pos.CENTER);
        playerBoardContainer.setAlignment(Pos.CENTER);
        opponentBoardContainer.setAlignment(Pos.CENTER);
        updatePlayerViews();


        playerDeckButton = new Button("Player's deck");

        opponentDeckButton = new Button("Opponent's deck");

        endTurnButton = new Button("End Turn");

        decksContainer.getChildren().addAll(opponentDeckButton, endTurnButton, playerDeckButton);
        rightSideContainer.getChildren().addAll(opponentManaLabel, decksContainer, playerManaLabel);
        rightSideContainer.setAlignment(Pos.CENTER_RIGHT);

        leftSideContainer.getChildren().addAll(opponentGraveyardContainer, playerGraveyardContainer);
        leftSideContainer.setAlignment(Pos.CENTER_LEFT);


        playerHandContainer.setSpacing(5);
        opponentHandContainer.setSpacing(5);


        topSideContainer.getChildren().addAll(opponentCardView, opponentHandContainer);
        bottomSideContainer.getChildren().addAll(playerHandContainer, playerCardView);


        root.setRight(rightSideContainer);
        root.setLeft(leftSideContainer);
        root.setBottom(bottomSideContainer);
        root.setTop(topSideContainer);

        VBox centerContainer = new VBox(5);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(opponentBoardContainer, playerBoardContainer);
        root.setCenter(centerContainer);
        centerContainer.setSpacing(20);

        beginGame();

        Scene scene = new Scene(root, 1200, 1000);
        String css = this.getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        playerBoardContainer.getStyleClass().add("borderStyle");
        opponentBoardContainer.getStyleClass().add("borderStyle");
        playerBoardContainer.setMaxWidth(700);
        opponentBoardContainer.setMaxWidth(700);





        primaryStage.setScene(scene);
        primaryStage.setTitle("Art Of Magic");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void beginGame() {
        gameManager.setupGame();
        gameManager.drawInitialCards();
        gameManager.getPlayer().setNowMana();
        gameManager.getOpponent().setNowMana();
        gameManager.restoringValues(gameManager.getPlayerBoard(), gameManager.getOpponentBoard());
        allUpdatesForBegining();
        gameManager.startTurn(endTurnButton);
    }

    public void allUpdatesForBegining(){
        updateManaLabels();
        updatePlayerViews();
        updateHandDisplay(gameManager.getPlayer());
        updateHandDisplay(gameManager.getOpponent());
    }

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
    }

    public void updateHandDisplay(Player player) {
        if (player == gameManager.getPlayer()) {
            playerHandContainer.getChildren().clear();
            for (Card card : player.getHand().getCards()) {
                CardView cardView = new CardView(card);
                playerHandContainer.getChildren().add(cardView);
                cardView.setOnMouseClicked(e -> {
                    if (gameManager.getPlayerTurn()){
                        gameManager.clickedOnHand(cardView, gameManager.getPlayer(), gameManager.getPlayerBoard(), gameManager.getPlayer().getNowMana());
                    }
                });

            }
        } else {
            opponentHandContainer.getChildren().clear();
            for (Card card : player.getHand().getCards()) {
                CardView cardView = new CardView(card);
                opponentHandContainer.getChildren().add(cardView);
                cardView.setOnMouseClicked(e -> {
                    if (!gameManager.getPlayerTurn()) {
                        gameManager.clickedOnHand(cardView, gameManager.getOpponent(), gameManager.getOpponentBoard(), gameManager.getOpponent().getNowMana());
                    }
                });

            }
        }
    }
    public void updateManaLabels() {
        playerManaLabel.setText(gameManager.getPlayer().getNowMana() + "/" + gameManager.getPlayer().getMana());
        opponentManaLabel.setText(gameManager.getOpponent().getNowMana() + "/" + gameManager.getOpponent().getMana());

        playerManaLabel.setFont(new Font("Arial", 14));
        opponentManaLabel.setFont(new Font("Arial", 14));
    }
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
            gameManager.setupCardInteraction(cardView);
        }
    }
    public void updateGraveyardDisplay(Board board) {
        HBox currentContainer = board == gameManager.getPlayerBoard() ? playerGraveyardContainer : opponentGraveyardContainer;
        currentContainer.getChildren().clear();
        if (!board.getGraveyard().getCards().isEmpty()) {

            Card topCard = board.getGraveyard().getCards().getLast();
            CardView cardView = new CardView(topCard);
            currentContainer.getChildren().add(cardView);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}