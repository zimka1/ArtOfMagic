package GameScene;
import Cards.*;
import GameBoard.*;
import Players.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScene extends Application {

    private Board playerBoard = new Board();
    private Board opponentBoard = new Board();
    private Player player = new Player();
    private Player opponent = new Player();

    private HBox playerHandContainer = new HBox(10);
    private HBox opponentHandContainer = new HBox(10);
    private HBox playerBoardContainer = new HBox(10);
    private HBox opponentBoardContainer = new HBox(10);
    private VBox rightSideContainer = new VBox(20);

    @Override
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

        playerHandContainer.setSpacing(5);
        opponentHandContainer.setSpacing(5);

        root.setRight(rightSideContainer);
        root.setBottom(playerHandContainer);
        root.setTop(opponentHandContainer);
        root.setCenter(playerBoardContainer);

        VBox centerContainer = new VBox(5);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(opponentBoardContainer, playerBoardContainer);
        root.setCenter(centerContainer);
        centerContainer.setSpacing(20);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Art Of Magic");
        primaryStage.show();
    }

    private void setupGame() {
        playerBoard.getDeck().addCard(CardLibrary.BattleAxe);
        playerBoard.getDeck().addCard(CardLibrary.DarkLance);
        playerBoard.getDeck().addCard(CardLibrary.EarthGiant);
        playerBoard.getDeck().addCard(CardLibrary.GuardianOfTheForest);
        opponentBoard.getDeck().addCard(CardLibrary.ArcaneBlast);
        opponentBoard.getDeck().addCard(CardLibrary.BattleAxe);
        opponentBoard.getDeck().addCard(CardLibrary.DarkLance);
    }

    private void drawCard(Player player, Board board) {
        player.putCardInHand(board.getDeck());
        updateHandDisplay(player, board);
    }

    private void updateHandDisplay(Player player, Board board) {
        // Обновляем отображение рук игрока и противника
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
        if (board == this.playerBoard) {
            playerBoardContainer.getChildren().clear();
            for (Card card : board.getBoard()) {
                CardView cardView = new CardView(card);
                playerBoardContainer.getChildren().add(cardView);
            }
        } else {
            opponentBoardContainer.getChildren().clear();
            for (Card card : board.getBoard()) {
                CardView cardView = new CardView(card);
                opponentBoardContainer.getChildren().add(cardView);
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}