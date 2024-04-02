package GameScene;

import Judges.JudgeTask;
import Judges.JudgeTaskManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class GameStartScene extends Application {
    private VBox tasksContainer; // Container specifically for tasks, allowing for easy refresh
    private JudgeTaskManager taskManager = new JudgeTaskManager(); // Manages the logic behind assigning and reassigning tasks

    List<JudgeTask> tasksForThisGame;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15); // Main container for the scene
        root.setAlignment(Pos.CENTER); // Ensure content is centered
        root.getStyleClass().add("root");

        // Initialize and set up the tasks container with central alignment
        tasksContainer = new VBox(10);
        tasksContainer.setAlignment(Pos.CENTER); // Center tasks within this container

        displayTasks(); // Initially display tasks

        // Set up and add the 'Start the game' button
        Button startGameButton = new Button("Start the game");
        startGameButton.getStyleClass().add("button");
        startGameButton.setOnAction(event -> moveToGameScene(primaryStage));


        // Set up and add the 'Reassign Tasks' button
        Button reassignTasksButton = new Button("Reassign Tasks");
        reassignTasksButton.getStyleClass().add("button");
        reassignTasksButton.setOnAction(event -> displayTasks()); // Reassign and display new tasks on click

        // Adding the buttons directly to the root ensures they stay at the bottom and are not cleared with tasks
        root.getChildren().addAll(tasksContainer, startGameButton, reassignTasksButton); // Add elements to the root container

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Apply external CSS styling
        primaryStage.setTitle("Start Scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Displays or refreshes the tasks in the dedicated container
    private void displayTasks() {
        tasksContainer.getChildren().clear(); // Clear current tasks before displaying new ones

        Label title = new Label("Tasks for this game:");
        title.getStyleClass().add("label-title");
        tasksContainer.getChildren().addAll(title); // Add the title to the tasks container

        // Fetch and display a set of tasks, including the judge's name for each
        tasksForThisGame = taskManager.getRandomTasksForJudges();
        for (JudgeTask task : tasksForThisGame) {
            String taskText = task.getDescription() + " (Judge: " + task.getOwnerName() + ")";
            Label taskLabel = new Label(taskText);
            taskLabel.getStyleClass().add("task-label");
            tasksContainer.getChildren().add(taskLabel); // Add each task label to the container
        }
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
