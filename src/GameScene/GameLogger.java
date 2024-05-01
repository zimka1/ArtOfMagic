package GameScene;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class GameLogger that implements Observer to handle and animate game logs for players and opponents.
 */
public class GameLogger implements Observer {
    private TextArea playerLog;
    private TextArea opponentLog;
    private Queue<String> playerQueue = new LinkedList<>();
    private Queue<String> opponentQueue = new LinkedList<>();
    private boolean isPlayerAnimationRunning = false;
    private boolean isOpponentAnimationRunning = false;

    /**
     * Constructs a GameLogger with separate text areas for player and opponent logs.
     *
     * @param playerLog the text area to display player's logs
     * @param opponentLog the text area to display opponent's logs
     */
    public GameLogger(TextArea playerLog, TextArea opponentLog) {
        this.playerLog = playerLog;
        this.opponentLog = opponentLog;
    }

    /**
     * Updates the log for the player or opponent based on the event and player type.
     *
     * @param event the event message to log
     * @param playerType the type of player ("Player" or "Opponent")
     */
    public void update(String event, String playerType) {
        Platform.runLater(() -> {
            try {
                if ("Player".equals(playerType) && playerLog != null) {
                    playerQueue.add(event);
                    if (!isPlayerAnimationRunning) {
                        isPlayerAnimationRunning = true;
                        animateText(playerLog, playerQueue);
                    }
                } else if ("Opponent".equals(playerType) && opponentLog != null) {
                    opponentQueue.add(event);
                    if (!isOpponentAnimationRunning) {
                        isOpponentAnimationRunning = true;
                        animateText(opponentLog, opponentQueue);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error updating log: " + e.getMessage());
            }
        });
    }

    /**
     * Animates the text in the specified text area using the events from the queue.
     *
     * @param textArea the text area where the text will be animated
     * @param queue the queue containing the text events to be animated
     */
    private void animateText(TextArea textArea, Queue<String> queue) {
        if (queue.isEmpty()) {
            if (textArea == playerLog) {
                isPlayerAnimationRunning = false;
            } else {
                isOpponentAnimationRunning = false;
            }
            return;
        }

        String event = queue.poll();
        animatedTextAppend(textArea, event + "\n", () -> animateText(textArea, queue));
    }

    /**
     * Helper method to append text to a text area and ensure older lines are removed if necessary.
     *
     * @param textArea the text area to append text
     * @param text the text to append
     * @param onFinish a callback to be executed after the text has been appended
     */
    private void animatedTextAppend(TextArea textArea, String text, Runnable onFinish) {
        Platform.runLater(() -> {
            String[] lines = textArea.getText().split("\n", -1);
            if (lines.length >= 5) {
                String[] newLines = Arrays.copyOfRange(lines, 1, lines.length);
                textArea.setText(String.join("\n", newLines));
            }

            animateText(textArea, textArea.getText(), text, 0, onFinish);
        });
    }

    /**
     * Animates the appending of text to a text area, character by character.
     *
     * @param textArea the text area to which text is being appended
     * @param existingContent the existing content of the text area
     * @param textToAppend the text to append
     * @param index the current index of the character being appended
     * @param onFinish callback to execute after animation is complete
     */
    private void animateText(TextArea textArea, String existingContent, String textToAppend, int index, Runnable onFinish) {
        if (index < textToAppend.length()) {
            textArea.setText(existingContent + textToAppend.substring(0, index + 1));
            PauseTransition pause = new PauseTransition(Duration.millis(5));
            pause.setOnFinished(e -> animateText(textArea, existingContent, textToAppend, index + 1, onFinish));
            pause.play();
        } else {
            if (onFinish != null) {
                onFinish.run();
            }
            ensureVisible(textArea);
        }
    }

    /**
     * Ensures the most recently appended text is visible in the text area.
     *
     * @param textArea the text area to scroll
     */
    private void ensureVisible(TextArea textArea) {
        textArea.selectPositionCaret(textArea.getLength());
        textArea.deselect();
    }
}
