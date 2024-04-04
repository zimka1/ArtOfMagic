package GameScene;

import GameScene.GameScene;
import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class GameStartScene extends Application {
    private VBox tasksContainer;
    private JudgeTaskManager taskManager = new JudgeTaskManager();
    private Random random = new Random();

    List<JudgeTask> tasksForThisGame;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");

        tasksContainer = new VBox(10);
        tasksContainer.setAlignment(Pos.CENTER);

        displayTasks();

        Button startGameButton = new Button("Start the game");
        startGameButton.getStyleClass().add("button");
        startGameButton.setOnAction(event -> moveToGameScene(primaryStage));

        Button reassignTasksButton = new Button("Reassign Tasks");
        reassignTasksButton.getStyleClass().add("button");
        reassignTasksButton.setOnAction(event -> displayTasks());

        root.getChildren().addAll(tasksContainer, startGameButton, reassignTasksButton);

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setTitle("Start Scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayTasks() {
        tasksContainer.getChildren().clear();

        Label title = new Label("Tasks for this game:");
        title.getStyleClass().add("label-title");
        tasksContainer.getChildren().add(title);

        tasksForThisGame = taskManager.getRandomTasksForJudges();
        for (JudgeTask task : tasksForThisGame) {
            String taskText = task.getDescription() + " (Judge: " + task.getOwnerName() + ")";
            Label taskLabel = new Label();
            taskLabel.getStyleClass().add("task-label");
            tasksContainer.getChildren().add(taskLabel);

            animateTaskLabel(taskLabel, taskText);
        }
    }

    private void animateTaskLabel(Label label, String fullText) {
        label.setText("");
        Timeline timeline = new Timeline();

        for (int i = 0; i < fullText.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(index * 0.05), event -> {
                String partialText = fullText.substring(0, index + 1);
                label.setText(partialText);
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();
    }

    private void moveToGameScene(Stage currentStage) {
        try {
            // Assuming GameScene is your class that sets up the game scene
            GameScene gameScene = new GameScene(tasksForThisGame);
            // Close the current window
            currentStage.close();
            // Set up a new stage for the game scene
            Stage newStage = new Stage();
            gameScene.start(newStage); // Start the game scene on the new stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
