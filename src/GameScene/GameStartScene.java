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

/**
 * The {@code GameStartScene} class is responsible for setting up and displaying
 * the initial game start scene for the application. This scene allows users to
 * view and reassign tasks before starting the game.
 * <p>
 * This class extends {@link javafx.application.Application} and sets up a simple
 * GUI using JavaFX that includes buttons for starting the game and reassigning tasks,
 * as well as displaying the list of tasks assigned to judges.
 */
public class GameStartScene extends Application {
    private VBox tasksContainer; // Container for displaying tasks.
    private JudgeTaskManager taskManager = new JudgeTaskManager(); // Manages judge tasks.
    private Random random = new Random();

    List<JudgeTask> tasksForThisGame; // Holds the tasks assigned for the current game.

    /**
     * Starts and displays the primary stage with necessary UI components.
     * Sets up the layout and event handlers for the game start scene.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene is set.
     */
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

    /**
     * Displays the list of tasks for the game in the UI.
     * Each task is animated to appear one character at a time.
     */
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

    /**
     * Animates the display of each task label by gradually appending characters
     * to the label text using a {@link Timeline}.
     *
     * @param label    the label to animate.
     * @param fullText the full text to display in the label.
     */
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

    /**
     * Transitions to the main game scene, closing the current start scene.
     *
     * @param currentStage the current stage that will be closed as the game scene starts.
     */
    private void moveToGameScene(Stage currentStage) {
        try {
            GameScene gameScene = new GameScene(tasksForThisGame);
            currentStage.close();
            Stage newStage = new Stage();
            gameScene.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
