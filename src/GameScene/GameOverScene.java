package GameScene;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverScene extends Application {

    GameManager gameManager;

    public GameOverScene(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void start(Stage stage) {
        Label gameOver = new Label("Game over!");
        Label whoWin;
        if (gameManager.getOpponent().getNowHP() <= 0) {
            whoWin = new Label("You win!");
        } else{
            whoWin = new Label("You lose!");
        }

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(gameOver, whoWin);
        vBox.setAlignment(Pos.CENTER); 

        Scene scene = new Scene(vBox, 900, 600);
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }

    // Дополнительные методы (если они у вас есть)
}
