package GameScene;
import Cards.*;
import Players.PlayerCardView;
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

import java.util.Objects;

public class GameScene extends Application {

    private Board playerBoard = new Board(1);
    private Board opponentBoard = new Board(2);

    private Button playerDeckButton;
    private Button opponentDeckButton;

    private Button endTurnButton;
    private Player player = new Player(1);
    private Player opponent = new Player(2);
    PlayerCardView playerCardView = new PlayerCardView("Player 1", player.getNowHP());
    PlayerCardView opponentCardView = new PlayerCardView("Player 2", opponent.getNowHP());
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

    private CardView selectedCardForAttack = null;

    private Label playerManaLabel = new Label();
    private Label opponentManaLabel = new Label();


    private boolean isPlayerTurn = true;

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

    private void setupGame() {
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

    private void beginGame() {
        setupGame();
        drawInitialCards();
        player.setNowMana();
        opponent.setNowMana();
        updateManaLabels();
        updateHandDisplay(player, playerBoard);
        updateHandDisplay(opponent, opponentBoard);
        players_move();
    }

    private void players_move(){
        drawCard(player, playerBoard);
        player.plusMana();
        player.setNowMana();
        updateManaLabels();
        endTurnButton.setOnAction(e -> {
            isPlayerTurn = !isPlayerTurn;
            System.out.println(isPlayerTurn);
            opponents_move();
        });


    }
    private void opponents_move(){
        drawCard(opponent, opponentBoard);
        opponent.plusMana();
        opponent.setNowMana();
        updateManaLabels();
        endTurnButton.setOnAction(e -> {
            isPlayerTurn = !isPlayerTurn;
            System.out.println(isPlayerTurn);
            players_move();
        });
    }

    private void drawInitialCards() {
        for (int i = 0; i < 5; i++) {
            drawCard(player, playerBoard);
            drawCard(opponent, opponentBoard);
        }
    }


    private void drawCard(Player player, Board board) {
        player.putCardInHand(board.getDeck());
        updateHandDisplay(player, board);
    }

    private void updatePlayerViews() {
        playerCardView.updateHP(player.getNowHP());
        opponentCardView.updateHP(opponent.getNowHP());
    }
    private void updateHandDisplay(Player player, Board board) {
        if (player == this.player) {
            playerHandContainer.getChildren().clear();
            for (Card card : player.getHand().getCards()) {
                CardView cardView = new CardView(card);
                playerHandContainer.getChildren().add(cardView);
                    cardView.setOnMouseClicked(e -> {
                        if (isPlayerTurn) {
                            player.putCardOnTable(card.getID(), board);
                            updateBoardDisplay(board);
                            updateManaLabels();
                            updateHandDisplay(player, board);
                        }
                    });

            }
        } else {
            opponentHandContainer.getChildren().clear();
            for (Card card : player.getHand().getCards()) {
                CardView cardView = new CardView(card);
                opponentHandContainer.getChildren().add(cardView);
                cardView.setOnMouseClicked(e -> {
                    if (!isPlayerTurn) {
                        opponent.putCardOnTable(card.getID(), board);
                        updateBoardDisplay(board);
                        updateManaLabels();
                        updateHandDisplay(player, board);
                    }
                });

            }
        }
    }
    private void updateManaLabels() {
        playerManaLabel.setText(player.getNowMana() + "/" + player.getMana());
        opponentManaLabel.setText(opponent.getNowMana() + "/" + opponent.getMana());

        // Настройка внешнего вида меток, если необходимо
        playerManaLabel.setFont(new Font("Arial", 14));
        opponentManaLabel.setFont(new Font("Arial", 14));
    }
    private void updateBoardDisplay(Board board) {
        HBox currentContainer = board == this.playerBoard ? playerBoardContainer : opponentBoardContainer;
        currentContainer.getChildren().clear();
        for (Card card : board.getBoard()) {
            CardView cardView = new CardView(card);
            currentContainer.getChildren().add(cardView);
            setupCardInteraction(cardView, card);
        }
    }
    private void setupCardInteraction(CardView cardView, Card card) {
        cardView.setOnMouseClicked(e -> {
            if (isPlayerTurn && card.getWhose() == player.getWhose() && selectedCardForAttack == null) {
                selectCardForAttack(cardView);
            } else if (!isPlayerTurn && card.getWhose() == opponent.getWhose() && selectedCardForAttack == null) {
                selectCardForAttack(cardView);
            } else if (selectedCardForAttack != null && card.getWhose() != selectedCardForAttack.getCard().getWhose()) {
                executeAttack(selectedCardForAttack, cardView);
            }
        });
    }
    private void selectCardForAttack(CardView cardView) {
        if (selectedCardForAttack != null) {
            selectedCardForAttack.getStyleClass().remove("selected");
        }
        selectedCardForAttack = cardView;
        selectedCardForAttack.getStyleClass().add("selected");
    }
    private void executeAttack(CardView attacker, CardView target) {
        Board attackerBoard = (attacker.getCard().getWhose() == player.getWhose()) ? this.playerBoard : this.opponentBoard;
        Board targetBoard = (target.getCard().getWhose() == player.getWhose()) ? this.playerBoard : this.opponentBoard;

        attacker.getCard().attackCard(target.getCard(), attackerBoard, targetBoard);

        attacker.getStyleClass().remove("selected");
        selectedCardForAttack = null;


        updateBoardDisplay(this.playerBoard);
        updateBoardDisplay(this.opponentBoard);
        updateGraveyardDisplay(this.playerBoard);
        updateGraveyardDisplay(this.opponentBoard);
        updatePlayerViews();
    }



    private void updateGraveyardDisplay(Board board) {
        HBox currentContainer = board == this.playerBoard ? playerGraveyardContainer : opponentGraveyardContainer;
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
