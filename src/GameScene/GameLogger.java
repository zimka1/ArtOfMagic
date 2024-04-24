package GameScene;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GameLogger implements Observer {
    private TextArea playerLog;
    private TextArea opponentLog;
    private Queue<String> playerQueue = new LinkedList<>();
    private Queue<String> opponentQueue = new LinkedList<>();
    private boolean isPlayerAnimationRunning = false;
    private boolean isOpponentAnimationRunning = false;

    public GameLogger(TextArea playerLog, TextArea opponentLog) {
        this.playerLog = playerLog;
        this.opponentLog = opponentLog;
    }

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

    private void animatedTextAppend(TextArea textArea, String text, Runnable onFinish) {
        String currentText = textArea.getText();
        if (currentText.split("\n", -1).length >= 5) {
            String[] lines = currentText.split("\n", -1);
            currentText = String.join("\n", Arrays.copyOfRange(lines, 1, lines.length));
            textArea.setText(currentText);
        }

        animateText(textArea, currentText, text, 0, onFinish);
    }

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
        }
    }


    private void ensureVisible(TextArea textArea) {
            textArea.selectPositionCaret(textArea.getLength());
            textArea.deselect();
        }
}
