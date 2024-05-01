package GameScene;

import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import Judges.TaskStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/**
 * A scene representing the end of the game, displaying the game result, task verdicts, and an option to restart the game.
 * This scene provides visual feedback on the game outcome, task completion progress, and final verdict based on task results.
 */
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

    /**
     * Initializes and displays the game over scene with game result, task verdicts, and restart option.
     * This method sets up the UI components for displaying the game result, progress of completed tasks, and an option to restart the game.
     *
     * @param stage The primary stage where the game over scene will be displayed.
     */

    @Override
    public void start(Stage stage) {
        boolean win = gameManager.getOpponent().getNowHP() <= 0;

        Label gameOver = new Label("Game over!");
        gameOver.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set font
        Label whoWin = win ? new Label("You win!") : new Label("You lose!");
        whoWin.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Set font
        whoWin.setTextFill(win ? Color.GREEN : Color.RED); // Set text color based on result

        VBox mainContainer = new VBox(10);
        mainContainer.getChildren().addAll(gameOver, whoWin);
        mainContainer.setAlignment(Pos.CENTER);

        resultContainer = new VBox(10);
        resultContainer.setAlignment(Pos.CENTER);

        // Add resultContainer to the main VBox
        mainContainer.getChildren().add(resultContainer);

        tasksCompletedContainer = new VBox(10);
        tasksCompletedContainer.setAlignment(Pos.CENTER);
        displayTasksProgressAndVerdicts();

        // Add tasksCompletedContainer to the main VBox
        mainContainer.getChildren().add(tasksCompletedContainer);

        // Add restart button
        Button restartButton = new Button("Restart Game");
        restartButton.getStyleClass().add("restart-button"); // Apply CSS styling to the button
        restartButton.setOnAction(event -> {
            stage.close();
            // Restart the game by creating a new instance of GameStartScene
            GameStartScene gameStartScene = new GameStartScene();
            Stage newStage = new Stage();
            gameStartScene.start(newStage);
        });
        mainContainer.getChildren().add(restartButton);

        Scene scene = new Scene(mainContainer, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Apply external CSS styling
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the progress and verdicts of tasks completed during the game.
     */
    private void displayTasksProgressAndVerdicts() {
        tasksCompletedContainer.getChildren().add(new Label("Task Verdicts:"));
        final int[] animationsCompleted = {0};

        for (int i = 0; i < tasksForThisGame.size(); i++) {
            JudgeTask task = tasksForThisGame.get(i);
            int progressPercent = judgeTaskManager.calculateProgressPercent(task, taskStatus);
            String verdictText = String.format("%s: (%d%% complete)", task.getDescription(), progressPercent);

            Label verdictLabel = new Label(verdictText);
            verdictLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            tasksCompletedContainer.getChildren().add(verdictLabel);

            // Animate from 0% to progressPercent
            Timeline timeline = new Timeline();
            final int totalFrames = progressPercent; // Number of animation frames equals progress percentage
            for (int frame = 0; frame <= totalFrames; frame++) {
                int finalFrame = frame;
                KeyFrame keyFrame = new KeyFrame(Duration.millis(frame * 20), e -> {
                    verdictLabel.setText(String.format("%s: (%d%% complete)", task.getDescription(), finalFrame));
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

    /**
     * Displays the final verdict based on the results of all tasks completed during the game.
     *
     * @param container The container in which to display the final verdict.
     * @param tasks     The list of tasks relevant to the game.
     * @param manager   The JudgeTaskManager for calculating progress and making decisions.
     * @param status    The status of tasks completed during the game.
     */
    private void displayFinalVerdict(VBox container, List<JudgeTask> tasks, JudgeTaskManager manager, TaskStatus status) {
        int passedCount = 0; // Number of tasks successfully completed
        int failedCount = 0; // Number of tasks failed

        // Count successful and failed tasks
        for (JudgeTask task : tasks) {
            int progressPercent = manager.calculateProgressPercent(task, status);
            if (manager.decideIfPassed(progressPercent)) {
                passedCount++;
            } else {
                failedCount++;
            }
        }

        // Create text to display results
        String resultsText = String.format("Results: Passed %d, Failed %d", passedCount, failedCount);
        Label resultsLabel = new Label(resultsText);
        resultsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        container.getChildren().add(resultsLabel);

        // Create and display final verdict based on results
        String finalVerdictText = "Joint decision: " + (passedCount > failedCount ? "Passed" : "Didn't pass");
        Label finalVerdictLabel = new Label(finalVerdictText);
        finalVerdictLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        finalVerdictLabel.setTextFill(Color.BLUE);
        container.getChildren().add(finalVerdictLabel);
    }

}
