package GameScene;

import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import Judges.TaskStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        tasksCompletedContainer.getChildren().add(new Label("Task Verdicts:"));
        final int[] animationsCompleted = {0};

        for (int i = 0; i < tasksForThisGame.size(); i++) {
            JudgeTask task = tasksForThisGame.get(i);
            int progressPercent = judgeTaskManager.calculateProgressPercent(task, taskStatus);
            boolean isPassed = judgeTaskManager.decideIfPassed(progressPercent);
            String verdictText = String.format("%s: %s (%d%% complete)", task.getDescription(), isPassed ? "Passed" : "Failed", progressPercent);

            Label verdictLabel = new Label(verdictText);
            verdictLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            tasksCompletedContainer.getChildren().add(verdictLabel);

            // Анимируем от 0% до progressPercent
            Timeline timeline = new Timeline();
            final int totalFrames = progressPercent; // Количество кадров анимации равно проценту выполнения
            for (int frame = 0; frame <= totalFrames; frame++) {
                int finalFrame = frame;
                KeyFrame keyFrame = new KeyFrame(Duration.millis(frame * 20), e -> {
                    verdictLabel.setText(String.format("%s: %s (%d%% complete)", task.getDescription(), isPassed ? "Passed" : "Failed", finalFrame));
                });
                timeline.getKeyFrames().add(keyFrame);
            }

            timeline.setOnFinished(e -> {
                animationsCompleted[0]++;
                if (animationsCompleted[0] == tasksForThisGame.size()) {
                    displayFinalVerdict(tasksCompletedContainer, tasksForThisGame, judgeTaskManager, taskStatus);
                }
            });

            timeline.play();
        }
    }

    private void displayFinalVerdict(VBox container, List<JudgeTask> tasks, JudgeTaskManager manager, TaskStatus status) {
        int passedCount = 0; // Количество успешно выполненных задач
        int failedCount = 0; // Количество не выполненных задач

        // Подсчет успешно выполненных и не выполненных задач
        for (JudgeTask task : tasks) {
            int progressPercent = manager.calculateProgressPercent(task, status);
            if (manager.decideIfPassed(progressPercent)) {
                passedCount++;
            } else {
                failedCount++;
            }
        }

        // Создание текста для отображения результатов
        String resultsText = String.format("Results: Passed %d, Failed %d", passedCount, failedCount);
        Label resultsLabel = new Label(resultsText);
        resultsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        container.getChildren().add(resultsLabel);

        // Создание и отображение финального вердикта на основе результатов
        String finalVerdictText = "Joint decision: " + (passedCount >= tasks.size() / 2 ? "Passed" : "Didn't pass");
        Label finalVerdictLabel = new Label(finalVerdictText);
        finalVerdictLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        finalVerdictLabel.setTextFill(Color.BLUE);
        container.getChildren().add(finalVerdictLabel);
    }

}
