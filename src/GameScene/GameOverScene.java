package GameScene;

import Judges.TaskStatus;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverScene extends Application {

    GameManager gameManager;
    TaskStatus taskStatus; // Add TaskStatus as a class member

    private VBox resultContainer;

    // Modify the constructor to accept TaskStatus
    public GameOverScene(GameManager gameManager, TaskStatus taskStatus) {
        this.gameManager = gameManager;
        this.taskStatus = taskStatus; // Initialize taskStatus
    }

    @Override
    public void start(Stage stage) {
        Label gameOver = new Label("Game over!");
        Label whoWin;
        if (gameManager.getOpponent().getNowHP() <= 0) {
            whoWin = new Label("You win!");
        } else {
            whoWin = new Label("You lose!");
        }

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(gameOver, whoWin);
        vBox.setAlignment(Pos.CENTER);

        resultContainer = new VBox(10);
        resultContainer.setAlignment(Pos.CENTER);

        // Display information from TaskStatus
        resultContainer.getChildren().add(new Label("HP Lost: " + taskStatus.getHPlose()));
        resultContainer.getChildren().add(new Label("Number of Minion Cards: " + taskStatus.getNumberMinionCards()));
        resultContainer.getChildren().add(new Label("Number of Spell Cards: " + taskStatus.getNumberSpellCards()));
        resultContainer.getChildren().add(new Label("Number of Weapon Cards: " + taskStatus.getNumberWeaponCards()));
        resultContainer.getChildren().add(new Label("Use All Cards In One Turn: " + taskStatus.getUseAllCardsInOneTurn()));
        resultContainer.getChildren().add(new Label("Opponent Lost HP In One Turn: " + taskStatus.getOppLostHPInOneTurn()));

        // Add resultContainer to the main VBox
        vBox.getChildren().add(resultContainer);

        Scene scene = new Scene(vBox, 900, 600);
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }
}
