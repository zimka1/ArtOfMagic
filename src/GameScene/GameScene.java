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

import java.util.Objects;

public class GameScene extends Application {

    private Board playerBoard = new Board(1);
    private Board opponentBoard = new Board(2);
    private Player player = new Player(1);
    private Player opponent = new Player(2);

    private HBox playerHandContainer = new HBox(10);
    private HBox opponentHandContainer = new HBox(10);
    private HBox playerBoardContainer = new HBox(10);
    private HBox opponentBoardContainer = new HBox(10);
    private HBox playerGraveyardContainer = new HBox(10);
    private HBox opponentGraveyardContainer = new HBox(10);
    private VBox rightSideContainer = new VBox(20);
    private VBox leftSideContainer = new VBox(100);

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

        root.setRight(rightSideContainer);
        root.setLeft(leftSideContainer);
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
        HBox currentContainer = board == this.playerBoard ? playerBoardContainer : opponentBoardContainer;
        currentContainer.getChildren().clear();
        for (Card card : board.getBoard()) {
            CardView cardView = new CardView(card);
            currentContainer.getChildren().add(cardView);
            cardView.setOnMouseClicked(e -> {
                if (selectedCardForAttack == null) {
                    selectedCardForAttack = cardView;
                } else {
                    if ((selectedCardForAttack.getCard().getWhose() == card.getWhose()) && (Objects.equals(selectedCardForAttack.getCard().getID(), card.getID()))) {
                        System.out.println(selectedCardForAttack.getCard().getID() + " " + card.getID());
                        selectedCardForAttack = null;
                        return;
                    }
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
        // Допустим, у Board есть метод getGraveyard(), который возвращает список карт на кладбище
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
