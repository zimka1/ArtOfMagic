package GameScene;

import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import Judges.TaskStatus;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

public class GameOverScene extends Application {

    GameManager gameManager;
    TaskStatus taskStatus;
    List<JudgeTask> tasksForThisGame;
    JudgeTaskManager judgeTaskManager = new JudgeTaskManager(); // Assume this is acceptable to instantiate here

    private VBox resultContainer;
    private VBox tasksCompletedContainer;

    public GameOverScene(GameManager gameManager, TaskStatus taskStatus, List<JudgeTask> tasksForThisGame) {
        this.gameManager = gameManager;
        this.taskStatus = taskStatus;
        this.tasksForThisGame = tasksForThisGame;
    }

    @Override
    public void start(Stage stage) {
        boolean win = gameManager.getOpponent().getNowHP() <= 0;

        Label gameOver = new Label("Game over!");
        gameOver.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Устанавливаем шрифт
        Label whoWin = win ? new Label("You win!") : new Label("You lose!");
        whoWin.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Устанавливаем шрифт
        whoWin.setTextFill(win ? Color.GREEN : Color.RED); // Цвет текста в зависимости от результата

        VBox mainContainer = new VBox(10);
        mainContainer.getChildren().addAll(gameOver, whoWin);
        mainContainer.setAlignment(Pos.CENTER);

        resultContainer = new VBox(10);
        resultContainer.setAlignment(Pos.CENTER);

//        Label hpLostLabel = new Label("HP Lost: " + taskStatus.getHPlose());
//        hpLostLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
//        resultContainer.getChildren().add(hpLostLabel);

        // Add resultContainer to the main VBox
        mainContainer.getChildren().add(resultContainer);

        tasksCompletedContainer = new VBox(10);
        tasksCompletedContainer.setAlignment(Pos.CENTER);
        displayTasksProgressAndVerdicts();

        // Add tasksCompletedContainer to the main VBox
        mainContainer.getChildren().add(tasksCompletedContainer);

        Scene scene = new Scene(mainContainer, 900, 600);
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }

    private void displayTasksProgressAndVerdicts() {
        List<String> tasksVerdicts = judgeTaskManager.checkTasksProgressAndVerdicts(taskStatus, tasksForThisGame);
        tasksCompletedContainer.getChildren().add(new Label("Task Verdicts:"));
        for (String verdict : tasksVerdicts) {
            Label verdictLabel = new Label(verdict);
            verdictLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            tasksCompletedContainer.getChildren().add(verdictLabel);
        }
        Label finalVerdict = (Label) tasksCompletedContainer.getChildren().get(tasksCompletedContainer.getChildren().size() - 1);
        finalVerdict.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        finalVerdict.setTextFill(Color.BLUE);
    }
}
