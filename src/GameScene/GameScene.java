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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class GameScene extends Application {

    private Board playerBoard = new Board(1);
    private Board opponentBoard = new Board(2);
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
    private VBox rightSideContainer = new VBox(20);
    private VBox leftSideContainer = new VBox(20);

    private VBox topSideContainer = new VBox(20);
    private VBox bottomSideContainer = new VBox(20);

    private CardView selectedCardForAttack = null;

    public void start(Stage primaryStage) {
        setupGame();

        BorderPane root = new BorderPane();
        playerHandContainer.setAlignment(Pos.CENTER);
        opponentHandContainer.setAlignment(Pos.CENTER);
        playerBoardContainer.setAlignment(Pos.CENTER);
        opponentBoardContainer.setAlignment(Pos.CENTER);


        Button playerDeckButton = new Button("Player's deck");
        playerDeckButton.setOnAction(e -> drawCard(player, playerBoard));

        Button opponentDeckButton = new Button("Opponent's deck");
        opponentDeckButton.setOnAction(e -> drawCard(opponent, opponentBoard));

        rightSideContainer.getChildren().addAll(opponentDeckButton, playerDeckButton);
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

        String borderStyle = "-fx-border-color: black; " +
                "-fx-border-width: 1; " +
                "-fx-border-style: solid; " +
                "-fx-padding: 5;";

        playerBoardContainer.setStyle(borderStyle);
        opponentBoardContainer.setStyle(borderStyle);
        playerBoardContainer.setMaxWidth(700);
        opponentBoardContainer.setMaxWidth(700);

        Scene scene = new Scene(root, 1200, 1000);
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
        opponentBoard.getDeck().addCard(CardLibrary.opponents_ArcaneBlast);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_BattleAxe);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_DarkLance);
        opponentBoard.getDeck().addCard(CardLibrary.opponents_EarthGiant);
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
                    player.putCardOnTable(card.getID(), board);
                    updateBoardDisplay(board);
                    updateHandDisplay(player, board);
                });
            }
        } else {
            opponentHandContainer.getChildren().clear();
            for (Card card : player.getHand().getCards()) {
                CardView cardView = new CardView(card);
                opponentHandContainer.getChildren().add(cardView);
                cardView.setOnMouseClicked(e -> {
                    opponent.putCardOnTable(card.getID(), board);
                    updateBoardDisplay(board);
                    updateHandDisplay(player, board);
                });
            }
        }
    }
    private void updateBoardDisplay(Board board) {
        HBox currentContainer = board == this.playerBoard ? playerBoardContainer : opponentBoardContainer;
        currentContainer.getChildren().clear();
        for (Card card : board.getBoard()) {
            CardView cardView = new CardView(card);
            currentContainer.getChildren().add(cardView);
            playerCardView.setOnMouseClicked(e -> {
                if (selectedCardForAttack != null && selectedCardForAttack.getCard().getWhose() != player.getWhose()) {
                    player.takingDamage(selectedCardForAttack.getCard().getPower());
                    updatePlayerViews();
                    selectedCardForAttack.setStyle("");
                    selectedCardForAttack = null;
                }
            });

            opponentCardView.setOnMouseClicked(e -> {
                if (selectedCardForAttack != null && selectedCardForAttack.getCard().getWhose() != opponent.getWhose()) {
                    opponent.takingDamage(selectedCardForAttack.getCard().getPower());
                    updatePlayerViews();
                    selectedCardForAttack.setStyle("");
                    selectedCardForAttack = null;

                }
            });
            cardView.setOnMouseClicked(e -> {
                if (selectedCardForAttack == null) {
                    selectedCardForAttack = cardView;
                    selectedCardForAttack.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;");
                } else {
                    if ((selectedCardForAttack.getCard().getWhose() == card.getWhose())) {
                        System.out.println(selectedCardForAttack.getCard().getID() + " " + card.getID());
                        selectedCardForAttack.setStyle("");
                        selectedCardForAttack = null;
                        return;
                    }
                    System.out.println(selectedCardForAttack.getCard().getWhose() + " " + card.getWhose());
                    Board attackerBoard = selectedCardForAttack.getCard().getWhose() == this.player.getWhose() ? this.playerBoard : this.opponentBoard;
                    Board defenderBoard = card.getWhose() == this.player.getWhose() ? this.playerBoard : this.opponentBoard;
                    selectedCardForAttack.getCard().attackCard(card, attackerBoard, defenderBoard);
                    selectedCardForAttack = null;
                    updateBoardDisplay(this.playerBoard);
                    updateBoardDisplay(this.opponentBoard);
                    playerBoard.getGraveyard().DisplayGraveyard();
                    updateGraveyardDisplay(this.opponentBoard);
                    updateGraveyardDisplay(this.playerBoard);
                }
            });
        }
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
